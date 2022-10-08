package com.sjtu.o2o.entity;

import java.util.Date;

/**
 * @author ：Fang Jiangjing
 * @date ：Created in 2022/9/19 21:44
 * @description：商品类别
 * @modified By：
 * @version: $
 */
public class ProductCategory {
    private Long productCategoryId;
    private Long shopId;
    /**
     * 商品类别名称
     */
    private String productCategoryName;
    /**
     * 商品类别权重
     */
    private Integer priority;
    private Date createTime;

    public Long getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getProductCategoryName() {
        return productCategoryName;
    }

    public void setProductCategoryName(String productCategoryName) {
        this.productCategoryName = productCategoryName;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
