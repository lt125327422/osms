package com.itecheasy.core.fba;

import java.util.List;

/**
 * @Auther: liteng
 * @Date: 2018/7/4 08:46
 * @Description:
 */
public class UpdateBlacklistVO {

    private int shopId;
    private List<String> skuList;   //二选一
//    private String [] skus;        //二选一
    private int seaTransportationBlacklistStatus;    //海运黑名单


    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public List<String> getSkuList() {
        return skuList;
    }

    public void setSkuList(List<String> skuList) {
        this.skuList = skuList;
    }

    public int getSeaTransportationBlacklistStatus() {
        return seaTransportationBlacklistStatus;
    }

    public void setSeaTransportationBlacklistStatus(int seaTransportationBlacklistStatus) {
        this.seaTransportationBlacklistStatus = seaTransportationBlacklistStatus;
    }
}
