package com.itecheasy.core.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.itecheasy.common.util.CollectionUtils;
import com.itecheasy.common.util.DateUtils;
import com.itecheasy.core.fba.FbaShopProductService;
import com.itecheasy.core.fba.dao.FbaShopProductDao;
import com.itecheasy.core.po.ThreeMonthSalesSkuTempPO;
import com.itecheasy.core.prepare.dao.ThreeMonthSalesSkuTempDao;
import com.itecheasy.core.system.Shop;
import com.itecheasy.core.system.ShopService;

/**
 * @author taozihao
 * @date 2018年7月4日 上午11:21:53
 * @description
 */
public class StatisticFbaShopProductServiceImpl implements StatisticFbaShopProductService {

	private static final Logger LOGGER = Logger.getLogger(StatisticFbaShopProductServiceImpl.class);
	private FbaShopProductService fbaShopProductService;
	private ShopService shopService;
	private ThreeMonthSalesSkuTempDao threeMonthSalesSkuTempDao;
	private FbaShopProductDao fbaShopProductDao;

	public void setFbaShopProductService(FbaShopProductService fbaShopProductService) {
		this.fbaShopProductService = fbaShopProductService;
	}

	public void setShopService(ShopService shopService) {
		this.shopService = shopService;
	}

	public void setThreeMonthSalesSkuTempDao(ThreeMonthSalesSkuTempDao threeMonthSalesSkuTempDao) {
		this.threeMonthSalesSkuTempDao = threeMonthSalesSkuTempDao;
	}

	public void setFbaShopProductDao(FbaShopProductDao fbaShopProductDao) {
		this.fbaShopProductDao = fbaShopProductDao;
	}

	@Override
	public void autoStatisticFbaShopProductMonthSales() {
		LOGGER.info("statisticFbaShopProductMonthSales-->> start at " + new Date());
		Date today = DateUtils.getFullDate(new Date());
		Date endDate = org.apache.commons.lang.time.DateUtils.addDays(today, -2);
		Date startDate = org.apache.commons.lang.time.DateUtils.addDays(endDate, -30);
		fbaShopProductService.statisticFbaShopProductMonthSales(startDate, endDate);
		LOGGER.info("statisticFbaShopProductMonthSales-->> end at " + new Date());
	}

	@Override
	public void autoStatisticThreeMonth() {
		LOGGER.info("autoStatisticThreeMonth-->> start at " + new Date());
		// 删除原来所有
		threeMonthSalesSkuTempDao.deleteAllData();
		List<Shop> shops = shopService.getAvailableShops();
		int id = 1;
		for (Shop shop : shops) {
			LOGGER.info(shop.getName()+"-->> start at " + new Date());
			List<ThreeMonthSalesSkuTempPO> tempList = new ArrayList<ThreeMonthSalesSkuTempPO>();
			Date today = DateUtils.getFullDate(new Date());
			Date endDate = org.apache.commons.lang.time.DateUtils.addDays(today, -2);
			Date startDate = org.apache.commons.lang.time.DateUtils.addDays(endDate, -30);
			List<String> skus = fbaShopProductService.getHotSaleProducts(startDate, endDate, shop.getId(), 50);
			if (CollectionUtils.isEmpty(skus)) {
				LOGGER.info(shop.getName()+" end -->> no conditional product");
				continue;
			}
			boolean flag =false;
			for (int i = 0; i < 2; i++) {
				endDate = startDate;
				startDate = org.apache.commons.lang.time.DateUtils.addDays(endDate, -30);
				List<String> list = fbaShopProductService.getHotSaleProducts(startDate, endDate, shop.getId(), 50);
				if (CollectionUtils.isEmpty(list)) {
					flag = true;
					break;
				}

				skus.retainAll(list);
			}
			if(flag){
				LOGGER.info(shop.getName()+" end -->> no conditional product");
				continue;
			}
			Date updateTime = new Date();
			for (String sku : skus) {
				ThreeMonthSalesSkuTempPO po = new ThreeMonthSalesSkuTempPO();
				po.setId(id++);
				//id++;
				po.setShopId(shop.getId());
				po.setSku(sku);
				po.setUpdateTime(updateTime);
				tempList.add(po);
			}
			threeMonthSalesSkuTempDao.addObject(tempList);
			LOGGER.info(shop.getName()+"-->> end at " + new Date());
		}
		LOGGER.info("autoStatisticThreeMonth-->> end at " + new Date());
	}

	@Override
	public void autoUpdateHasSeaTransported() {
		LOGGER.info("autoUpdateHasSeaTransported-->> start at " + new Date());
		fbaShopProductDao.updateHasSeaTransportedForAllShop();
		LOGGER.info("autoUpdateHasSeaTransported-->> end at " + new Date());
	}

}
