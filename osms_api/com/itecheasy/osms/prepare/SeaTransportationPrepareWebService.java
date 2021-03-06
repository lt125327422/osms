package com.itecheasy.osms.prepare;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.itecheasy.common.PageList;
import com.itecheasy.core.BussinessException;
import com.itecheasy.core.prepare.PreparePlanSearchVO;
import com.itecheasy.core.prepare.SeaTransportationPlanDetailVO;
import com.itecheasy.core.prepare.SeaTransportationPlanOperateLogVO;
import com.itecheasy.core.prepare.SeaTransportationPlanVO;

/**
 * @author taozihao
 * @date 2018年6月30日 下午12:47:26
 * @description osms海运备货计划服务接口
 */
@WebService
public interface SeaTransportationPrepareWebService {
	/**
	 * 根据条件查询海运备货计划列表
	 * 
	 * @param planSearchVO
	 * @return
	 * 
	 */
	public PageList<SeaTransportationPlanVO> getSeaTransportationPlanList(
			@WebParam(name = "planSearchVO") PreparePlanSearchVO planSearchVO,
			@WebParam(name = "operatorId") int operatorId);

	/**
	 * 根据店铺id和计划到货日期和预期库存周期推荐备货计划商品项 同时生成备货计划以及商品项，保存
	 * 
	 * @param seaTransportationPlanVO
	 * @param operatorId
	 * @return
	 */
	public SeaTransportationPlanDetailVO saveAndGetRecommendProductList (
			@WebParam(name = "seaTransportationPlanVO") SeaTransportationPlanVO seaTransportationPlanVO,
			@WebParam(name = "operatorId") int operatorId) throws BussinessException;

	/**
	 * 新增海运备货计划(拆分的时候需要)
	 * 
	 * @param seaTransportationPlanDetail
	 * @param operatorId
	 */
	public void addSeaTransportationPlan(
			@WebParam(name = "seaTransportationPlanDetail") SeaTransportationPlanDetailVO seaTransportationPlanDetail,
			@WebParam(name = "operatorId") int operatorId) throws BussinessException;

	/**
	 * 修改海运备货计划
	 * 
	 * @param seaTransportationPlanDetail
	 * @param operatorId
	 */
	public void updateSeaTransportationPlan(
			@WebParam(name = "seaTransportationPlanVO") SeaTransportationPlanVO seaTransportationPlanVO,
			@WebParam(name = "operatorId") int operatorId) throws BussinessException;

	/**
	 * 手动增加的sku
	 * 
	 * @param skus
	 * @param planId
	 * @param operatorId
	 * @return
	 */
	public void addProduct(@WebParam(name = "skus") List<String> skus, @WebParam(name = "planId") int planId,
			@WebParam(name = "operatorId") int operatorId) throws BussinessException;

	/**
	 * 根据海运计划id查询计划详情，注意商品项要按照商品推荐类型排序
	 * 
	 * @param planId
	 * @return
	 * 
	 */
	public SeaTransportationPlanDetailVO getSeaTransportationPlanDetailByPlanId(@WebParam(name = "planId") int planId);

	/**
	 * 取消计划商品
	 * 
	 * @param planId
	 * @param skus
	 * @param operatorId
	 * @throws BussinessException
	 */
	public void cancelProducts(@WebParam(name = "planId") int planId, @WebParam(name = "skus") List<String> skus,@WebParam(name = "isCanceled")int isCanceled,
			@WebParam(name = "operatorId") int operatorId) throws BussinessException;

	/**
	 * 更新商品实际批量
	 * 
	 * @param planId
	 * @param sku
	 * @param actualQuantity
	 * @param operatorId
	 */
	public double updateProductActualQuantity(@WebParam(name = "planId") int planId, @WebParam(name = "sku") String sku,
			@WebParam(name = "actualQuantity") int actualQuantity, @WebParam(name = "operatorId") int operatorId);

	/**
	 * 根据计划id修改审核状态
	 * 
	 * @param planId
	 * @param operatorId
	 * @param status
	 * 
	 */
	public void updateCheckStatus(@WebParam(name = "planId") int planId, @WebParam(name = "operatorId") int operatorId,
			@WebParam(name = "status") int status);

	/**
	 * 获取海运补货计划日志
	 * 
	 * @param planId
	 * 
	 */
	public List<SeaTransportationPlanOperateLogVO> getSeaTransportationPlanOperateLogs(
			@WebParam(name = "planId") int planId);

	/**
	 * 双向增加日志记录
	 * 
	 * @param seaTransPlanId
	 * @param inboundPlanId
	 * @param operatorId
	 * 
	 */
	public void addSeaTransToInboundPlanLog(@WebParam(name = "seaTransPlanId") int seaTransPlanId,
			@WebParam(name = "inboundPlanId") int inboundPlanId, @WebParam(name = "operatorId") int operatorId);
}
