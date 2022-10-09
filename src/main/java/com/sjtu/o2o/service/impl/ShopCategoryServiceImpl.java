package com.sjtu.o2o.service.impl;

import com.sjtu.o2o.dao.ShopCategoryDao;
import com.sjtu.o2o.entity.ShopCategory;
import com.sjtu.o2o.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：Fang Jiangjing
 * @date ：Created in 2022/10/9 10:01
 * @description：
 * @modified By：
 * @version: $
 */
@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {
    @Autowired
    private ShopCategoryDao shopCategoryDao;
    @Override
    public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition) {
        return shopCategoryDao.queryShopCategory(shopCategoryCondition);
    }
}
