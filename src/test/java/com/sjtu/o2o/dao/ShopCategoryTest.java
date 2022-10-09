package com.sjtu.o2o.dao;

import com.sjtu.o2o.BaseTest;
import com.sjtu.o2o.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author ：Fang Jiangjing
 * @date ：Created in 2022/10/9 09:54
 * @description：
 * @modified By：
 * @version: $
 */
public class ShopCategoryTest extends BaseTest {
    @Autowired
    private ShopCategoryDao shopCategoryDao;
    @Test
    public void testQueryShopCategory(){
        List<ShopCategory> shopCategories = shopCategoryDao.queryShopCategory(new ShopCategory());
        assertEquals(1,shopCategories.size());
    }
}
