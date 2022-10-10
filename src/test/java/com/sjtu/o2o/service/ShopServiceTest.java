package com.sjtu.o2o.service;

import com.sjtu.o2o.BaseTest;
import com.sjtu.o2o.dto.ShopExecution;
import com.sjtu.o2o.entity.Area;
import com.sjtu.o2o.entity.PersonInfo;
import com.sjtu.o2o.entity.Shop;
import com.sjtu.o2o.entity.ShopCategory;
import com.sjtu.o2o.enums.ShopStateEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * @author ：Fang Jiangjing
 * @date ：Created in 2022/10/8 16:03
 * @description：
 * @modified By：
 * @version: $
 */
public class ShopServiceTest extends BaseTest {
    @Autowired
    private ShopService shopService;
    @Test
    public void testAddShop() throws FileNotFoundException {
        Shop shop = new Shop();
        PersonInfo owner = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();
        owner.setUserId(1L);
        area.setAreaId(2);
        shopCategory.setShopCategoryId(1L);
        shop.setOwner(owner);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopAddr("test1");
        shop.setShopName("test1");
        shop.setShopDesc("test1");
        shop.setPhone("13812345679");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(1);
        shop.setAdvice("审核中");
        File shopImg = new File("/Users/Wall-f/codeTest/test.jpg");
        InputStream shopImgInputStream = new FileInputStream(shopImg);
        ShopExecution shopExecution = shopService.addShop(shop, shopImgInputStream, shopImg.getName());
        assertEquals(ShopStateEnum.CHECK.getState(),0);
    }

    @Test
    public void testModifyShop() throws FileNotFoundException {
        Shop shop = new Shop();
        shop.setShopId(12L);
        shop.setShopName("修改后的店铺名称");
        File shopImg = new File("/Users/Wall-f/codeTest/modify.jpg");
        ShopExecution shopExecution = shopService.modifyShop(shop,new FileInputStream(shopImg),shopImg.getName());
        System.out.println(shopExecution.getShop().getShopImg());
    }
}
