package com.itecheasy.core.task;

import com.itecheasy.common.util.DateUtils;
import com.itecheasy.core.BussinessException;
import com.itecheasy.core.fba.FbaInboundService;
import com.itecheasy.core.fba.PutTransportContentVO;
import com.itecheasy.core.fba.dao.ReplenishmentOrderDao;
import com.itecheasy.core.fba.dao.ReplenishmentOrderItemDao;
import com.itecheasy.core.order.OrderService;
import com.itecheasy.core.order.dao.OrderDao;
import com.itecheasy.core.order.dao.OrderProductDao;
import com.itecheasy.core.order.dao.OrderTrackingDao;
import com.itecheasy.core.po.OrderTrackingPO;
import com.itecheasy.core.po.ReplenishmentOrderPO;
import com.itecheasy.webservice.amazon.AmazonShipmentStatusListVO;
import com.itecheasy.webservice.amazon.InboundShipmentInfoVO;
import com.itecheasy.webservice.amazon.ListInboundShipmentsResultVO;
import org.apache.log4j.Logger;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.*;

/**
 * @Auther: liteng
 * @Date: 2018/6/28 08:57
 * @Description:
 */
public class AmozonUpdateReplenishmentShipmentImpl implements AmozonUpdateReplenishmentShipment {

    private static final Logger LOGGER = Logger.getLogger(AmozonUpdateReplenishmentShipmentImpl.class);

    private ReplenishmentOrderDao replenishmentOrderDao;


    private OrderTrackingDao orderTrackingDao;

    public void setOrderTrackingDao(OrderTrackingDao orderTrackingDao) {
        this.orderTrackingDao = orderTrackingDao;
    }

    private FbaInboundService fbaInboundService;


    public void setReplenishmentOrderDao(ReplenishmentOrderDao replenishmentOrderDao) {
        this.replenishmentOrderDao = replenishmentOrderDao;
    }

    public void setFbaInboundService(FbaInboundService fbaInboundService) {
        this.fbaInboundService = fbaInboundService;
    }

    @Override
    public void autoUploadOrderCode() {
        LOGGER.info("start同步订单跟踪号到亚马逊");

        Date beginDate = java.sql.Date.valueOf("2018-5-1");
        //查出所有的订单，并且状态是已发货的
        List<ReplenishmentOrderPO> replenishmentOrderPOS =
                replenishmentOrderDao.findListByHql("FROM ReplenishmentOrderPO WHERE status=? AND deliveryDate>=? AND ltrim(rtrim(fba_replenishment_plan_id)) != '' ",new Object[]{OrderService.OrderStatus.已发货.getVal(),beginDate});

        // TODO: 2018/6/28 auto task upload ordertracking to amazon
        //跟踪单号如果为空就不执行提交亚马逊，如果不为空就提交给亚马逊   ,选择一个时间，这个时间以后的执行
        for (ReplenishmentOrderPO replenishmentOrderPO : replenishmentOrderPOS) {
            //每个补货订单下可能有多个跟踪订单
            List<OrderTrackingPO> orderTrackingPOS = orderTrackingDao.
                    findListByHql("FROM OrderTrackingPO WHERE orderCode=? ",new Object[]{replenishmentOrderPO.getCode()});
            if (orderTrackingPOS!=null && orderTrackingPOS.size()>0) {
                PutTransportContentVO putTransportContentVO = new PutTransportContentVO();
                putTransportContentVO.setShipmentId(replenishmentOrderPO.getShipmentID());
                putTransportContentVO.setOperatorId(-3); //自动任务小于零,用于不记录日志
                putTransportContentVO.setOrderTrackCode(orderTrackingPOS.get(0).getCode());
                putTransportContentVO.setShopId(replenishmentOrderPO.getShopId());
                putTransportContentVO.setReplenishmentOrderCode(replenishmentOrderPO.getCode());
                String transportStatus = fbaInboundService.putTransportContent(putTransportContentVO);

//                if (transportStatus==null){
//                    throw new BussinessException("更新货运单号至亚马逊失败,补货订单编号:"+replenishmentOrderPO.getCode());
//                }
                //更新亚马逊货件状态为已发货
                fbaInboundService.updateFbaInboundOrder(replenishmentOrderPO,"SHIPPED",false);

            }
        }
        LOGGER.info("end同步订单跟踪号到亚马逊");
    }

