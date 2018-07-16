package com.itecheasy.core.prepare;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import com.itecheasy.common.PageList;
import com.itecheasy.common.util.CollectionUtils;
import com.itecheasy.common.util.DateUtils;
import com.itecheasy.core.BussinessException;
import com.itecheasy.core.fba.dao.AmazonStockReportDao;
import com.itecheasy.core.fba.dao.FbaReplenishmentPlanDao;
import com.itecheasy.core.fba.dao.FbaReplenishmentPlanOperateLogDao;
import com.itecheasy.core.fba.dao.FbaShopProductDao;
import com.itecheasy.core.fba.dao.ReplenishmentOrderItemDao;
import com.itecheasy.core.fba.dao.ShopProductCmsInfoDao;
import com.itecheasy.core.po.AmazonStockReportPO;
import com.itecheasy.core.po.FbaReplenishmentPlanOperateLogPO;
import com.itecheasy.core.po.FbaReplenishmentPlanPO;
import com.itecheasy.core.po.FbaShopProductPO;
import com.itecheasy.core.po.SeaTransportationPreparePlanItemPO;
import com.itecheasy.core.po.SeaTransportationPreparePlanOperateLogPO;
import com.itecheasy.core.po.SeaTransportationPreparePlanPO;
import com.itecheasy.core.po.ShopProductCmsInfoPO;
import com.itecheasy.core.po.ThreeMonthSalesSkuTempPO;
import com.itecheasy.core.po.UserPO;
import com.itecheasy.core.prepare.dao.SeaTransportationPreparePlanDao;
import com.itecheasy.core.prepare.dao.SeaTransportationPreparePlanItemDao;
import com.itecheasy.core.prepare.dao.SeaTransportationPreparePlanOperateLogDao;
import com.itecheasy.core.prepare.dao.ThreeMonthSalesSkuTempDao;
import com.itecheasy.core.system.Shop;
import com.itecheasy.core.system.SystemService;
import com.itecheasy.core.user.dao.UserDAO;

/**
 * @author taozihao
 * @date 2018年7月4日 上午8:44:26
 * @description 海运备货计划
 */
public class SeaTransportationPrepareServiceImpl implements SeaTransportationPrepareService {

	private SystemService systemService;
	private SeaTransportationPreparePlanDao seaTransportationPreparePlanDao;
	private SeaTransportationPreparePlanItemDao seaTransportationPreparePlanItemDao;
	private SeaTransportationPreparePlanOperateLogDao seaTransportationPreparePlanOperateLogDao;
	private ShopProductCmsInfoDao shopProductCmsInfoDao;
	private FbaShopProductDao fbaShopProductDao;
	private ThreeMonthSalesSkuTempDao threeMonthSalesSkuTempDao;
	private ReplenishmentOrderItemDao replenishmentOrderItemDao;
	private AmazonStockReportDao amazonStockReportDao;
	private UserDAO userDAO;
	private FbaReplenishmentPlanDao fbaReplenishmentPlanDao;
	private FbaReplenishmentPlanOperateLogDao fbaReplenishmentPlanOperateLogDao;

	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	public void setSeaTransportationPreparePlanDao(SeaTransportationPreparePlanDao seaTransportationPreparePlanDao) {
		this.seaTransportationPreparePlanDao = seaTransportationPreparePlanDao;
	}

	public void setSeaTransportationPreparePlanItemDao(
			SeaTransportationPreparePlanItemDao seaTransportationPreparePlanItemDao) {
		this.seaTransportationPreparePlanItemDao = seaTransportationPreparePlanItemDao;
	}

	public void setSeaTransportationPreparePlanOperateLogDao(
			SeaTransportationPreparePlanOperateLogDao seaTransportationPreparePlanOperateLogDao) {
		this.seaTransportationPreparePlanOperateLogDao = seaTransportationPreparePlanOperateLogDao;
	}

	public void setShopProductCmsInfoDao(ShopProductCmsInfoDao shopProductCmsInfoDao) {
		this.shopProductCmsInfoDao = shopProductCmsInfoDao;
	}

	public void setFbaShopProductDao(FbaShopProductDao fbaShopProductDao) {
		this.fbaShopProductDao = fbaShopProductDao;
	}

	public void setThreeMonthSalesSkuTempDao(ThreeMonthSalesSkuTempDao threeMonthSalesSkuTempDao) {
		this.threeMonthSalesSkuTempDao = threeMonthSalesSkuTempDao;
	}

