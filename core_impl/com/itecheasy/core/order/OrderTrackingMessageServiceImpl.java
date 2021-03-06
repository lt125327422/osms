package com.itecheasy.core.order;

import java.util.Date;
import java.util.List;

import com.itecheasy.core.po.OrderTrackingProductPO;
import org.apache.log4j.Logger;

import com.itecheasy.common.util.BeanUtils;
import com.itecheasy.common.util.CollectionUtils;
import com.itecheasy.common.util.DateUtils;
import com.itecheasy.core.po.OrderTrackingPO;
import com.itecheasy.core.po.OrderTrackingProductAttachmentPO;

//import com.itecheasy.core.po.OrderTrackingProductPO;
import com.itecheasy.core.task.BaseOrderDetailView;
import com.itecheasy.core.user.User;
import com.itecheasy.webservice.client.CMSClient;
import com.itecheasy.webservice.client.CMSOrderClient;
import com.itecheasy.webservice.cms.order.CommunicationLog;
import com.itecheasy.webservice.cms.order.OrderTracking;
import com.itecheasy.webservice.cms.order.OrderTrackingAttachment;

import com.itecheasy.webservice.cms.order.OrderTrackingItem;


public class OrderTrackingMessageServiceImpl extends OrderTrackingServiceImpl implements OrderTrackingMessageService{
	private final static Logger LOGGER = Logger.getLogger(OrderTrackingMessageServiceImpl.class);
	private static final String CMS_ORDER_TRACKING_MAX_ID = "cms_order_tracking_max_id";

	
	private int convert2systemUserId(int cmsUserId) {
		if(cmsUserId==0) return 0;
		User m = systemService.getUserByCMSUserId(cmsUserId);
		return m != null ? m.getId() : 0;
	}
	
	
	@Override
	public void processingOrderTracking() {
		LOGGER.info("sync OrderTracking begin...");
		String maxID = sysConfigService.getValue(CMS_ORDER_TRACKING_MAX_ID);
		LOGGER.info("sync OrderTracking id..." + maxID);
		int _maxId = Integer.valueOf(maxID);
		List<CommunicationLog> logs_order = CMSOrderClient.getOrderTrackingOperatorLogById(_maxId);   //from cms download ordertracking

		//test
//		logs_order = logs_order.subList(0,33);

		if (CollectionUtils.isNotEmpty(logs_order)) {
			for (CommunicationLog g : logs_order) {
				if (_maxId < g.getId()) {
					_maxId = g.getId();
				}
				communicationOrderTrackingByLog(g);
			}
			sysConfigService.setValue(CMS_ORDER_TRACKING_MAX_ID, String.valueOf(_maxId));
		}
		LOGGER.info("sync OrderTracking end...maxID:" + _maxId);
	}

