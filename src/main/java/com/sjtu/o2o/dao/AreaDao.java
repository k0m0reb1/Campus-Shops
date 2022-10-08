package com.sjtu.o2o.dao;

import com.sjtu.o2o.entity.Area;

import java.util.List;

/**
 * @author ：Fang Jiangjing
 * @date ：Created in 2022/10/3 03:17
 * @description：
 * @modified By：
 * @version: $
 */
public interface AreaDao {
    /**
     * 列出区域列表
     * @return
     */
    List<Area> queryArea();
}