    @Override
    public void autoSyncInboundShipments() {
        LOGGER.info("start同步FBA补货订单状态到osms");
        //查出所有的订单，并且状态是已发货的
        Date beginDate = java.sql.Date.valueOf("2018-5-1");
        //增加一个条件 某个时间开始的订单          latest_shipping_date>='2018-5-1'
        List<ReplenishmentOrderPO> replenishmentOrderPOS = replenishmentOrderDao.findListByHql
                ("FROM ReplenishmentOrderPO WHERE status=? AND deliveryDate>=? ", new Object[]{OrderService.OrderStatus.已发货.getVal(),beginDate});
        //遍历把所有的已发货状态的商品的shipmentId传给亚马逊 返回的状态写入到数据库

        //分类ids //根据shopId进行分类
//        Map<Integer,Map<Integer, List<String>>> mapIndex = new HashMap<Integer, Map<Integer, List<String>>>();
        Map<Integer, List<String>> map = new HashMap<Integer, List<String>>();
        for (ReplenishmentOrderPO replenishmentOrderPO : replenishmentOrderPOS) {
            if(!map.containsKey(replenishmentOrderPO.getShopId())){
                map.put(replenishmentOrderPO.getShopId(),new ArrayList<String>());
            }
            List<String> integerList = map.get(replenishmentOrderPO.getShopId());
            integerList.add(replenishmentOrderPO.getShipmentID());
        }

        //不同商店的的订单调用不同的api,这是第一个shopId的
        for (Map.Entry<Integer, List<String>> entry : map.entrySet()) {
            AmazonShipmentStatusListVO vo = new AmazonShipmentStatusListVO();
            List<String> shipmentIdList = entry.getValue();
            vo.setShipmentIdList(shipmentIdList);
            Integer shopId = entry.getKey();

            //只需要同步OSMS中FBA补货订单状态是已发货，发货日期是2018-5-1之后的订单。
            vo.setStartDate(DateUtils.getXMLGregorianCalendar(new Date()));
            ListInboundShipmentsResultVO listInboundShipmentsResultVO = fbaInboundService.listInboundShipments(vo,shopId); //ListInboundShipments调用

            String token = null;
            if (listInboundShipmentsResultVO!=null) {
                updateAmazonOrderStatus(listInboundShipmentsResultVO,shopId);  //持久化第一次返回的一部分需要save
                token = listInboundShipmentsResultVO.getNextToken();
            }
            //test if find token being ten
//            token = " 10 ";

            while (token!=null && !"".equalsIgnoreCase(token.trim())){          //如果是否有nextToken,有的话需要继续调用，直到nextToken为null为止
                ListInboundShipmentsResultVO resultVO = fbaInboundService.listInboundShipmentsByNextToken(token,shopId);
                if (resultVO!=null) {
                    updateAmazonOrderStatus(resultVO,shopId);  //持久化返回剩下的的一部分
                    token = resultVO.getNextToken();
                }else if (resultVO == null){
                    token = null;   //clear token
                }
            }
        }
        LOGGER.info("end同步FBA补货订单状态到osms");
    }


    /**
     * 根据调用的亚马逊接口的返回状态，更新本地亚马逊补货订单的时间和亚马逊发货状态
     * @param listInboundShipmentsResultVO
     */
    public void updateAmazonOrderStatus(ListInboundShipmentsResultVO listInboundShipmentsResultVO,Integer shopId){
        for (InboundShipmentInfoVO inboundShipmentInfoVO : listInboundShipmentsResultVO.getInboundShipmentInfoVOList()) {

            ReplenishmentOrderPO byHql = replenishmentOrderDao.
                    findByHql("FROM ReplenishmentOrderPO WHERE shipmentID=? AND shopId=? ", new Object[]{inboundShipmentInfoVO.getShipmentId(),shopId});
            if ("RECEIVING".equalsIgnoreCase(inboundShipmentInfoVO.getShipmentStatus().trim()) ||
                    "CLOSED".equalsIgnoreCase(inboundShipmentInfoVO.getShipmentStatus().trim())) {
                // 检测到订单为“RECEIVING，CLOSED”状态时，都称为已到货，把时间记下来。这个字段，是为海运备货计划做准备用。

                byHql.setOrderAmazonStatus(OrderService.OrderAmazonStatus.RECEIVING.getStatusCode());
                byHql.setAmazonPracticalityArriveDate(new Date());   //当前时间就是 实际到货时间
                replenishmentOrderDao.updateObject(byHql);
            }
            //其他状态时暂时不处理
        }
    }


    public static void main(String[] args) {
        System.out.println(DateUtils.convertDate("2018-5-1"));
        Date beginDate = java.sql.Date.valueOf("2018-5-1");
        System.out.println(beginDate);

    }
}