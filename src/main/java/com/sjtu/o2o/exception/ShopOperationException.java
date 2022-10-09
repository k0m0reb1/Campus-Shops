package com.sjtu.o2o.exception;

import com.sjtu.o2o.enums.ShopStateEnum;

import java.io.Serializable;

/**
 * @author ：Fang Jiangjing
 * @date ：Created in 2022/10/8 15:50
 * @description：
 * @modified By：
 * @version: $
 */
public class ShopOperationException extends RuntimeException  {
    private static final long serialVersionUID = 1L;
    public ShopOperationException(String errMsg){
        super(errMsg);
    }
}