	private void communicationOrderTrackingByLog(CommunicationLog log) {
		OrderTracking cmsOrderTracking = CMSOrderClient.getOrderTrackingById(log.getObjectId());
		if (CollectionUtils.isEmpty(cmsOrderTracking.getTrackingItems())) {
			return;
		}
		com.itecheasy.core.order.OrderTracking orderTrack = this.getOrderTrackingByCode(cmsOrderTracking.getCode());

		int replyUserId = 0;
		if (orderTrack != null) {
			if (orderTrack.isFormCMS()) {// Cms发起的订单跟踪CRM处理人不变，固定跟单员
				// replyUserId = orderTrack.ReplyUserId;
				// //SingleDal.GetOrderTrackingDal().UpdateOrderTrackingReplyInfo(orderTracking.code,
				// trackItem.replyContent, replyUserId,
				// trackItem.replyTime.ToString("yyyy-MM-dd HH:mm:ss"),
				// trackItem.remark, trackItem.status, db, transaction);
				// #region 同步咨询明细
				// SingleDal.GetOrderTrackingDal().DeleteOrderTrackingItem(orderTrack.Id,
				// db, transaction);
				// orderTrackingReplayHistory[] Items =
				// CRM.Dal.Communication_CMS.OrderWebService.getOrderTrackingReplayHistory(orderTracking.id);
				// foreach (orderTrackingReplayHistory item in Items)
				// {
				// SingleDal.GetOrderTrackingDal().AddOrderTrackingItem(orderTracking.code,
				// item.content, item.replayUser,
				// item.replayTime.ToString("yyyy-MM-dd HH:mm:ss"), db,
				// transaction);
				// }
				// #endregion
			} else {
				OrderTrackingPO orderTrackingPO=this.orderTrackingDao.getObject(orderTrack.getId());
				for (OrderTrackingItem item : cmsOrderTracking.getTrackingItems()) {

					OrderTrackingProductPO po = getOrderTrackingProduct(orderTrack.getId(), item.getProductId());
					po.setFollowUpUser(convert2systemUserId(item.getOperatorId()));
					// CMS 有已分配=12，待审核=15，审核不通过=17状态
					if (item.getStatus() == OrderTrackingStatus.未答复.getVal()
							|| item.getStatus() == OrderTrackingStatus.已答复.getVal()
							|| item.getStatus() == OrderTrackingStatus.完成.getVal()) {
						if (item.getStatus() == OrderTrackingStatus.已答复.getVal()) {
							
							po.setBranchRemark(item.getBranchRemark());
							po.setReplyDate(DateUtils.getDateByXMLGregorianCalendar(item.getAuditTime()));

							orderTrackingPO.setRead(false);   //只要有一个商品是已答复状态，就要把订单跟踪状态的isRead改为false（有未读消息）

							orderTrackingPO.setReplyDate(po.getReplyDate());
							orderTrackingPO.setReplyContent(po.getBranchRemark());
							orderTrackingPO.setReplyUserId(convert2systemUserId(item.getReplyUserId()));
							if (po.getStatus() == OrderTrackingStatus.未答复.getVal()) {
								addOrderTrackingHistory(orderTrack.getId(), "更新跟踪商品" + po.getCmsProductCode()
										+ "状态为已答复", convert2systemUserId(item.getOperatorId()),
										DateUtils.getDateByXMLGregorianCalendar(item.getAuditTime()));
								po.setStatus(item.getStatus());
								
								addOrderTrackingItem(orderTrack.getId(), po.getBranchRemark(), orderTrackingPO.getReplyUserId(), 
										po.getReplyDate());
							}
							this.orderTrackingProductDao.updateObject(po);
							this.orderTrackingDao.updateObject(orderTrackingPO);
						}
						syncAttachment(orderTrack.getId(), po.getId(), item);
					}
				}

				this.checkOrderTrackingAlreadyReply(orderTrack.getId(),orderTrackingPO.getReplyUserId());
			}
		} else {
			orderTrack = new com.itecheasy.core.order.OrderTracking();
			orderTrack.setCode(cmsOrderTracking.getCode());
			orderTrack.setType(cmsOrderTracking.getType());
			orderTrack.setOrderCode(cmsOrderTracking.getOrderCode());
			orderTrack.setConsultationUserId(convert2systemUserId(cmsOrderTracking.getConsultationUserId()));
			orderTrack.setCreateUserId(convert2systemUserId(cmsOrderTracking.getCreateUserId()));
			orderTrack.setConsultationTime(DateUtils.getDateByXMLGregorianCalendar(cmsOrderTracking
					.getConsultationTime()));
			orderTrack.setStatus(OrderTrackingStatus.未答复.getVal());
			if (replyUserId == 0) {
				BaseOrderDetailView _order = orderSchedule.getOrderDetailByCode(orderTrack.getOrderCode());
				if (_order != null)
					replyUserId = convert2systemUserId(_order.getMerchandiserId());
			}
			orderTrack.setReplyUserId(replyUserId);
			orderTrack.setFormCMS(true);

			orderTrack.setRead(false);	//  下载订单过来是未读状态  set having not read message   isRead 没有阅读

			String log1 = "";
			OrderTrackingPO po = BeanUtils.copyProperties(orderTrack, OrderTrackingPO.class, new String[] { "products",
					"items" });
//			po.setRead(false);
			int orderTrakcingId = this.orderTrackingDao.addObject(po);

			log1 += "添加订单跟踪：" + orderTrack.getCode() + ";添加跟踪商品:";
			OrderTrackingProductPO product = null;
			for (OrderTrackingItem item : cmsOrderTracking.getTrackingItems()) {
				product = new OrderTrackingProductPO();
				product.setOrderTrackingId(orderTrakcingId);
				product.setCmsProductId(item.getProductId());
				product.setConsultationContent(item.getContent());
				product.setFollowUpUser(replyUserId);
				product.setStatus(item.getStatus());
				product.setCmsProductCode(CMSClient.getCMSProduct(item.getProductId()).getCode());


				int orderTrackingProductId = orderTrackingProductDao.addObject(product);
				syncAttachment(po.getId(), orderTrackingProductId, item);
				log1 += product.getCmsProductCode() + ";";
			}
			
			addOrderTrackingHistory(orderTrakcingId, log1, 0);
		}
	}

	private void syncAttachment(int orderTrackingId, int orderTrackingProductId, OrderTrackingItem cmsTrackingItem) {
		if (CollectionUtils.isNotEmpty(cmsTrackingItem.getOrderTrackingAttachments())) {
			String hql="from OrderTrackingProductAttachmentPO where orderTrackingProductId=?";
			orderTrackingProductAttachmentDao.deleteObjects(orderTrackingProductAttachmentDao.findListByHql(hql, orderTrackingProductId));
			for (OrderTrackingAttachment att : cmsTrackingItem.getOrderTrackingAttachments()) {
				String UUID = this.saveAttachment(orderTrackingProductId, att.getAttachmentName(),
						att.getAttachmentCode());
				OrderTrackingProductAttachmentPO po = new OrderTrackingProductAttachmentPO();
				po.setOrderTrackingId(orderTrackingId);
				po.setOrderTrackingProductId(orderTrackingProductId);
				po.setJoinDate(new Date());
				po.setFileCode(UUID);
				po.setOriginalFileName(att.getAttachmentName());
				po.setFileName(att.getAttachmentName());

				orderTrackingProductAttachmentDao.addObject(po);
			}
		}
	}

}
