package com.sjtu.o2o.dao;

import com.sjtu.o2o.BaseTest;
import com.sjtu.o2o.entity.Area;
import com.sjtu.o2o.entity.PersonInfo;
import com.sjtu.o2o.entity.Shop;
import com.sjtu.o2o.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * @author ：Fang Jiangjing
 * @date ：Created in 2022/10/7 21:42
 * @description：
 * @modified By：
 * @version: $
 */
public class ShopDaoTest extends BaseTest {
    @Autowired
    private ShopDao shopDao;
    @Test
    public void testInsertShop(){
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
        shop.setShopAddr("test");
        shop.setShopName("test");
        shop.setShopDesc("test");
        shop.setPhone("13812345678");
        shop.setShopImg("test.img");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(1);
        shop.setAdvice("审核中");
        assertEquals(1,shopDao.insertShop(shop));
    }
    @Test
    public void testUpdateShop(){
        Shop shop = new Shop();
        shop.setShopId(2L);
        shop.setShopName("testUpdate");
        int i = shopDao.updateShop(shop);
        assertEquals(i,1);
    }

    @Test
    public void testQueryByShopId(){
        Shop shop = shopDao.queryByShopId(2L);
        System.out.println(shop);
    }
}
