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
    public ShopExecution addShop(Shop shop, InputStream ShopImgInputStream, String fileName);
}
