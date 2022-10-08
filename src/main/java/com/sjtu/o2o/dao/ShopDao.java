package com.sjtu.o2o.dao;

import com.sjtu.o2o.entity.Shop;

/**
 * @author ：Fang Jiangjing
 * @date ：Created in 2022/10/7 21:18
 * @description：
 * @modified By：
 * @version: $
 */
public interface ShopDao {
    /**
     * 新增店铺
     * @param shop
     * @return
     */
    int insertShop(Shop shop);
    int updateShop(Shop shop);
}