	public void setReplenishmentOrderItemDao(ReplenishmentOrderItemDao replenishmentOrderItemDao) {
		this.replenishmentOrderItemDao = replenishmentOrderItemDao;
	}

	public void setAmazonStockReportDao(AmazonStockReportDao amazonStockReportDao) {
		this.amazonStockReportDao = amazonStockReportDao;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public void setFbaReplenishmentPlanDao(FbaReplenishmentPlanDao fbaReplenishmentPlanDao) {
		this.fbaReplenishmentPlanDao = fbaReplenishmentPlanDao;
	}

	public void setFbaReplenishmentPlanOperateLogDao(FbaReplenishmentPlanOperateLogDao fbaReplenishmentPlanOperateLogDao) {
		this.fbaReplenishmentPlanOperateLogDao = fbaReplenishmentPlanOperateLogDao;
	}

	@Override
	public PageList<SeaTransportationPlanVO> getSeaTransportationPlanList(PreparePlanSearchVO planSearchVO,
			int operatorId) {
		StringBuffer buffer = new StringBuffer("SELECT DISTINCT a FROM SeaTransportationPreparePlanPO a ,SeaTransportationPreparePlanItemPO b ,FbaShopProductPO c WHERE a.id=b.seaTransportationPreparePlanId  AND b.fbaShopProductId = c.id ");
		//PageList<SeaTransportationPreparePlanPO> pageList = null;
		List<Object> params = new ArrayList<Object>();
		if (planSearchVO.getShopId() < 0) {
			buffer.append("AND a.shopId IN ( ");
			List<Shop> shops = systemService.getShopsByUserId(operatorId);
			for (int i = 0; i < shops.size(); i++) {
				if (i == shops.size() - 1) {
					buffer.append(shops.get(i).getId() + " ) ");
				} else {
					buffer.append(shops.get(i).getId() + ",");
				}
			}
		} else {
			buffer.append("AND a.shopId = ? ");
			params.add(planSearchVO.getShopId());
		}

		if (planSearchVO.getPrepareType() >= 0) {
			buffer.append("AND a.planType = ? ");
			params.add(planSearchVO.getPrepareType());
		}
		if (planSearchVO.getStatus() >= 0) {
			buffer.append("AND a.status = ? ");
			params.add(planSearchVO.getStatus());
		}

		if (planSearchVO.getSku() != null && !planSearchVO.getSku().trim().equals("")) {
			buffer.append("AND c.sku = ? ");
			params.add(planSearchVO.getSku());
		}
		buffer.append("ORDER BY a.createTime DESC ");
		//System.out.println(buffer.toString());
		//PageList<SeaTransportationPreparePlanPO> pageList = seaTransportationPreparePlanDao.findPageListBySql(planSearchVO.getCurrentPage(), planSearchVO.getPageSize(), sql, where, " create_time DESC ", params);
		PageList<SeaTransportationPreparePlanPO> pageList = seaTransportationPreparePlanDao.findPageListByHql(
				planSearchVO.getCurrentPage(), planSearchVO.getPageSize(), buffer.toString(), params.toArray());

		List<SeaTransportationPlanVO> voList = new ArrayList<SeaTransportationPlanVO>();
		for (SeaTransportationPreparePlanPO po : pageList.getData()) {
			SeaTransportationPlanVO vo = new SeaTransportationPlanVO();
			vo.setCreateDate(po.getCreateTime());
			vo.setDeliveryDate(po.getArriveDate());
			vo.setPlanId(po.getId());
			vo.setPlanName(po.getPlanName());
			vo.setPlanType(po.getPlanType());
			vo.setReceiveDate(po.getArriveDate());
			vo.setRemark(po.getRemark());
			vo.setShopId(po.getShopId());
			vo.setStatus(po.getStatus());
			vo.setStockPeriod(po.getStockPeriod().doubleValue());
			// shopProductCmsInfoDao.findByHql("FROM ShopProductCmsInfoPO
			// a,SeaTransportationPreparePlanItemPO b,FbaShopProduct c WHERE
			// a.cmsProductId = c.cmsProductId AND b.fbaShopProductId=c.id",
			// parameter)
			List<SeaTransportationPreparePlanItemPO> items = seaTransportationPreparePlanItemDao.findListByHql(
					"FROM SeaTransportationPreparePlanItemPO WHERE seaTransportationPreparePlanId = ? AND isCanceled = ? ",
					new Object[] { po.getId(), SeaTransPrepareItemIsCanceled.未取消.getVal() });
			if (items != null) {
				vo.setMskus(items.size());
				int totalProductAmount = 0;
				for (SeaTransportationPreparePlanItemPO itemPO : items) {
					totalProductAmount += itemPO.getActualQuantity();
				}
				vo.setTotalProductAmount(totalProductAmount);
			}
			// vo.setTotalWeight(totalWeight);
			voList.add(vo);
		}
		PageList<SeaTransportationPlanVO> resultList = new PageList<SeaTransportationPlanVO>();
		resultList.setData(voList);
		resultList.setPage(pageList.getPage());
		return resultList;
	}

	@Override
	public SeaTransportationPlanDetailVO saveAndGetRecommendProductList(SeaTransportationPlanVO seaTransportationPlanVO,
			int operatorId) throws BussinessException{
		
		// 查出现有的所有备货计划到货时间，根据店铺id和sku去fba补货订单里面查询
		List<SeaTransportationPreparePlanPO> plans = seaTransportationPreparePlanDao.findListByHql(
				"FROM SeaTransportationPreparePlanPO WHERE status=4 AND shopId = ? AND arriveDate>= ? ",
				new Object[] { seaTransportationPlanVO.getShopId(), DateUtils.getRealDate(new Date()) });
		ArrayList<Date> dates = new ArrayList<Date>();
		dates.add(DateUtils.getRealDate(new Date()));
		for (SeaTransportationPreparePlanPO plan : plans) {
			Date arriveDate = plan.getArriveDate();
			dates.add(arriveDate);
		}
		dates.add(DateUtils.getFullDate(seaTransportationPlanVO.getReceiveDate()));
		// 查出当前店铺所有商品的图片，单重，月销量，sku，是否走过海运，afn总库存，排除海运黑名单的商品
		double monthDiffer = DateUtils.dayDiffer(new Date(), seaTransportationPlanVO.getReceiveDate())*1.0/30;
		List<PreparePlanProductVO> info = seaTransportationPreparePlanDao.getAllProductInfo(seaTransportationPlanVO.getShopId(),monthDiffer,seaTransportationPlanVO.getStockPeriod());
		if(CollectionUtils.isEmpty(info)){
			throw new BussinessException("当前没有可推荐的商品项！");
		}
		double totalWeight = 0;
		int totalProductAmount = 0;
		Date startDate = DateUtils.getRealDate(new Date());
		
		//
		Map<String, List<DateAndOnPassageVO>> hashMap = new HashMap<String,List<DateAndOnPassageVO>>();
		String[] skus = new String[info.size()];
		for (int i = 0; i < skus.length; i++) {
			skus[i]=info.get(i).getSku();
			hashMap.put(skus[i], new ArrayList<DateAndOnPassageVO>());
		}
		//Map<String, Integer> map = replenishmentOrderItemDao.getSeaTransOnPassageQuantity(seaTransportationPlanVO.getShopId(), skus, startDate, dates.get(dates.size()-1));
		
		for (Date endDate : dates) {
			Map<String, Integer> map = replenishmentOrderItemDao.getSeaTransOnPassageQuantity(seaTransportationPlanVO.getShopId(), skus, startDate, endDate);
			for (String sku : hashMap.keySet()) {
				DateAndOnPassageVO onPassageVO = new DateAndOnPassageVO();
				onPassageVO.setDate(endDate);
				onPassageVO.setQuantity(map.get(sku)==null?0:map.get(sku));
				hashMap.get(sku).add(onPassageVO);
			}
		}


		
		//
		Date debug = new Date();// TODO
		for (PreparePlanProductVO vo : info) {
			List<DateAndStockPeriodVO> dateAndStockPeriods = new ArrayList<DateAndStockPeriodVO>();
			vo.setDateAndStockPeriods(dateAndStockPeriods);
			vo.setIsCanceled(SeaTransPrepareItemIsCanceled.未取消.getVal());
			vo.setProductType(vo.getHasSeaTransported()==1?SeaTransportationProductType.已走过海运.getVal():SeaTransportationProductType.程序推荐海运.getVal());
			// available+need
			// =/vo.getMonthSales()*seaTransportationPlanVO.getStockPeriod();
			// 还要减去期间的销量
			int needQuantity = clacNeed(vo.getMonthSales(), seaTransportationPlanVO.getStockPeriod(), vo.getAfnTotalQuantity(), startDate, seaTransportationPlanVO.getReceiveDate());
			vo.setPrepareQuantity(needQuantity);
			vo.setActualQuantity(needQuantity);
			vo.setTotalWeight(vo.getUnitWeight() * needQuantity);
			totalWeight += vo.getUnitWeight() * needQuantity;
			totalProductAmount += needQuantity;
			//显示库存取实时stockPeriodVO.setStockPeriod(seaTransportationPlanVO.getStockPeriod());
			// 拿到可用库存+空运在途之后计算
			// 此次计划到货日期前该sku所有的海运在途批次
			int seaTransOnPass = 0;
			double available = 0;//当天的空运+在库库存
			/*for (int i = dates.size()-1; i >=0 ; i--) {
				DateAndStockPeriodVO stockPeriodVO = new DateAndStockPeriodVO();
				stockPeriodVO.setDate(dates.get(i));
				double dayDiffer = DateUtils.dayDiffer(dates.get(i), startDate);
				if (i == dates.size() - 1) {
					seaTransOnPass = replenishmentOrderItemDao.getSeaTransOnPassageQuantity(seaTransportationPlanVO.getShopId(), vo.getSku(), startDate, dates.get(i));
					//当天的空运+海运库存
					available = new Double(vo.getAfnTotalQuantity() - seaTransOnPass);
					stockPeriodVO.setStockPeriod((available+seaTransOnPass+vo.getActualQuantity()) / vo.getMonthSales() - dayDiffer / 30);
				}else{
					int quantity = replenishmentOrderItemDao.getSeaTransOnPassageQuantity(seaTransportationPlanVO.getShopId(), vo.getSku(), startDate, dates.get(i));
					// （可用库存+到货当天以及之前到货库存）/月销量 - （到货当天时间-今天）/30；
					stockPeriodVO.setStockPeriod((available+quantity) / vo.getMonthSales() - dayDiffer / 30);
				}
				
				vo.getDateAndStockPeriods().add(stockPeriodVO);
			}*/
			List<DateAndOnPassageVO> list = hashMap.get(vo.getSku());
			for (int i = list.size()-1; i >=0 ; i--) {
				DateAndStockPeriodVO stockPeriodVO = new DateAndStockPeriodVO();
				stockPeriodVO.setDate(list.get(i).getDate());
				double dayDiffer = DateUtils.dayDiffer(dates.get(i), startDate);
				if(i == dates.size() - 1){
					seaTransOnPass = list.get(i).getQuantity();
					//当天的空运+海运库存
					available = vo.getAfnTotalQuantity() - seaTransOnPass;
					stockPeriodVO.setStockPeriod((available+seaTransOnPass+vo.getActualQuantity()) / vo.getMonthSales() - dayDiffer / 30);
				}else{
					stockPeriodVO.setStockPeriod((available+list.get(i).getQuantity()) / vo.getMonthSales() - dayDiffer / 30);
				}
				// （可用库存+到货当天以及之前到货库存）/月销量 - （到货当天时间-今天）/30；
				vo.getDateAndStockPeriods().add(stockPeriodVO);
			}
			vo.setAmAvailableStock(vo.getAfnTotalQuantity() - seaTransOnPass);
		}
		System.out.println((new Date().getTime()-debug.getTime())/1000);// TODO

		seaTransportationPlanVO.setStatus(SeaTransportationPrepareStatus.推荐.getVal());
		seaTransportationPlanVO.setMskus(info.size());
		seaTransportationPlanVO.setCreateDate(new Date());
		seaTransportationPlanVO.setTotalProductAmount(totalProductAmount);
		seaTransportationPlanVO.setTotalWeight(totalWeight);
		SeaTransportationPlanDetailVO detailVO = new SeaTransportationPlanDetailVO();
		detailVO.setPreparePlanProductList(info);
		detailVO.setSeaTransportationPlanVO(seaTransportationPlanVO);

		// 将计划持久到数据库
		SeaTransportationPreparePlanPO planPO = new SeaTransportationPreparePlanPO();
		planVoToPo(seaTransportationPlanVO, planPO);
		Integer planId = seaTransportationPreparePlanDao.addObject(planPO);
		seaTransportationPlanVO.setPlanId(planId);
		// 将计划商品项持久化
		List<SeaTransportationPreparePlanItemPO> itemList = new ArrayList<SeaTransportationPreparePlanItemPO>();
		for (PreparePlanProductVO itemVo : info) {
			SeaTransportationPreparePlanItemPO itemPO = new SeaTransportationPreparePlanItemPO();
			itemVoToPo(itemVo, itemPO, planId);
			itemPO.setProductType(itemVo.getProductType());
			itemList.add(itemPO);
		}
		seaTransportationPreparePlanItemDao.addObject(itemList);
		addPlanOperateLog("创建了海运备货计划", operatorId, planId);
		ObjectMapper mapper = new ObjectMapper();
		String string =null;
		try {
			string = mapper.writeValueAsString(detailVO);
			System.out.println(string);
		} catch (Exception e) {
			
		}
		return detailVO;
	}

	@Override
	public void addSeaTransportationPlan(SeaTransportationPlanDetailVO seaTransportationPlanDetail, int operatorId) {
		SeaTransportationPlanVO planVO = seaTransportationPlanDetail.getSeaTransportationPlanVO();
		planVO.setPlanName(planVO.getPlanName() + "-SPLIT");
		List<PreparePlanProductVO> productListVo = seaTransportationPlanDetail.getPreparePlanProductList();
		if(productListVo.size()<1){
			throw new BussinessException("请给拆分的计划添加商品项");
		}
		
		SeaTransportationPreparePlanPO planPO = new SeaTransportationPreparePlanPO();
		this.planVoToPo(planVO, planPO);
		Integer newPlanId = seaTransportationPreparePlanDao.addObject(planPO);
		for (PreparePlanProductVO itemVo : productListVo) {
			seaTransportationPreparePlanItemDao.updateItemsByPlanId(planVO.getPlanId(), newPlanId,
					itemVo.getFbaShopProductId());
		}
		addPlanOperateLog("将海运备货计划拆分了，此次共拆出了"+productListVo.size()+"个商品", operatorId, planVO.getPlanId());
		addPlanOperateLog("拆分海运备货计划生成", operatorId, newPlanId);
	}

	@Override
	public void updateSeaTransportationPlan(SeaTransportationPlanVO seaTransportationPlanVO, int operatorId) {
		SeaTransportationPreparePlanPO planPO = seaTransportationPreparePlanDao.getObject(seaTransportationPlanVO.getPlanId());
		planPO.setLastUpdateTime(new Date());
		if(!seaTransportationPlanVO.getPlanName().equals(planPO.getPlanName())){
			addPlanOperateLog("将海运备货计划名由"+planPO.getPlanName()+"修改为："+seaTransportationPlanVO.getPlanName(), operatorId, planPO.getId());
			planPO.setPlanName(seaTransportationPlanVO.getPlanName());
		}
		planPO.setRemark(seaTransportationPlanVO.getRemark());
		if(seaTransportationPlanVO.getPlanType()!=planPO.getPlanType()){
			addPlanOperateLog("将海运备货计划类型由"+SeaTransportationPrepareType.getName(planPO.getPlanType())+"修改为"+SeaTransportationPrepareType.getName(seaTransportationPlanVO.getPlanType()), operatorId, planPO.getId());
			planPO.setPlanType(seaTransportationPlanVO.getPlanType());
		}
		seaTransportationPreparePlanDao.updateObject(planPO);
	}

	@Override
	public void addProduct(List<String> skus, int planId, int operatorId) {
		SeaTransportationPreparePlanPO planPO = seaTransportationPreparePlanDao.getObject(planId);
		for (String sku : skus) {
			FbaShopProductPO productPO = fbaShopProductDao.findByHql("FROM FbaShopProductPO WHERE shopId = ? AND sku = ?", new Object[] { planPO.getShopId(), sku });
			ShopProductCmsInfoPO cmsInfoPO = shopProductCmsInfoDao.findByHql("FROM ShopProductCmsInfoPO WHERE cmsProductId = ?", productPO.getCmsProductId());
			// 添加商品的时候算出对应的属性
			SeaTransportationPreparePlanItemPO itemPO = new SeaTransportationPreparePlanItemPO();
			AmazonStockReportPO stockReportPO = amazonStockReportDao.findByHql(
					"FROM AmazonStockReportPO WHERE shopId = ? AND sku = ?", new Object[] { planPO.getShopId(), sku });
			
			int fabTotalStock = 0;
			if(stockReportPO!=null){
				fabTotalStock = stockReportPO.getAfnTotalQuantity();
			}

			int needQuantity = clacNeed(productPO.getMonthSales(), planPO.getStockPeriod(), fabTotalStock, new Date(), planPO.getArriveDate());
			itemPO.setActualQuantity(needQuantity);
			itemPO.setFbaShopProductId(productPO.getId());
			itemPO.setIsCanceled(SeaTransPrepareItemIsCanceled.未取消.getVal());
			itemPO.setPrepareQuantity(needQuantity);
			itemPO.setProductType(SeaTransportationProductType.人工加入海运.getVal());
			itemPO.setSeaTransportationPreparePlanId(planId);
			itemPO.setTotalWeight(cmsInfoPO.getUnitWeight()*needQuantity);
			seaTransportationPreparePlanItemDao.addObject(itemPO);
			addPlanOperateLog("添加了商品sku:" + sku, operatorId, planId);
		}
	}

	@Override
	public SeaTransportationPlanDetailVO getSeaTransportationPlanDetailByPlanId(int planId) {
		SeaTransportationPreparePlanPO planPO = seaTransportationPreparePlanDao.getObject(planId);
		SeaTransportationPlanVO planVO = new SeaTransportationPlanVO();
		planVO.setCreateDate(planPO.getCreateTime());
		planVO.setDeliveryDate(planPO.getShippingDate());
		planVO.setPlanId(planId);
		planVO.setPlanName(planPO.getPlanName());
		planVO.setPlanType(planPO.getPlanType());
		planVO.setReceiveDate(planPO.getArriveDate());
		planVO.setRemark(planPO.getRemark());
		planVO.setShopId(planPO.getShopId());
		planVO.setStatus(planPO.getStatus());
		planVO.setStockPeriod(planPO.getStockPeriod());
		Date startDate = DateUtils.getRealDate(new Date());
		int mskus = 0;
		double totalWeight = 0;
		int totalProductAmount = 0;
		List<SeaTransportationPreparePlanPO> plans = seaTransportationPreparePlanDao.findListByHql(
				"FROM SeaTransportationPreparePlanPO WHERE status=4 AND shopId = ? AND arriveDate>= ? AND arriveDate<=? ORDER BY arriveDate ASC ",
				new Object[] { planPO.getShopId(), startDate,DateUtils.getFullDate(planPO.getArriveDate()) });
		List<PreparePlanProductVO> productInfo = seaTransportationPreparePlanDao.getProductInfoByPlanId(planId);
		
		
		ArrayList<Date> dates = new ArrayList<Date>();
		dates.add(startDate);
		for (SeaTransportationPreparePlanPO plan : plans) {
			Date arriveDate = plan.getArriveDate();
			dates.add(arriveDate);
		}
		if(planPO.getStatus()!=4){
			dates.add(planPO.getArriveDate());
		}
		
		Map<String, List<DateAndOnPassageVO>> hashMap = new HashMap<String,List<DateAndOnPassageVO>>();
		String[] skus = new String[productInfo.size()];
		for (int i = 0; i < skus.length; i++) {
			skus[i]=productInfo.get(i).getSku();
			hashMap.put(skus[i], new ArrayList<DateAndOnPassageVO>());
		}
		Date debug = new Date();// TODO
		for (Date endDate : dates) {
			Map<String, Integer> map = replenishmentOrderItemDao.getSeaTransOnPassageQuantity(planPO.getShopId(), skus, startDate, endDate);
			for (String sku : hashMap.keySet()) {
				DateAndOnPassageVO onPassageVO = new DateAndOnPassageVO();
				onPassageVO.setDate(endDate);
				onPassageVO.setQuantity(map.get(sku)==null?0:map.get(sku));
				hashMap.get(sku).add(onPassageVO);
			}
		}
		// TODO
		System.out.println((new Date().getTime()-debug.getTime())/1000);
		for (PreparePlanProductVO productVO : productInfo) {
			List<DateAndStockPeriodVO> dateAndStockPeriods = new ArrayList<DateAndStockPeriodVO>();
			productVO.setDateAndStockPeriods(dateAndStockPeriods);
			int seaTransOnPass = 0;
			double available = 0;//当天的空运+在库库存
			List<DateAndOnPassageVO> list = hashMap.get(productVO.getSku());
			for (int i = list.size()-1; i >=0 ; i--) {
				DateAndStockPeriodVO stockPeriodVO = new DateAndStockPeriodVO();
				stockPeriodVO.setDate(list.get(i).getDate());
				double dayDiffer = DateUtils.dayDiffer(dates.get(i), startDate);
				if(i == dates.size() - 1){
					seaTransOnPass = list.get(i).getQuantity();
					//当天的空运+海运库存
					available = productVO.getAfnTotalQuantity() - seaTransOnPass;
					stockPeriodVO.setStockPeriod((available+seaTransOnPass+productVO.getActualQuantity()) / productVO.getMonthSales() - dayDiffer / 30);
				}else{
					stockPeriodVO.setStockPeriod((available+list.get(i).getQuantity()) / productVO.getMonthSales() - dayDiffer / 30);
				}
				// （可用库存+到货当天以及之前到货库存）/月销量 - （到货当天时间-今天）/30；
				productVO.getDateAndStockPeriods().add(stockPeriodVO);
			}
			productVO.setAmAvailableStock(productVO.getAfnTotalQuantity() - seaTransOnPass);
			productVO.setDateAndStockPeriods(dateAndStockPeriods);
		}
		
		planVO.setMskus(mskus);
		planVO.setTotalProductAmount(totalProductAmount);
		planVO.setTotalWeight(totalWeight);
		
		SeaTransportationPlanDetailVO detailVO = new SeaTransportationPlanDetailVO();
		detailVO.setSeaTransportationPlanVO(planVO);
		detailVO.setPreparePlanProductList(productInfo);
		ObjectMapper mapper = new ObjectMapper();
		try {
			String string = mapper.writeValueAsString(detailVO);
			System.out.println(string);
		} catch (Exception e) {
		}
		return detailVO;
	}

	@Override
	public void cancelProducts(int planId,List<String> skus, int operatorId) throws BussinessException {
			SeaTransportationPreparePlanPO planPO = seaTransportationPreparePlanDao.getObject(planId);
			seaTransportationPreparePlanItemDao.changeItemsStatusByPlanId(planPO.getId(),planPO.getShopId(), skus, SeaTransPrepareItemIsCanceled.取消.getVal());
			addPlanOperateLog("取消了商品sku:" + skus, operatorId, planId);
	}

	@Override
	public void updateProductActualQuantity(int planId, String sku, int actualQuantity, int operatorId) {
		SeaTransportationPreparePlanPO planPO = seaTransportationPreparePlanDao.getObject(planId);
		FbaShopProductPO shopProductPO = fbaShopProductDao.findByHql("FROM FbaShopProductPO WHERE shopId=? AND sku=? ", new Object[]{planPO.getShopId(),sku});
		SeaTransportationPreparePlanItemPO itemPO = seaTransportationPreparePlanItemDao.findByHql("FROM SeaTransportationPreparePlanItemPO WHERE seaTransportationPreparePlanId=? AND fbaShopProductId= ?", new Object[]{planId,shopProductPO.getId()});
		Integer old = itemPO.getActualQuantity();
		itemPO.setActualQuantity(actualQuantity);
		ShopProductCmsInfoPO cmsInfoPO = shopProductCmsInfoDao.findByHql("SELECT b FROM FbaShopProductPO a, ShopProductCmsInfoPO b WHERE a.cmsProductId= b.cmsProductId AND a.shopId=? AND a.sku=? ", new Object[]{planPO.getShopId(),sku});
		itemPO.setTotalWeight(actualQuantity*cmsInfoPO.getUnitWeight());
		seaTransportationPreparePlanItemDao.updateObject(itemPO);
		addPlanOperateLog("将"+sku+"批量由"+old+"修改到"+actualQuantity, operatorId, planId);
	}

	@Override
	public void updateCheckStatus(int planId, int operatorId, int status) {
		SeaTransportationPreparePlanPO planPO = seaTransportationPreparePlanDao.getObject(planId);
		planPO.setStatus(status);
		seaTransportationPreparePlanDao.updateObject(planPO);
		addPlanOperateLog("将海运备货计划状态修改为："+SeaTransportationPrepareStatus.getName(status), operatorId, planId);
	}

	@Override
	public List<SeaTransportationPlanOperateLogVO> getSeaTransportationPlanOperateLogs(int planId) {
		List<SeaTransportationPlanOperateLogVO> logs = new ArrayList<SeaTransportationPlanOperateLogVO>();
		List<SeaTransportationPreparePlanOperateLogPO> list = seaTransportationPreparePlanOperateLogDao.findListByHql("FROM SeaTransportationPreparePlanOperateLogPO WHERE seaTransportationPreparePlanId = ? ", planId);
		for (SeaTransportationPreparePlanOperateLogPO logPO : list) {
			SeaTransportationPlanOperateLogVO logVO = new SeaTransportationPlanOperateLogVO();
			logVO.setComment(logPO.getComment());
			logVO.setDate(logPO.getOperateDate());
			UserPO userPO = userDAO.getObject(logPO.getOperatorId());
			logVO.setOperator(userPO.getName());
			logs.add(logVO);
		}
		
		return logs;
	}

	@Override
	public void addSeaTransToInboundPlanLog(int seaTransPlanId, int inboundPlanId, int operatorId) {
		SeaTransportationPreparePlanPO stPlanPO = seaTransportationPreparePlanDao.getObject(seaTransPlanId);
		FbaReplenishmentPlanOperateLogPO fbaLogPO = new FbaReplenishmentPlanOperateLogPO();
		fbaLogPO.setComment("由海运备货计划："+stPlanPO.getPlanName()+" 生成了fba补货计划");
		fbaLogPO.setOperateTime(new Date());
		fbaLogPO.setOperatorId(operatorId);
		fbaLogPO.setReplenishmentPlanId(inboundPlanId);
		fbaReplenishmentPlanOperateLogDao.addObject(fbaLogPO);
		FbaReplenishmentPlanPO replenishmentPlanPO = fbaReplenishmentPlanDao.getObject(inboundPlanId);
		addPlanOperateLog("已经生成了fba补货计划："+replenishmentPlanPO.getPlanName(), operatorId, seaTransPlanId);
		updateCheckStatus(seaTransPlanId, operatorId, SeaTransportationPrepareStatus.已建单.getVal());
	}

	/**
	 * 添加日志
	 * 
	 * @param comment
	 * @param operatorId
	 * @param planId
	 */
	protected void addPlanOperateLog(String comment, int operatorId, int planId) {
		SeaTransportationPreparePlanOperateLogPO logPO = new SeaTransportationPreparePlanOperateLogPO();
		logPO.setOperateDate(new Date());
		logPO.setOperatorId(operatorId);
		logPO.setSeaTransportationPreparePlanId(planId);
		logPO.setComment(comment);
		seaTransportationPreparePlanOperateLogDao.addObject(logPO);
	}

	/**
	 * 将计划vo属性复制到po
	 * 
	 * @param vo
	 * @param po
	 */
	protected void planVoToPo(SeaTransportationPlanVO vo, SeaTransportationPreparePlanPO po) {
		po.setArriveDate(vo.getReceiveDate());
		if (po.getCreateTime() == null) {
			po.setCreateTime(new Date());
		}
		po.setLastUpdateTime(new Date());
		po.setPlanName(vo.getPlanName());
		po.setPlanType(vo.getPlanType());
		po.setRemark(vo.getRemark());
		po.setShippingDate(vo.getDeliveryDate());
		po.setShopId(vo.getShopId());
		po.setStatus(vo.getStatus());
		po.setStockPeriod(vo.getStockPeriod());
	}

	/**
	 * 将计划商品项vo属性复制到po
	 * 
	 * @param vo
	 * @param po
	 * @param planId
	 */
	protected void itemVoToPo(PreparePlanProductVO vo, SeaTransportationPreparePlanItemPO po, int planId) {
		po.setActualQuantity(vo.getActualQuantity());
		po.setFbaShopProductId(vo.getFbaShopProductId());
		po.setIsCanceled(vo.getIsCanceled());
		po.setPrepareQuantity(vo.getPrepareQuantity());
		po.setProductType(vo.getProductType());
		po.setSeaTransportationPreparePlanId(planId);
		po.setTotalWeight(vo.getTotalWeight());
	}

	protected int clacNeed(int monthSale,double stockPeriod,int fabTotalStock,Date startDate ,Date receiveDate){
		int quantity = (int) Math.ceil(monthSale* (stockPeriod
				+ (new Double(DateUtils.dayDiffer(startDate,receiveDate)) / 30))
				- fabTotalStock);
		return quantity;
	}
	
}