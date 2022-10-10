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

    /**
     * 更新店铺信息
     * @param shop
     * @return
     */
    int updateShop(Shop shop);

    /**
     * 通过shopId查找店铺
     * @param shopId
     * @return
     */
    Shop queryByShopId(long shopId);
}
