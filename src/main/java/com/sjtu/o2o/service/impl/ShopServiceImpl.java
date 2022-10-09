package com.sjtu.o2o.service.impl;

import com.sjtu.o2o.dao.ShopDao;
import com.sjtu.o2o.dto.ShopExecution;
import com.sjtu.o2o.entity.Shop;
import com.sjtu.o2o.enums.ShopStateEnum;
import com.sjtu.o2o.exception.ShopOperationException;
import com.sjtu.o2o.service.ShopService;
import com.sjtu.o2o.util.ImgUtil;
import com.sjtu.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.InputStream;
import java.util.Date;

/**
 * @author ：Fang Jiangjing
 * @date ：Created in 2022/10/8 15:25
 * @description：
 * @modified By：
 * @version: $
 */
@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopDao shopDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName) {
        //空值判断
        if (shop == null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }
        try{
            //给店铺信息赋初始值
            shop.setEnableStatus(0);
            shop.setCreateTime(new Date());
            shop.setLastEditTime(new Date());
            int effectNum = shopDao.insertShop(shop);
            if(effectNum <= 0){
                throw new ShopOperationException("店铺创建失败");
            }else{
                if(shopImgInputStream != null){
                    //存储图片
                    try{
                        addShopImg(shop, shopImgInputStream,fileName);
                    } catch (Exception e){
                        throw new ShopOperationException("更新店铺图片错误："+e.getMessage());
                    }
                    int updEffectNum = shopDao.updateShop(shop);
                    if(updEffectNum <= 0){
                        throw new ShopOperationException("更新店铺图片错误");
                    }
                }
            }
        } catch (Exception e){
            throw new ShopOperationException("注册店铺异常！");
        }
        return new ShopExecution(ShopStateEnum.CHECK,shop);
    }
    public void addShopImg(Shop shop, InputStream shopImgInputStream, String fileName){
        //获取shop图片目录的相对值路径
        String dest = PathUtil.getShopImgPath(shop.getShopId());
        String shopImgAddr = ImgUtil.generateThumbnail(shopImgInputStream,fileName,dest);
        shop.setShopImg(shopImgAddr);
    }
}
