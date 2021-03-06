package com.itecheasy.core.task;

import com.itecheasy.common.util.CollectionUtils;
import com.itecheasy.core.fba.AmazonReportService;
import com.itecheasy.core.system.Shop;
import com.itecheasy.core.system.SystemService;
import com.itecheasy.core.util.StaticUtils;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Auther: liteng
 * @Date: 2018/7/3 11:01
 * @Description: 定时任务，自动来跑亚马逊，获取报告，之后写入本地
 */
public class SyncAmozonStackReportTaskImpl implements SyncAmozonStackReportTask {

    private static Logger logger = Logger.getLogger(SyncAmozonStackReportTaskImpl.class.getName());

    private SystemService systemService;
    private AmazonReportService amazonReportService;
    //用于多线程访问的shopList,当enableShops中没有了元素，今天就不会再跑了
    private List<Shop> enableShops;
    //0今天的还没有走，1今天的任务已经做完了
    private int lastOneFlag = 0;

    public void setSystemService(SystemService systemService) {
        this.systemService = systemService;
    }

    public void setAmazonReportService(AmazonReportService amazonReportService) {
        this.amazonReportService = amazonReportService;
    }

    public void initShopList() {
        logger.info("初始化要获取亚马逊报告的店铺");
//        enableShops = shopDao.getEnableShops();
        // TODO: 2018/9/4
         this.enableShops = systemService.getSyncAmazonStockShopList();


//        for (Iterator<Shop> it = enableShops.iterator(); it.hasNext(); ) {
//            Shop sp = it.next();
//            boolean isAmazonShop = sp.getName().startsWith("Amazon");
//            if (!isAmazonShop) {
//                it.remove();
//            }
//        }

    }

    /**
     * 获取所有的商店report，多线程quartz
     */
    @Override
    public void syncAmozonStackReportTask() {
        logger.info("开始调用中间服务来获取亚马逊商品库存报告");
        //init shopList every day
        if (enableShops == null && lastOneFlag == 0) {    //第一次时执行,今天的跑完了之后就把flag改为1，就不会再初始化了店铺了
            initShopList();
        }

        //访问亚马逊 全部
        if (enableShops != null && enableShops.size() > 0) {
            List<Shop> shopList = new ArrayList<Shop>();
            Shop shop = enableShops.get(0);
            shopList.add(shop);
            enableShops.remove(0);
            for (Iterator<Shop> it = enableShops.iterator(); it.hasNext(); ) {
                Shop sp = it.next();
                if (sp.getShopDevInfoId().equals(shop.getShopDevInfoId())) {
                    shopList.add(sp);
                    it.remove();
                }
            }

            if (CollectionUtils.isNotEmpty(shopList)) {
                List<Integer> shopIds = new ArrayList<Integer>();
                for (Shop sh : shopList) {
                    shopIds.add(sh.getId());
                }
                logger.error("以下店铺将开始请求亚马逊" + shopIds);
                Map<Integer, Boolean> isSuccessSyncAmazonStock = amazonReportService.syncAmazonStockReportFromAmazon(shopList);
                logger.error("以下店铺请求亚马逊获取报告完成" + shopIds);
                for (Map.Entry<Integer, Boolean> integerBooleanEntry : isSuccessSyncAmazonStock.entrySet()) {
                    //true 同步成功   //false 同步失败
                    if (!integerBooleanEntry.getValue()) {
                        StaticUtils.addEmail("OSMS系统:同步库存报告出错", "\r\n" + "shopId：" + integerBooleanEntry.getKey());
                    }
                }
            }


            //拿完之后删除掉刚刚拿到的那个
            //今天的已经跑完了   停止的条件
            if (enableShops.size() == 0) {
                lastOneFlag = 1;

            }
            logger.info("完成调用中间服务获取亚马逊商品库存报告");
        }
    }


    /**
     * 用于重置lastOneFlag,就是判断今天是否已经跑过了一次亚马逊
     */
    @Override
    public void resetEverydaySync() {
        if (enableShops != null && enableShops.size() == 0 && lastOneFlag == 1) {  //第二次和以后的重置，单独的自动任务来初始化店铺
            initShopList();
            logger.error("已经重置lastOneFlag，开始获取今天新的亚马逊stockReport");

            //其他的异常情况
        } else if (enableShops != null && lastOneFlag != 1) {
            logger.error("初始化错误，今天不能初始化并且访问亚马逊来获取报告，可能是昨天或者更早的报告没有完成");
        } else {
            logger.error("未知错误,不能初始化亚马逊自动任务来获取报告");
        }
    }

}
