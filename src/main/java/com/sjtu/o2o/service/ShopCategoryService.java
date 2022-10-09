package com.sjtu.o2o.service;


import com.sjtu.o2o.entity.ShopCategory;

import java.util.List;

/**
 * @author ：Fang Jiangjing
 * @date ：Created in 2022/10/9 09:59
 * @description：
 * @modified By：
 * @version: $
 */
public interface ShopCategoryService {
    List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition);
}
