package com.itecheasy.core.fba;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.itecheasy.core.order.OrderTracking;
import com.itecheasy.core.order.dao.OrderTrackingDao;
import com.itecheasy.core.po.*;
import com.itecheasy.printMethod.AccessPath;
import com.itecheasy.webservice.amazon.*;
import org.apache.commons.lang.time.DateUtils;

import com.itecheasy.common.PageList;
import com.itecheasy.common.Param;
import com.itecheasy.common.util.BeanUtils;
import com.itecheasy.common.util.CalcUtils;
import com.itecheasy.common.util.CollectionUtils;
import com.itecheasy.common.util.StringUtils;
import com.itecheasy.core.BussinessException;
import com.itecheasy.core.fba.FbaInboundService.FbaInboundPlanSubmitStatus;
import com.itecheasy.core.fba.dao.FbaFromAddressDao;
import com.itecheasy.core.fba.dao.FbaFromAddressSnapshotDao;
import com.itecheasy.core.fba.dao.FbaReplenishmentPlanDao;
import com.itecheasy.core.fba.dao.FbaReplenishmentPlanItemDao;
import com.itecheasy.core.fba.dao.FbaReplenishmentPlanOperateLogDao;
import com.itecheasy.core.fba.dao.FbaShopProductDao;
import com.itecheasy.core.fba.dao.ReplenishmentOrderDao;
import com.itecheasy.core.fba.dao.ReplenishmentOrderItemDao;
import com.itecheasy.core.fba.dao.ReplenishmentOrderOperateLogDao;
import com.itecheasy.core.fba.dao.ReplenishmentOrderRepositoryDao;
import com.itecheasy.core.fba.dao.ShopProductCmsInfoDao;
import com.itecheasy.core.order.IBaseOrder;
import com.itecheasy.core.order.OrderService.OrderProductStatus;
import com.itecheasy.core.order.OrderService.OrderStatus;
import com.itecheasy.core.system.Merchandiser;
import com.itecheasy.core.system.Shop;
import com.itecheasy.core.system.ShopInfo;
import com.itecheasy.core.system.SystemService;
import com.itecheasy.core.system.dao.CountryDao;
import com.itecheasy.core.system.dao.ShippingMethodDao;
import com.itecheasy.core.user.dao.DepartmentDAO;
import com.itecheasy.core.user.dao.UserDAO;
import com.itecheasy.core.util.DictUtils;
import com.itecheasy.webservice.client.AmazonClient;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * @author taozihao
 * @date 2018年6月7日 下午12:47:57
 * @description
 */
public class FbaInboundServiceImpl implements FbaInboundService {

	public static final Logger logger = Logger.getLogger(FbaInboundServiceImpl.class);

	private FbaFromAddressDao fbaFromAddressDao;
	//private FbaToAddressDao fbaToAddressDao;
	private FbaShopProductDao fbaShopProductDao;
	private ShopProductCmsInfoDao shopProductCmsInfoDao;
	private FbaReplenishmentPlanDao fbaReplenishmentPlanDao;
	private FbaReplenishmentPlanItemDao fbaReplenishmentPlanItemDao;
	private ReplenishmentOrderDao replenishmentOrderDao;
	private ReplenishmentOrderItemDao replenishmentOrderItemDao;
	private FbaFromAddressSnapshotDao fbaFromAddressSnapshotDao;
	private SystemService systemService;
	private FbaReplenishmentPlanOperateLogDao fbaReplenishmentPlanOperateLogDao;
	private UserDAO userDAO;
	private ShippingMethodDao shippingMethodDao;
	private CountryDao countryDao;
	private ReplenishmentOrderRepositoryDao replenishmentOrderRepositoryDao;
	private ReplenishmentOrderOperateLogDao replenishmentOrderOperateLogDao;
	private DepartmentDAO departmentDAO;
	private OrderTrackingDao orderTrackingDao;

	public void setOrderTrackingDao(OrderTrackingDao orderTrackingDao) {
		this.orderTrackingDao = orderTrackingDao;
	}

	public void setDepartmentDAO(DepartmentDAO departmentDAO) {
		this.departmentDAO = departmentDAO;
	}

	public void setReplenishmentOrderOperateLogDao(ReplenishmentOrderOperateLogDao replenishmentOrderOperateLogDao) {
		this.replenishmentOrderOperateLogDao = replenishmentOrderOperateLogDao;
	}

	public void setReplenishmentOrderRepositoryDao(ReplenishmentOrderRepositoryDao replenishmentOrderRepositoryDao) {
		this.replenishmentOrderRepositoryDao = replenishmentOrderRepositoryDao;
	}

	public void setCountryDao(CountryDao countryDao) {
		this.countryDao = countryDao;
	}

