package com.sjtu.o2o.dao;

import com.sjtu.o2o.entity.ShopCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ：Fang Jiangjing
 * @date ：Created in 2022/10/8 22:34
 * @description：
 * @modified By：
 * @version: $
 */
public interface ShopCategoryDao {
    List<ShopCategory> queryShopCategory(@Param("shopCategoryCondition")ShopCategory shopCategoryCondition);
}
