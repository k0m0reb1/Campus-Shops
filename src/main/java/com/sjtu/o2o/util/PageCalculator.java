package com.sjtu.o2o.util;

/**
 * @author ：Fang Jiangjing
 * @date ：Created in 2022/10/12 22:52
 * @description：
 * @modified By：
 * @version: $
 */
public class PageCalculator {
    public static int calculateRowIndex(int pageIndex, int pageSize){
        return (pageIndex > 0) ? (pageIndex -1 ) * pageSize : 0;
    }
}