	public void setShippingMethodDao(ShippingMethodDao shippingMethodDao) {
		this.shippingMethodDao = shippingMethodDao;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public void setFbaFromAddressDao(FbaFromAddressDao fbaFromAddressDao) {
		this.fbaFromAddressDao = fbaFromAddressDao;
	}

	public void setFbaShopProductDao(FbaShopProductDao fbaShopProductDao) {
		this.fbaShopProductDao = fbaShopProductDao;
	}

	//public void setFbaToAddressDao(FbaToAddressDao fbaToAddressDao) {
	//	this.fbaToAddressDao = fbaToAddressDao;
	//}

	public void setShopProductCmsInfoDao(ShopProductCmsInfoDao shopProductCmsInfoDao) {
		this.shopProductCmsInfoDao = shopProductCmsInfoDao;
	}

	public void setFbaReplenishmentPlanDao(FbaReplenishmentPlanDao fbaReplenishmentPlanDao) {
		this.fbaReplenishmentPlanDao = fbaReplenishmentPlanDao;
	}

	public void setFbaReplenishmentPlanItemDao(FbaReplenishmentPlanItemDao fbaReplenishmentPlanItemDao) {
		this.fbaReplenishmentPlanItemDao = fbaReplenishmentPlanItemDao;
	}

	public void setReplenishmentOrderDao(ReplenishmentOrderDao replenishmentOrderDao) {
		this.replenishmentOrderDao = replenishmentOrderDao;
	}
	
	public void setReplenishmentOrderItemDao(ReplenishmentOrderItemDao replenishmentOrderItemDao) {
		this.replenishmentOrderItemDao = replenishmentOrderItemDao;
	}
	
	public void setFbaFromAddressSnapshotDao(FbaFromAddressSnapshotDao fbaFromAddressSnapshotDao) {
		this.fbaFromAddressSnapshotDao = fbaFromAddressSnapshotDao;
	}

	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	public void setFbaReplenishmentPlanOperateLogDao(FbaReplenishmentPlanOperateLogDao fbaReplenishmentPlanOperateLogDao) {
		this.fbaReplenishmentPlanOperateLogDao = fbaReplenishmentPlanOperateLogDao;
	}

	@Override
	public String checkExcel(int shopId ,List<String> skus) {
		StringBuffer buffer = new StringBuffer();
		//首先对fba商品和cms里面联合查询。判断sku是否可用,判断是否有条形码名称和条形码以及状态是否为可用 
		for (String sku : skus) {
			FbaShopProductPO productPO = fbaShopProductDao.findByHql("FROM FbaShopProductPO WHERE fbaBarcodeName is not null AND fbaBarcodeSku is not null AND status='1' AND shopId = ? AND sku = ?",new Object[]{shopId,sku});
			if(productPO==null){
				buffer.append(sku);
				//每个问题sku中间采用英文逗号分隔
				buffer.append(",");
			}else{
				if(productPO.getFbaBarcodeName().trim().equals("")||productPO.getFbaBarcodeSku().trim().equals("")){
					buffer.append(sku);
					buffer.append(",");
				}
			}
		}
		if(buffer.toString().trim().equals("")){
			return null;
		}
		return buffer.toString();
	}


	@Override
	public List<FbaInboundPlanItemExcelResult> showExcelProductResult(int shopId ,List<InboundPlanProductItem> items) {
		List<FbaInboundPlanItemExcelResult> list = new ArrayList<FbaInboundPlanItemExcelResult>();
		for (InboundPlanProductItem item : items) {
			ShopProductCmsInfoPO cmsInfoPO = shopProductCmsInfoDao.findByHql("SELECT p FROM ShopProductCmsInfoPO p, FbaShopProductPO s WHERE p.cmsProductId = s.cmsProductId AND s.shopId = ? AND s.sku = ?",new Object[]{shopId,item.getSku()});
			FbaInboundPlanItemExcelResult excelResult = new FbaInboundPlanItemExcelResult();
			excelResult.setCmsProductCode(cmsInfoPO.getCmsProductCode());
			excelResult.setOrderedQuantity(item.getQuantity());
			excelResult.setSku(item.getSku());
			excelResult.setPrimaryPictureCode(cmsInfoPO.getPrimaryPictureCode());
			excelResult.setUnit(cmsInfoPO.getUnit());
			excelResult.setUnitQuantity(cmsInfoPO.getUnitQuantity());
			list.add(excelResult);
		}
		return list;
	}

	@Override
	public int createFbaInboundPlan(int shopId, String planName, String shipToCountryCode,
			String shippingMethod, List<InboundPlanProductItem> items,int isSubmitToAm,int operatorId) throws BussinessException{
		
		//由于店铺绑定了默认的退货地址，直接去查
		FbaFromAddress address = fbaFromAddressDao.findFbaFromAddressByShopId(shopId);
		//这一块如果查出来的结果为空则需要抛异常
		if(address==null){
			throw new BussinessException("店铺地址不能为空！");
		}
		
		//获取商品总数
		int amount = 0;
		for (InboundPlanProductItem item : items) {
			amount = amount + item.getQuantity();
		}
		
		FbaReplenishmentPlanPO planPO = new FbaReplenishmentPlanPO();
		planPO.setIsSubmitToAm(isSubmitToAm);
		planPO.setAmount(amount);
		planPO.setCreateTime(new Date());
		planPO.setMskus(items.size());
		planPO.setPlanName(planName);
		planPO.setShippingMethod(shippingMethod);
		planPO.setShipToCountryCode(shipToCountryCode);
		planPO.setShopId(shopId);
		//设置未提交状态
		planPO.setSubmitStatus(FbaInboundPlanSubmitStatus.未提交.getVal());
		//添加计划,并返回计划id
		Integer planId = fbaReplenishmentPlanDao.addObject(planPO);
		//给补货计划退货地址添加快照，后续提交给亚马逊的用快照里面的
		FbaFromAddressSnapshotPO addressSnapshotPO = new FbaFromAddressSnapshotPO();
		BeanUtils.copyProperties(address, addressSnapshotPO);
		addressSnapshotPO.setFbaReplenishmentPlanId(planId);
		fbaFromAddressSnapshotDao.addObject(addressSnapshotPO);
		//添加计划商品项
		List<FbaReplenishmentPlanItemPO> list = new ArrayList<FbaReplenishmentPlanItemPO>();
		for (InboundPlanProductItem item : items) {
			FbaShopProductPO productPO = fbaShopProductDao.findByHql("FROM FbaShopProductPO WHERE shopId= ? AND sku = ?",new Object[]{shopId,item.getSku()} );
			FbaReplenishmentPlanItemPO planItemPO = new FbaReplenishmentPlanItemPO();
			//关联计划id
			planItemPO.setFbaReplenishmentPlanId(planId);
			//设置关联商品的id
			planItemPO.setFbaShopProductId(productPO.getId());
			planItemPO.setQuantity(item.getQuantity());
			list.add(planItemPO);
		}
		
		fbaReplenishmentPlanItemDao.addObject(list);
		addPlanOperateLog("创建了fba补货计划，未提交", operatorId, planId);
		return planId;
	}

	public void addPlanOperateLog(String comment, int operatorId, int planId){
		FbaReplenishmentPlanOperateLogPO logPO = new FbaReplenishmentPlanOperateLogPO();
		logPO.setOperateTime(new Date());
		logPO.setOperatorId(operatorId);
		logPO.setReplenishmentPlanId(planId);
		logPO.setComment(comment);
		fbaReplenishmentPlanOperateLogDao.addObject(logPO);
	}
	
	public void addOrderOperateLog(String comment, String operator, int objectId) {
		ReplenishmentOrderOperateLogPO po = new ReplenishmentOrderOperateLogPO();
		po.setOperator(operator);
		po.setComment(comment);
		po.setObjectId(objectId);
		po.setOperateDate(new Date());
		replenishmentOrderOperateLogDao.addObject(po);
	}
	
	@Override
	public void updateFbaInboundPlan(int planId, String planName, String shipToCountryCode, String shippingMethod,
			List<InboundPlanProductItem> items,int operatorId) {
		//首先根据计划id查询计划
		FbaReplenishmentPlanPO replenishmentPlanPO = fbaReplenishmentPlanDao.getObject(planId);
		//只有未提交状态的计划才能修改
		//if(!replenishmentPlanPO.getSubmitStatus().equals("0")){
		//	throw new BussinessException("只有未提交状态的计划才能修改");
		//}
		replenishmentPlanPO.setPlanName(planName);
		replenishmentPlanPO.setLastUpdateTime(new Date());
		replenishmentPlanPO.setShippingMethod(shippingMethod);
		replenishmentPlanPO.setShipToCountryCode(shipToCountryCode);
		replenishmentPlanPO.setMskus(items.size());
		//获取商品总数
		int amount = 0;
		for (InboundPlanProductItem item : items) {
			amount = amount + item.getQuantity();
		}
		replenishmentPlanPO.setAmount(amount);
		
		//接着删除之前关联的商品项 
		List<FbaReplenishmentPlanItemPO> list = fbaReplenishmentPlanItemDao.findListByHql("FROM FbaReplenishmentPlanItemPO WHERE fbaReplenishmentPlanId = ?", replenishmentPlanPO.getId());
		fbaReplenishmentPlanItemDao.deleteObjects(list);
		//再重新插入商品项信息
		List<FbaReplenishmentPlanItemPO> itemList = new ArrayList<FbaReplenishmentPlanItemPO>();
		for (InboundPlanProductItem item : items) {
			FbaShopProductPO productPO = fbaShopProductDao.findByHql("FROM FbaShopProductPO WHERE shopId= ? AND sku = ?",new Object[]{replenishmentPlanPO.getShopId(),item.getSku()});
			FbaReplenishmentPlanItemPO planItemPO = new FbaReplenishmentPlanItemPO();
			//关联计划id
			planItemPO.setFbaReplenishmentPlanId(planId);
			//设置关联商品的id
			planItemPO.setFbaShopProductId(productPO.getId());
			planItemPO.setQuantity(item.getQuantity());
			itemList.add(planItemPO);
		}
		fbaReplenishmentPlanDao.addObject(replenishmentPlanPO);
		fbaReplenishmentPlanItemDao.addObject(itemList);
		addPlanOperateLog("修改了fba补货计划，未提交", operatorId, planId);
	}



	@Override
	public void cancelFbaInboundPlan(int planId,int operatorId) throws BussinessException{
		FbaReplenishmentPlanPO replenishmentPlanPO = fbaReplenishmentPlanDao.getObject(planId);
		if(replenishmentPlanPO.getSubmitStatus().equals(FbaInboundPlanSubmitStatus.未提交.getVal())){
		replenishmentPlanPO.setSubmitStatus(FbaInboundPlanSubmitStatus.已删除.getVal());
		replenishmentPlanPO.setLastUpdateTime(new Date());
		fbaReplenishmentPlanDao.updateObject(replenishmentPlanPO);
		addPlanOperateLog("删除了fba补货计划", operatorId, planId);
		}else{
			throw new BussinessException("只有未提交状态的计划才能删除");
		}
	}



	@Override
	public List<FbaPreInboundOrderVO> submitFbaInboundPlan(int planId, int shopId, int operatorId) throws BussinessException{
		
		//查询计划，
		FbaReplenishmentPlanPO planPO = fbaReplenishmentPlanDao.findByHql("FROM FbaReplenishmentPlanPO WHERE shopId=? AND id=?", new Object[]{shopId,planId});
		//为了防止重复提交亚马逊，如果发现状态不对就抛出异常
		if(!planPO.getSubmitStatus().equals(FbaInboundPlanSubmitStatus.未提交.getVal())){
			throw new BussinessException("计划:"+planPO.getPlanName()+"已经提交或者删除");
		}
		
		List<InboundItemVO> list = new ArrayList<InboundItemVO>();
		//查询计划商品项
		List<FbaReplenishmentPlanItemPO> itemList = fbaReplenishmentPlanItemDao.findListByHql("FROM FbaReplenishmentPlanItemPO WHERE fbaReplenishmentPlanId = ?", planPO.getId());
		
		for (FbaReplenishmentPlanItemPO fbaReplenishmentPlanItemPO : itemList) {
			InboundItemVO itemVO = new InboundItemVO();
			itemVO.setQuantity(fbaReplenishmentPlanItemPO.getQuantity());
			FbaShopProductPO productPO = fbaShopProductDao.getObject(fbaReplenishmentPlanItemPO.getFbaShopProductId());
			itemVO.setSellerSKU(productPO.getSku());
			list.add(itemVO);
		}
		
		
		AddressVO shipFromAddress = BeanUtils.copyProperties(fbaFromAddressSnapshotDao.findByHql("FROM FbaFromAddressSnapshotPO WHERE fbaReplenishmentPlanId = ?", planId), AddressVO.class);
		//业务规则 将省和市拼在一起提交，省置为空
		shipFromAddress.setCity(shipFromAddress.getCity()+","+shipFromAddress.getStateOrProvinceCode());
		shipFromAddress.setStateOrProvinceCode(null);
		String shipToCountryCode = null;
		if(!planPO.getShipToCountryCode().equalsIgnoreCase("AU")&&!planPO.getShipToCountryCode().equalsIgnoreCase("JP")){
			shipToCountryCode=planPO.getShipToCountryCode();
		}
		//调用中间服务客户端
		List<InboundShipmentVO> resultList = AmazonClient.createInboundShipmentPlan(shopId, list, shipFromAddress,shipToCountryCode );
		//将中间服务返回的结果进行封装入库
		if(CollectionUtils.isEmpty(resultList)){
			throw new BussinessException("提交补货计划操作未成功，计划名："+planPO.getPlanName());
		}
		//同时进行封装返回给前端
		List<FbaPreInboundOrderVO> resultVO = new ArrayList<FbaPreInboundOrderVO>();
		UserPO userPO = userDAO.getObject(operatorId);
		//对亚马逊结果集进行遍历
		for (InboundShipmentVO result : resultList) {
			
			
			//创建
			FbaPreInboundOrderVO vo = new FbaPreInboundOrderVO();
			vo.setShipmentId(result.getShipmentId());
			vo.setPlanName(planPO.getPlanName());
			//创建商品项的vo
			List<FbaPreInboundItemVO> preItemsVo = new ArrayList<FbaPreInboundItemVO>();
			
			ReplenishmentOrderPO replenishmentOrderPO = new ReplenishmentOrderPO();
			replenishmentOrderPO.setFbaReplenishmentPlanId(planId);
			replenishmentOrderPO.setRecipients(result.getShipToAddress().getName());
			replenishmentOrderPO.setAddOrderDate(new Date());
			replenishmentOrderPO.setAddUser(operatorId);
			replenishmentOrderPO.setDestinationFulfillmentCenterId(result.getDestinationFulfillmentCenterId());
			replenishmentOrderPO.setShipmentID(result.getShipmentId());
			//将亚马逊返回的仓库地址国家拿去与数据库的国家匹配
			try{
				CountryPO countryPO = countryDao.findByHql("FROM CountryPO WHERE countryCode = ? ", new Object[]{result.getShipToAddress().getCountryCode()});
				replenishmentOrderPO.setCountry(countryPO.getId());
			}catch(Exception e){
				e.printStackTrace();
			}
			//货运方式默认fba-sp
			try{
				ShippingMethodPO shippingMethod = shippingMethodDao.findByHql("FROM ShippingMethodPO WHERE name = ?", "FBA-SP");
				replenishmentOrderPO.setShippingMethod(shippingMethod.getId());
			}catch(Exception e){
				e.printStackTrace();
			}
			replenishmentOrderPO.setShopId(shopId);
			//直接将补货计划的结果生成补货订单，结果为未提交状态
			replenishmentOrderPO.setSubmitStatus(FbaInboundOrderSubmitStatus.未提交.getVal());
			//将补货计划状态设置为已提交
			planPO.setSubmitStatus(FbaInboundPlanSubmitStatus.已提交.getVal());
			planPO.setLastUpdateTime(new Date());
			fbaReplenishmentPlanDao.updateObject(planPO);
			
			replenishmentOrderPO.setIsSend(false);
			replenishmentOrderPO.setOrderItemPrepareNum(0);
			
			replenishmentOrderPO.setModifyDeliveryRemark(false);
			replenishmentOrderPO.setShippingListStatus(ReplenishmentShippingListStatus.未上传.getVal());
			
			Date addDate = replenishmentOrderPO.getAddOrderDate();
			replenishmentOrderPO.setEarliestShippingDate(DateUtils.addDays(addDate, 7));
			replenishmentOrderPO.setLatestShipDate(DateUtils.addDays(addDate, 10));
			replenishmentOrderPO.setEarliestDeliveryDate(DateUtils.addDays(addDate, 12));
			replenishmentOrderPO.setLatestDeliveryDate(DateUtils.addDays(addDate, 25));
			
			replenishmentOrderPO.setIsQualityInspection(1);
			replenishmentOrderPO.setOrderItemNum(result.getItems().size());
			replenishmentOrderPO.setCmsPrepare(IBaseOrder.ORDER_PREPARE_TYPE_CMS);
			
			replenishmentOrderPO.setCode(createOrderCode());
			replenishmentOrderPO.setAddOrderDate(new Date());
			replenishmentOrderPO.setIsQualityInspection(1);
			replenishmentOrderPO.setStatus(OrderStatus.PENDING.getVal());
			replenishmentOrderPO.setShopSource(DictUtils.SHOP_SOURCE_WH_FBA);
			replenishmentOrderPO.setSubTotalPercent(100d);
			replenishmentOrderPO.setAddUser(operatorId);
			replenishmentOrderPO.setShopType(DictUtils.SHOP_TYPE_AMAZON);
			ShopInfo shopInfo = systemService.getShopInfo(shopId);
			Merchandiser merchandiser = systemService.getMerchandiserByCode(shopInfo.getMerchandiserId());
			replenishmentOrderPO.setMerchandiserId(merchandiser.getId());
			//以上参照之前添加补货订单的方法
			replenishmentOrderPO.setCustomerName(result.getShipToAddress().getName());
			Integer replenishmentOrderId = replenishmentOrderDao.addObject(replenishmentOrderPO);
			
			AddressVO shipToAddress = result.getShipToAddress();
			//向前端给地址
			vo.setShipToAddressVO(BeanUtils.copyProperties(shipToAddress, FbaToAddress.class));
			
			//关联地址
			//FbaToAddressPO toAddressPO = BeanUtils.copyProperties(shipToAddress, FbaToAddressPO.class);
			//toAddressPO.setReplenishmentOrderId(replenishmentOrderId);
			//fbaToAddressDao.addObject(toAddressPO);

			ReplenishmentOrderRepositoryPO orderRepository = new ReplenishmentOrderRepositoryPO();
			orderRepository.setAddressLine1(shipToAddress.getAddressLine1());
			orderRepository.setAddressLine2(shipToAddress.getAddressLine2());
			orderRepository.setCity(shipToAddress.getCity());
			if(replenishmentOrderPO.getCountry()!=null){
				orderRepository.setCountryId(replenishmentOrderPO.getCountry());
			}
			orderRepository.setName(shipToAddress.getName());
			orderRepository.setPostalCode(shipToAddress.getPostalCode());
			orderRepository.setStateOrRegion(shipToAddress.getStateOrProvinceCode());
			orderRepository.setRepositoryName(replenishmentOrderPO.getDestinationFulfillmentCenterId());
			orderRepository.setId(replenishmentOrderId);
			orderRepository.setPhone("000000");
			//现在只有武汉
			DepartmentPO departmentPO = departmentDAO.findByHql("FROM DepartmentPO WHERE name='武汉'");
			if(departmentPO!=null){
				orderRepository.setDepartmentId(departmentPO.getId());
			}else{
				orderRepository.setDepartmentId(1);
			}
			replenishmentOrderRepositoryDao.mergeObject(orderRepository);


			
			double costPrice = 0d;
			double totalWeight = 0d;
			//关联补货订单商品项
			List<InboundItemVO> items = result.getItems();
			for (InboundItemVO item : items) {
				ShopProductCmsInfoPO cmsInfoPO = shopProductCmsInfoDao.findByHql("SELECT p FROM ShopProductCmsInfoPO p, FbaShopProductPO s WHERE p.cmsProductId = s.cmsProductId AND s.shopId= ? AND s.sku = ?", new Object[]{shopId,item.getSellerSKU()});
				FbaShopProductPO shopProductPO = fbaShopProductDao.findByHql("FROM FbaShopProductPO WHERE shopId = ? AND sku = ? ", new Object[]{shopId,item.getSellerSKU()});
				
				ReplenishmentOrderItemPO itemPO = new ReplenishmentOrderItemPO();
				itemPO.setOrderedQuantity(item.getQuantity());
				itemPO.setAmQuantity(new Double(item.getQuantity()));
				itemPO.setSku(item.getSellerSKU());
				itemPO.setFbaBarcodeName(shopProductPO.getFbaBarcodeName());
				itemPO.setFbaShopProductId(shopProductPO.getId());
				itemPO.setFbaBarcodeSku(shopProductPO.getFbaBarcodeSku());
				itemPO.setReplenishmentOrderId(replenishmentOrderId);
				itemPO.setStatus(OrderProductStatus.备货中.getVal());
				itemPO.setCmsPrepare(IBaseOrder.ORDER_PREPARE_TYPE_CMS);
				itemPO.setCostPrice(cmsInfoPO.getCostPrice().multiply(new BigDecimal(itemPO.getOrderedQuantity()))
						.multiply(new BigDecimal(cmsInfoPO.getUnitQuantity())).setScale(3, 4).doubleValue());
				costPrice+=itemPO.getCostPrice();
				
				itemPO.setUnit(cmsInfoPO.getUnit());
				itemPO.setUnitQty(cmsInfoPO.getUnitQuantity());
				itemPO.setUnitWeight(cmsInfoPO.getUnitWeight());
				itemPO.setCmsProductCode(cmsInfoPO.getCmsProductCode());
				itemPO.setCmsProductId(cmsInfoPO.getCmsProductId());
				itemPO.setUnitVolume(cmsInfoPO.getUnitVolume());
				
				replenishmentOrderItemDao.addObject(itemPO);
				
				//商品的批量重量
				//BigDecimal unitWeight = CalcUtils.unitweight(new BigDecimal(itemPO.getUnitWeight()), itemPO.getUnitQty());
				//totalWeight += unitWeight.doubleValue()*itemPO.getOrderedQuantity();
				totalWeight +=itemPO.getUnitWeight()*itemPO.getOrderedQuantity();
				//将参数封装到vo的商品项中
				FbaPreInboundItemVO preInboundItemVO = new FbaPreInboundItemVO();
				preInboundItemVO.setCmsProductCode(itemPO.getCmsProductCode());
				preInboundItemVO.setOrderedQuantity(item.getQuantity());
				preInboundItemVO.setPrimaryPictureCode(cmsInfoPO.getPrimaryPictureCode());
				preInboundItemVO.setSku(itemPO.getSku());
				preInboundItemVO.setUnit(itemPO.getUnit());
				preInboundItemVO.setUnitQuantity(itemPO.getUnitQty());
				preItemsVo.add(preInboundItemVO);
			}
			replenishmentOrderPO.setCostPrice(costPrice);
			replenishmentOrderPO.setFbaShipmentMethod(planPO.getShippingMethod());
			replenishmentOrderDao.updateObject(replenishmentOrderPO);
			addOrderOperateLog("提交了补货计划，生成了补货订单，计划名："+planPO.getPlanName(), userPO.getName(), replenishmentOrderPO.getId());
			vo.setTotalWeight(totalWeight);
			vo.setTotalAmount(items.size());
			vo.setItems(preItemsVo);
			resultVO.add(vo);
		}
		addPlanOperateLog("将补货计划提交给了亚马逊", operatorId, planId);
		return resultVO;
	}

	/**
	 * fab补货订单编号生成
	 * @return
	 */
	protected String createOrderCode() {
		List<Param> parameters = new ArrayList<Param>();
		parameters.add(new Param("Code", ""));
		String object = replenishmentOrderDao.execStoredProcedure4output("[dbo].[Order_OrderIdBuild_replenishment]",
				parameters);
		if (object != null) {
			return object.toString();
		}
		return null;
	}


	@Override
	public String createFbaInboundOrder(int shopId,String shipmenId,String shipmentName,int planId,int operatorId) throws BussinessException{
		//先通过shipmenId将亚马逊需要的信息查找出来
		ReplenishmentOrderPO orderPO = replenishmentOrderDao.findByHql("FROM ReplenishmentOrderPO WHERE shopId = ? AND shipmentID = ? ", new Object[]{shopId,shipmenId});
		if(FbaInboundOrderSubmitStatus.已提交.getVal().equalsIgnoreCase(orderPO.getSubmitStatus().trim())){
			throw new BussinessException("请勿重复提交");
		}
		//将货件号保存
		orderPO.setShipmentName(shipmentName);
		orderPO.setFbaReplenishmentPlanId(planId);
		//通过查出来的订单将订单商品项查出来
		List<ReplenishmentOrderItemPO> itemList = replenishmentOrderItemDao.findListByHql("FROM ReplenishmentOrderItemPO WHERE replenishmentOrderId = ?", orderPO.getId());
		
		//通过补货计划id查询退货地址
		FbaFromAddressSnapshotPO snapshotPO = fbaFromAddressSnapshotDao.findByHql("FROM FbaFromAddressSnapshotPO WHERE fbaReplenishmentPlanId = ?", planId);

		//封装给亚马逊
		InboundShipmentVO vo = new InboundShipmentVO();
		vo.setDestinationFulfillmentCenterId(orderPO.getDestinationFulfillmentCenterId());
		//此处固定为卖家贴标  
		vo.setLabelPrepType("SELLER_LABEL");
		vo.setShipmentId(orderPO.getShipmentID());
		vo.setShipmentName(orderPO.getShipmentName());
		//此处固定WORKING 
		vo.setShipmentStatus("WORKING");
		//添加退货地址
		AddressVO shipFromAddress = BeanUtils.copyProperties(snapshotPO, AddressVO.class);
		
		//业务规则 将省和市拼在一起提交，省置为空
		shipFromAddress.setCity(shipFromAddress.getCity()+","+shipFromAddress.getStateOrProvinceCode());
		shipFromAddress.setStateOrProvinceCode(null);
		vo.setShipFromAddress(shipFromAddress);
		
		//添加商品项
		List<InboundItemVO> items = new ArrayList<InboundItemVO>();
		for (ReplenishmentOrderItemPO orderItem : itemList) {
			InboundItemVO itemVO = new InboundItemVO();
			itemVO.setSellerSKU(orderItem.getSku());
			//数据库中的数据类型为double
			itemVO.setQuantity(new Double(orderItem.getOrderedQuantity()).intValue());
			items.add(itemVO);
		}
		vo.setItems(items);
		
		String shipment = AmazonClient.createInboundShipment(shopId, vo);
		if(StringUtils.isEmpty(shipment)){
			throw new BussinessException("提交补货订单操作未成功，订单编号："+orderPO.getCode()+"shipmentId:"+shipmenId);
		}
		//创建补货订单成功后需要修改订单状态为已提交亚马逊，注意异常的处理
		orderPO.setSubmitStatus(FbaInboundOrderSubmitStatus.已提交.getVal());
		orderPO.setAddUser(operatorId); 
		
		//addPlanOperateLog("将补货订单提交给了亚马逊", operatorId, planId);
		UserPO userPO = userDAO.getObject(operatorId);
		addOrderOperateLog("将补货订单提交给了亚马逊，订单编号："+orderPO.getCode(), userPO.getName(), orderPO.getId());
		//保存
		replenishmentOrderDao.updateObject(orderPO);
		return shipment;
	}

	@Override
	public PageList<FbaInboundPlanVO> getFbaInboundPlanList(int shopId, int page, int pageSize, String sku,String productCode,int operatorId) {
		//根据店铺id ， sku,productCode查询出该店铺的补货计划
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT distinct a FROM FbaReplenishmentPlanPO a,FbaReplenishmentPlanItemPO b,FbaShopProductPO c WHERE a.id=b.fbaReplenishmentPlanId AND b.fbaShopProductId = c.id ");
		List<Object> o = new ArrayList<Object>();
		if(shopId!=-1){
			buffer.append("AND a.shopId = ? ");
			o.add(shopId);
		}else{
			buffer.append("AND a.shopId in ( ");
			List<Shop> shops = systemService.getShopsByUserId(operatorId);
			for (int i = 0; i < shops.size(); i++) {
				if(i==shops.size()-1){
					buffer.append(shops.get(i).getId()+" )");
				}else{
					buffer.append(shops.get(i).getId()+",");
				}
			}
		}
		
		if(sku!=null&&!sku.trim().equals("")){
			String[] skus = sku.split(";");
			if(skus.length==1){
				buffer.append("AND c.sku = ? ");
				o.add(sku.trim());
			}else{
				buffer.append("AND c.sku in ( ");
				for (int i = 0; i < skus.length; i++) {
					if(i==skus.length-1){
						buffer.append("'"+skus[i].trim()+"' )");
					}else{
						buffer.append("'"+skus[i].trim()+"',");
					}
				}
			}
		}
		if(productCode!=null&&!productCode.trim().equals("")){
			String[] codes = productCode.split(";");
			if(codes.length==1){
				buffer.append("AND c.cmsProductCode = ? ");
				o.add(productCode.trim());
			}else{
				buffer.append("AND c.cmsProductCode in ( ");
				for (int i = 0; i < codes.length; i++) {
					if(i==codes.length-1){
						buffer.append("'"+codes[i].trim()+"' )");
					}else{
						buffer.append("'"+codes[i].trim()+"',");
					}
				}
			}
		}
		buffer.append(" ORDER BY a.createTime DESC");
		//System.out.println(buffer.toString());
		//查询分页信息
		PageList<FbaReplenishmentPlanPO> pageList = fbaReplenishmentPlanDao.findPageListByHql(page,pageSize,buffer.toString(), o.toArray());
		
		List<FbaInboundPlanVO> resultList = new ArrayList<FbaInboundPlanVO>();
		for (FbaReplenishmentPlanPO fbaReplenishmentPlanPO : pageList.getData()) {
			FbaInboundPlanVO planVO = new FbaInboundPlanVO();
			planVO.setAmount(fbaReplenishmentPlanPO.getAmount());
			planVO.setCreateTime(fbaReplenishmentPlanPO.getCreateTime());
			planVO.setId(fbaReplenishmentPlanPO.getId());
			planVO.setLastUpdateTime(fbaReplenishmentPlanPO.getLastUpdateTime());
			planVO.setPlanName(fbaReplenishmentPlanPO.getPlanName());
			planVO.setMskus(fbaReplenishmentPlanPO.getMskus());
			planVO.setSubmitStatus(Integer.parseInt(fbaReplenishmentPlanPO.getSubmitStatus()));
			planVO.setIsSubmitToAm(fbaReplenishmentPlanPO.getIsSubmitToAm()==null?0:fbaReplenishmentPlanPO.getIsSubmitToAm());
			resultList.add(planVO);
		}
		
		PageList<FbaInboundPlanVO> pageList2 = new PageList<FbaInboundPlanVO>();
		pageList2.setData(resultList);
		pageList2.setPage(pageList.getPage());
		return pageList2;
	}

	@Override
	public FbaInboundPlanSearchVO findFbaInboundPlanByPlanId(int planId) {
		FbaInboundPlanSearchVO planSearchVO = new FbaInboundPlanSearchVO();
		
		FbaReplenishmentPlanPO planPO = fbaReplenishmentPlanDao.getObject(planId);
		if(planPO==null){
			return null;
		}
		
		//查询地址
		FbaFromAddressSnapshotPO addressSnapshotPO = fbaFromAddressSnapshotDao.findByHql("FROM FbaFromAddressSnapshotPO WHERE fbaReplenishmentPlanId = ?", planId);
		
		planSearchVO.setAdddress(BeanUtils.copyProperties(addressSnapshotPO, FbaFromAddress.class));
		
		List<FbaInboundPlanItemExcelResult> items = new ArrayList<FbaInboundPlanItemExcelResult>();
		List<FbaReplenishmentPlanItemPO> itemList = fbaReplenishmentPlanItemDao.findListByHql("FROM FbaReplenishmentPlanItemPO WHERE fbaReplenishmentPlanId = ?", planId);
		for (FbaReplenishmentPlanItemPO item : itemList) {
			//ShopProductCmsInfoPO cmsInfoPO = shopProductCmsInfoDao.findByHql("FROM ShopProductCmsInfoPO WHERE cmsProductId = ?", item.getFbaShopProductId());
			
			//查询出对应关系
			FbaShopProductPO shopProductPO = fbaShopProductDao.findByHql("FROM FbaShopProductPO WHERE id = ?", item.getFbaShopProductId());
			
			FbaInboundPlanItemExcelResult excelResult = new FbaInboundPlanItemExcelResult();
			excelResult.setCmsProductCode(shopProductPO.getCmsProductCode());
			excelResult.setOrderedQuantity(item.getQuantity());
			excelResult.setSku(shopProductPO.getSku());
			
			ShopProductCmsInfoPO cmsInfoPO = shopProductCmsInfoDao.findByHql("FROM ShopProductCmsInfoPO WHERE cmsProductId = ?",shopProductPO.getCmsProductId());
			excelResult.setPrimaryPictureCode(cmsInfoPO.getPrimaryPictureCode());
			excelResult.setUnit(cmsInfoPO.getUnit());
			excelResult.setUnitQuantity(cmsInfoPO.getUnitQuantity());
			items.add(excelResult);
		}
		planSearchVO.setSubmitStatus(Integer.parseInt(planPO.getSubmitStatus()));
		planSearchVO.setItems(items);
		planSearchVO.setMskus(planPO.getMskus());
		planSearchVO.setPlanName(planPO.getPlanName());
		planSearchVO.setShippingMethod(planPO.getShippingMethod());
		planSearchVO.setShipToCountryCode(planPO.getShipToCountryCode());
		planSearchVO.setShopId(planPO.getShopId());
		
		//如果该计划已经提交，那么需要将订单对象查询出来
		List<ReplenishmentOrderPO> orderList = replenishmentOrderDao.findListByHql("FROM ReplenishmentOrderPO WHERE fbaReplenishmentPlanId = ?", planId);
		if(orderList!=null){
			List<FbaPreInboundOrderVO> orders = new ArrayList<FbaPreInboundOrderVO>();
			for (ReplenishmentOrderPO orderPO : orderList) {
				FbaPreInboundOrderVO orderVO = new FbaPreInboundOrderVO();
				orderVO.setPlanName(planPO.getPlanName());
				orderVO.setShipmentId(orderPO.getShipmentID());
				orderVO.setShipmentName(orderPO.getShipmentName());
				//根据订单Id去查询收货地址
				//FbaToAddressPO toAddressPO = fbaToAddressDao.findByHql("FROM FbaToAddressPO WHERE replenishmentOrderId = ?", orderPO.getId());
				//orderVO.setShipToAddressVO(BeanUtils.copyProperties(toAddressPO, FbaToAddress.class));
				ReplenishmentOrderRepositoryPO repositoryPO = replenishmentOrderRepositoryDao.findByHql("FROM ReplenishmentOrderRepositoryPO WHERE id = ?", orderPO.getId());
				FbaToAddress fbaToAddress = new FbaToAddress();
				fbaToAddress.setAddressLine1(repositoryPO.getAddressLine1());
				fbaToAddress.setAddressLine2(repositoryPO.getAddressLine2());
				fbaToAddress.setCity(repositoryPO.getCity());
				CountryPO countryPO = countryDao.findByHql("FROM CountryPO WHERE id = ? ", repositoryPO.getCountryId());
				fbaToAddress.setCountryCode(countryPO.getCountryCode());
				fbaToAddress.setDistrictOrCounty(repositoryPO.getStateOrRegion());
				fbaToAddress.setId(repositoryPO.getId());
				fbaToAddress.setName(repositoryPO.getName());
				fbaToAddress.setPostalCode(repositoryPO.getPostalCode());
				fbaToAddress.setStateOrProvinceCode(repositoryPO.getStateOrRegion());
				orderVO.setShipToAddressVO(fbaToAddress);
				//根据订单id查询商品项
				List<ReplenishmentOrderItemPO> orderItemList = replenishmentOrderItemDao.findListByHql("FROM ReplenishmentOrderItemPO WHERE replenishmentOrderId = ?", orderPO.getId());
				
				//先定义该单的总重量
				double totalWeight = 0;
				int amount = 0;
				List<FbaPreInboundItemVO> list = new ArrayList<FbaPreInboundItemVO>();
				for (ReplenishmentOrderItemPO replenishmentOrderItemPO : orderItemList) {
					FbaPreInboundItemVO vo = new FbaPreInboundItemVO();
					vo.setOrderedQuantity(new Double(replenishmentOrderItemPO.getOrderedQuantity()).intValue());
					vo.setCmsProductCode(replenishmentOrderItemPO.getCmsProductCode());
					int cmsProductId = replenishmentOrderItemPO.getCmsProductId();
					ShopProductCmsInfoPO cmsInfoPO = shopProductCmsInfoDao.findByHql("FROM ShopProductCmsInfoPO WHERE cmsProductId = ?", cmsProductId);
					vo.setPrimaryPictureCode(cmsInfoPO.getPrimaryPictureCode());
					vo.setSku(replenishmentOrderItemPO.getSku());
					vo.setUnit(replenishmentOrderItemPO.getUnit());
					vo.setUnitQuantity(replenishmentOrderItemPO.getUnitQty());
					list.add(vo);
					//totalWeight+=cmsInfoPO.getUnitWeight()*cmsInfoPO.getUnitQuantity()*1.15*replenishmentOrderItemPO.getOrderedQuantity();
					totalWeight+=cmsInfoPO.getUnitWeight()*replenishmentOrderItemPO.getOrderedQuantity();
					amount+=1;
				}
				
				orderVO.setTotalAmount(amount);
				orderVO.setTotalWeight(totalWeight);
				orderVO.setItems(list);
				
				orders.add(orderVO);
			}
			planSearchVO.setOrders(orders);
		}
		
		return planSearchVO;
	}

	@Override
	public void deleteFbaInboundPlan(int planId, int operatorId) {
		//先查出补货计划
		FbaReplenishmentPlanPO planPO = fbaReplenishmentPlanDao.getObject(planId);
		
		//删除发货地址快照
		List<FbaFromAddressSnapshotPO> list = fbaFromAddressSnapshotDao.findListByHql("FROM FbaFromAddressSnapshotPO WHERE fbaReplenishmentPlanId = ?", planId);
		//删除计划商品项
		List<FbaReplenishmentPlanItemPO> list2 = fbaReplenishmentPlanItemDao.findListByHql("FROM FbaReplenishmentPlanItemPO WHERE fbaReplenishmentPlanId = ?", planId);
		fbaReplenishmentPlanDao.deleteObject(planPO);
		fbaFromAddressSnapshotDao.deleteObjects(list);
		fbaReplenishmentPlanItemDao.deleteObjects(list2);
		addPlanOperateLog("删除了补货计划", operatorId, planId);
	}


	@Override
	public List<FbaInboundPlanOperateLog> getOperateLogsByPlanId(int planId) {
		List<FbaReplenishmentPlanOperateLogPO> poList = fbaReplenishmentPlanOperateLogDao.findListByHql("FROM FbaReplenishmentPlanOperateLogPO WHERE replenishmentPlanId = ?", planId);
		List<FbaInboundPlanOperateLog> voList = new ArrayList<FbaInboundPlanOperateLog>();
		for (FbaReplenishmentPlanOperateLogPO po : poList) {
			FbaInboundPlanOperateLog vo = new FbaInboundPlanOperateLog();
			vo.setDate(po.getOperateTime());
			UserPO userPO = userDAO.getObject(po.getOperatorId());
			vo.setOperator(userPO.getName());
			vo.setComment(po.getComment());
			voList.add(vo);
		}
		
		return voList;
	}


	/**
	 *
	 *用于自动任务的和手动任务的   因为涉及到老单，而且老单查不到退货地址，所以更新给亚马逊是空，所以不允许手动建立的订单更新
	 *
	 * @param replenishmentOrder
	 * @param ShipmentStatus
	 * @param areCasesRequired
	 */
	@Override
	public void updateFbaInboundOrder(ReplenishmentOrderPO replenishmentOrder, String ShipmentStatus,boolean areCasesRequired) {
//		int shopId = replenishmentOrder.getShopId();
//		String shipmentID = replenishmentOrder.getShipmentID();
		//先通过shipmenId将亚马逊需要的信息查找出来
//		ReplenishmentOrderPO orderPO = replenishmentOrderDao.
//				findByHql("FROM ReplenishmentOrderPO WHERE shopId = ? AND shipmentID = ? ", new Object[]{shopId,shipmentID});

        //通过查出来的订单将订单商品项查出来
		List<ReplenishmentOrderItemPO> itemList = replenishmentOrderItemDao.
				findListByHql("FROM ReplenishmentOrderItemPO WHERE replenishmentOrderId = ?", replenishmentOrder.getId());
		//封装给亚马逊
		InboundShipmentVO vo = new InboundShipmentVO();
		vo.setDestinationFulfillmentCenterId(replenishmentOrder.getDestinationFulfillmentCenterId());
		vo.setLabelPrepType("SELLER_LABEL");
		vo.setShipmentId(replenishmentOrder.getShipmentID());
		vo.setShipmentName(replenishmentOrder.getShipmentName());
		vo.setShipmentStatus(ShipmentStatus);

		//AreCasesRequired false

		//通过补货计划id查询退货地址
		FbaFromAddressSnapshotPO snapshotPO = fbaFromAddressSnapshotDao
				.findByHql("FROM FbaFromAddressSnapshotPO WHERE fbaReplenishmentPlanId = ?", replenishmentOrder.getFbaReplenishmentPlanId());

//		ReplenishmentOrderRepositoryPO repositoryPO = replenishmentOrderRepositoryDao.
//				findByHql("FROM ReplenishmentOrderRepositoryPO WHERE id = ?", replenishmentOrder.getRepositoryId());

		//添加退货地址
		vo.setShipFromAddress(BeanUtils.copyProperties(snapshotPO, AddressVO.class));

		//添加商品项 只有sku和数量
		List<InboundItemVO> inboundItemVOList = new ArrayList<InboundItemVO>();
		for (ReplenishmentOrderItemPO orderItem : itemList) {
			InboundItemVO itemVO = new InboundItemVO();
			itemVO.setSellerSKU(orderItem.getSku());

			if (orderItem.getAmQuantity()!=null) {
				itemVO.setQuantity(orderItem.getAmQuantity().intValue());	//数据库中的数据类型为double
			}else {
				itemVO.setQuantity((int)orderItem.getOrderedQuantity());
			}
//			itemVO.setQuantity(orderItem.getAmQuantity().intValue());	//数据库中的数据类型为double
			inboundItemVOList.add(itemVO);
		}
		vo.setItems(inboundItemVOList);
		//通讯osms_am
		String returnShipmentId = AmazonClient.updateInboundShipment(replenishmentOrder.getShopId(),vo);



//		if(StringUtils.isEmpty(returnShipmentId)){
//			throw new BussinessException("补货订单更新失败,订单编号："+replenishmentOrder.getCode());
//		}

		//创建补货订单成功后需要修改订单状态为已提交亚马逊，注意异常的处理
//		orderPO.setSubmitStatus(FbaInboundOrderSubmitStatus.已提交.getVal());
//		orderPO.setAddUser(-3);
		//save order
//		replenishmentOrderDao.updateObject(orderPO);

		//add log
//		addOrderOperateLog("将补货订单更新提交给了亚马逊，订单编号："+orderPO.getCode(), userDAO.getObject(operatorId).getName(), orderPO.getId());
	}



	@Override
	public void updateFbaInboundOrder(String shipmentId, int shopId, List<FbaPreInboundItemVO> items, int operatorId) {
		if(items!=null && items.size()>200){
			throw new BussinessException("操作最多可包含 200 件商品");
		}
		//创建补货订单
		//先通过shipmenId将亚马逊需要的信息查找出来
		ReplenishmentOrderPO orderPO = replenishmentOrderDao.findByHql("FROM ReplenishmentOrderPO WHERE shopId = ? AND shipmentID = ? ", new Object[]{shopId,shipmentId});
		//通过查出来的订单将订单商品项查出来
		List<ReplenishmentOrderItemPO> itemList = replenishmentOrderItemDao.findListByHql("FROM ReplenishmentOrderItemPO WHERE replenishmentOrderId = ?", orderPO.getId());

		for (ReplenishmentOrderItemPO anItem : itemList) {
			if (items != null) {
				for (int i = 0; i < items.size(); i++) {
					if (anItem.getSku().trim().equals(items.get(i).getSku().trim())) {
						anItem.setAmQuantity((double) items.get(i).getOrderedQuantity());  //把am的quantity改过来
						items.remove(items.get(i));   //items 前台提交的需要修改的item
						i--;
					}
				}
			}
		}
		replenishmentOrderItemDao.updateObject(itemList);   //修改已有的商品

		//添加的新product ，items中剩下的
        if (items != null && items.size()>0) {
            for (FbaPreInboundItemVO item : items) {
                ReplenishmentOrderItemPO replenishmentOrderItemPO1 = new ReplenishmentOrderItemPO();	//添加这个新的商品从前台传来的
                replenishmentOrderItemPO1.setAmQuantity((double)item.getOrderedQuantity());
                replenishmentOrderItemPO1.setSku(item.getSku());
                itemList.add(replenishmentOrderItemPO1);   //用于传给亚马逊

                //用于持久化 新增商品
                List<Object> params = new ArrayList<Object>();
                params.add(orderPO.getId(),item.getSku());
                List<ReplenishmentOrderItemPO> replenishmentOrderItemPOList = replenishmentOrderItemDao.findListBySql
                        ("SELECT * FROM replenishment_order_product WHERE replenishment_order_id=? AND sku=? ",params);
                for (ReplenishmentOrderItemPO replenishmentOrderItemPO : replenishmentOrderItemPOList) {
                    replenishmentOrderItemPO.setAmQuantity((double)item.getOrderedQuantity());
                    replenishmentOrderItemDao.updateObject(replenishmentOrderItemPO);  //写入本地
                }
            }
        }

        //无论商品是后来新增的还是原有的，只要是状态为取消，都把传给亚马逊的数量改为0
		for (ReplenishmentOrderItemPO replenishmentOrderItemPO : itemList) {
			if(replenishmentOrderItemPO.getStatus() == OrderProductStatus.取消.getVal()){
				replenishmentOrderItemPO.setAmQuantity(0.0);
			}
		}

		//封装给亚马逊
		InboundShipmentVO vo = new InboundShipmentVO();
		vo.setDestinationFulfillmentCenterId(orderPO.getDestinationFulfillmentCenterId());
		vo.setLabelPrepType("SELLER_LABEL");//此处固定为卖家贴标
		vo.setShipmentId(orderPO.getShipmentID());
		vo.setShipmentName(orderPO.getShipmentName());
		vo.setShipmentStatus("WORKING"); //此处固定WORKING

		//通过补货计划id查询退货地址
		FbaFromAddressSnapshotPO snapshotPO = fbaFromAddressSnapshotDao
				.findByHql("FROM FbaFromAddressSnapshotPO WHERE fbaReplenishmentPlanId = ?", orderPO.getFbaReplenishmentPlanId());

		//添加退货地址
		AddressVO shipFromAddress = BeanUtils.copyProperties(snapshotPO, AddressVO.class);
		
		//业务规则 将省和市拼在一起提交，省置为空
		shipFromAddress.setCity(shipFromAddress.getCity()+","+shipFromAddress.getStateOrProvinceCode());
		shipFromAddress.setStateOrProvinceCode(null);
		vo.setShipFromAddress(shipFromAddress);
		//添加商品项 只有sku和数量
		List<InboundItemVO> inboundItemVOList = new ArrayList<InboundItemVO>();
		for (ReplenishmentOrderItemPO orderItem : itemList) {
			InboundItemVO itemVO = new InboundItemVO();
			itemVO.setSellerSKU(orderItem.getSku());
			itemVO.setQuantity(orderItem.getAmQuantity().intValue());	//数据库中的数据类型为double
			inboundItemVOList.add(itemVO);
		}
		vo.setItems(inboundItemVOList);

		//通讯osms_am
		String returnShipmentId = AmazonClient.updateInboundShipment(shopId,vo);
		if(StringUtils.isEmpty(returnShipmentId)){
			ObjectMapper mapper = new ObjectMapper();
			try {
				logger.error(mapper.writeValueAsString(vo));
			} catch (IOException e) {
				e.printStackTrace();
			}
			throw new BussinessException("补货订单更新失败,订单编号："+orderPO.getCode());
        }

		//创建补货订单成功后需要修改订单状态为已提交亚马逊，注意异常的处理
		orderPO.setSubmitStatus(FbaInboundOrderSubmitStatus.已提交.getVal());
		orderPO.setAddUser(operatorId);
		//save order
		replenishmentOrderDao.updateObject(orderPO);

		addOrderOperateLog("将补货订单更新提交给了亚马逊，订单编号："+orderPO.getCode(), userDAO.getObject(operatorId).getName(), orderPO.getId());

	}

	@Override
	public void createFbaInboundOrder(List<ShipmenIdAndNameVO> shipmenIdAndNameVO, int shopId, int planId,
			int operatorId) {
		for (ShipmenIdAndNameVO vo : shipmenIdAndNameVO) {
			createFbaInboundOrder(shopId, vo.getShipmentId(),vo.getShipmentName(),planId,operatorId);
			//System.out.println(order);
		}
		FbaReplenishmentPlanPO planPO = fbaReplenishmentPlanDao.getObject(planId);
		planPO.setSubmitStatus(FbaInboundPlanSubmitStatus.已建单.getVal());
		addPlanOperateLog("补货计划已经建单，计划名："+planPO.getPlanName(), operatorId, planId);
		fbaReplenishmentPlanDao.updateObject(planPO);
	}


	@Override
	public String putTransportContent(PutTransportContentVO putTransportContentVO) {

		ReplenishmentOrderPO replenishmentOrderPO = replenishmentOrderDao.findByHql("from ReplenishmentOrderPO where code=? ",new Object[]{putTransportContentVO.getReplenishmentOrderCode()});
		putTransportContentVO.setReplenishmentOrderId(replenishmentOrderPO.getId());

		TransportContentVO transportContentVO = new TransportContentVO();
		transportContentVO.setShipmentId(putTransportContentVO.getShipmentId());
		transportContentVO.setPartnered(false);
		transportContentVO.setShipmentType("SP");
		transportContentVO.setCarrierName("OTHER");

		List<TransportDetailInputVO> transportDetailInputVO = new ArrayList<TransportDetailInputVO>();
        TransportDetailInputVO _transportDetailInputVO = new TransportDetailInputVO();
		_transportDetailInputVO.setTrackingId(putTransportContentVO.getOrderTrackCode());
        transportDetailInputVO.add(_transportDetailInputVO);

		transportContentVO.setTransportDetailInputVO(transportDetailInputVO);

//        AmazonClient amazonClient = new AmazonClient();
		String transportResult = AmazonClient.putTransportContent(putTransportContentVO.getShopId(),transportContentVO);

//		if (transportResult==null){
//			throw new BussinessException("更新货运单号至亚马逊失败,补货订单编号："+putTransportContentVO.getReplenishmentOrderCode());
//		}

		//add log 人来建单才有操作日志记录
		if(putTransportContentVO.getOperatorId() > 0){
			addOrderOperateLog("将补货订单更新提交给了亚马逊，订单编号："+putTransportContentVO.getReplenishmentOrderCode(), userDAO.getObject(putTransportContentVO.getOperatorId()).getName(),putTransportContentVO.getReplenishmentOrderId());
		}

		updateFbaInboundOrder(replenishmentOrderPO,"SHIPPED",false);

		return transportResult;
	}

	@Override
	public ListInboundShipmentsResultVO listInboundShipments(AmazonShipmentStatusListVO amazonShipmentStatusListVO,Integer shopId) {

//        AmazonClient amazonClient = new AmazonClient();
        ListInboundShipmentsResultVO resultVO = AmazonClient.listInboundShipments(shopId, amazonShipmentStatusListVO);

        //如果返回值为空，抛异常

//        if(resultVO==null){
//            throw new BussinessException("更新货运单号至亚马逊失败,shipmentId："+amazonShipmentStatusListVO.getShipmentIdList());
//		}
        return resultVO;
	}

	@Override
	public ListInboundShipmentsResultVO listInboundShipmentsByNextToken(String token,Integer shopId) {

        AmazonShipmentStatusListVO amazonShipmentStatusListVO = new AmazonShipmentStatusListVO();
        amazonShipmentStatusListVO.setNextToken(token);

//        AmazonClient amazonClient = new AmazonClient();
        ListInboundShipmentsResultVO resultVO = AmazonClient.listInboundShipmentsByNextToken(shopId,amazonShipmentStatusListVO);

//        if(resultVO==null){
//            throw new BussinessException("更新货运单号至亚马逊失败,shipmentId："+amazonShipmentStatusListVO.getShipmentIdList());
//        }

        return resultVO;
	}



}