/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.itecheasy.osms.prepare;

import com.itecheasy.common.PageList;
import com.itecheasy.core.po.SuperLongPrepareProductPO;
import com.itecheasy.core.prepare.IsExistSuperLongPrepareProductDataBase;
import com.itecheasy.core.prepare.SuperLongPrepareProductSearchForm;
import com.itecheasy.core.prepare.SuperLongPrepareProductService;
import com.itecheasy.core.prepare.SuperLongPrepareProductVO;

import java.util.List;

/**
 * @Auther: liteng
 * @Date: 2018/8/24 16:12
 * @Description:
 */
public class SuperLongPrepareProductWebServiceImpl implements SuperLongPrepareProductWebService {

    /**
     *  shop_product_cms_info
     */

    private SuperLongPrepareProductService superLongPrepareProductService;

    public void setSuperLongPrepareProductService(SuperLongPrepareProductService superLongPrepareProductService) {
        this.superLongPrepareProductService = superLongPrepareProductService;
    }





    @Override
    public PageList<SuperLongPrepareProductVO> showSuperLongPrepareProduct(SuperLongPrepareProductSearchForm superLongPrepareProductSearchForm) {
        return superLongPrepareProductService.showSuperLongPrepareProduct(superLongPrepareProductSearchForm);
    }

    @Override
    public IsExistSuperLongPrepareProductDataBase checkRepeatCmsCode(List<String> cmsCodes, Integer operatorId) {
        return superLongPrepareProductService.checkRepeatCmsCode(cmsCodes, operatorId);
    }

    @Override
    public List<Integer> addSuperLongPrepareProducts(List<String> cmsCodes, Integer operatorId) {
        List<Integer> integers = superLongPrepareProductService.addSuperLongPrepareProducts(cmsCodes, operatorId);
        return integers;
    }



    @Override
    public List<Integer> deleteSuperLongPrepareProducts( List<String> cmsCodes, Integer operatorId) {
        List<Integer> integers = superLongPrepareProductService.deleteSuperLongPrepareProducts(cmsCodes, operatorId);
        return integers;
    }

    @Override
    public List<SuperLongPrepareProductPO> getSuperLongPrepareProductById(List<Integer> ids, Integer operatorId) {
        return superLongPrepareProductService.getSuperLongPrepareProductById(ids, operatorId);
    }
}