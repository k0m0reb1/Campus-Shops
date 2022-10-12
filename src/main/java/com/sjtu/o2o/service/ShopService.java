package com.sjtu.o2o.service;

import com.sjtu.o2o.dto.ShopExecution;
import com.sjtu.o2o.entity.Shop;

import java.io.InputStream;

/**
 * @author ：Fang Jiangjing
 * @date ：Created in 2022/10/8 15:16
 * @description：
 * @modified By：
 * @version: $
 */
public interface ShopService {
    /**
     * 注册店铺信息，包括图片处理
     * @param shop
     * @param ShopImgInputStream 图片流
     * @param fileName 图片名称
     * @return
     */
    public ShopExecution addShop(Shop shop, InputStream ShopImgInputStream, String fileName);

    /**
     * 通过店铺ID获取店铺信息
     * @param shopId
     * @return
     */
    Shop getByShopId(long shopId);

    /**
     * 更新店铺信息，包括对图片的处理
     * @param shop
     * @param shopImgInputStream
     * @param fileName
     * @return
     */
    ShopExecution modifyShop(Shop shop, InputStream shopImgInputStream, String fileName);

    /**
     * 根据shopCondition分页返回相应店铺列表
     * @param shopCondition
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize);
}
