package com.itecheasy.core.system.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.itecheasy.common.BaseDAOImpl;
import com.itecheasy.common.util.BeanUtils;
import com.itecheasy.common.util.CollectionUtils;
import com.itecheasy.core.po.ShopPo;
import com.itecheasy.core.system.Shop;

/** 
 * @author wanghw
 * @date 2015-3-26 
 * @description TODO
 * @version
 */
public class ShopDaoImpl extends BaseDAOImpl<ShopPo, Integer> implements ShopDao{
	private List<Shop> shops;
	
	public List<Shop> getShops() {
		return shops;
	}

	public void setShops(List<Shop> shops) {
		this.shops = shops;
	}

	@Override
	public Integer addObject(ShopPo entity) {
		Integer id= super.addObject(entity);
		shops=getEnableShops();
		return id;
	}
	
	@Override
	public void mergeObject(ShopPo entity) {
		super.mergeObject(entity);
		shops=getEnableShops();
	}
	
	public List<Shop> getEnableShops(){
		String hql = "select s from ShopPo s where enable=1";
		List<ShopPo> pos = findListByHql(hql);
		if (CollectionUtils.isNotEmpty(pos))
			return BeanUtils.copyList(pos, Shop.class);
		return null;
	}

	@Override
	public void setFbaFromAddressForShop(final int shopId,final int fbaFromAddressId) {
		this.getHibernateTemplate().execute(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				SQLQuery query = session.createSQLQuery("update shop set fba_from_address_id = ? where id = ? ");
				query.setParameter(0, fbaFromAddressId);
				query.setParameter(1, shopId);
				query.executeUpdate();
				return null;
			}
		});
	}
	
}