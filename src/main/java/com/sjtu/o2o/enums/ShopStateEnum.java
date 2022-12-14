package com.sjtu.o2o.enums;

/**
 * @author ：Fang Jiangjing
 * @date ：Created in 2022/10/8 14:19
 * @description：
 * @modified By：
 * @version: $
 */
public enum ShopStateEnum {
    CHECK(0,"审核中"),
    OFFLINE(-1,"非法店铺"),
    SUCCESS(1,"操作成功"),
    PASS(2,"通过认证"),
    INNER_ERROR(-1001,"内部错误"),
    NULL_SHOPID(-1002,"shopId为空"),
    NULL_SHOP(-1003,"shop信息为空");
    private int state;
    private String stateInfo;

    private ShopStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public static ShopStateEnum stateOf(int state){
        for(ShopStateEnum stateEnum : values()){
            if(stateEnum.state == state){
                return stateEnum;
            }
        }
        return null;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }
}
