package com.sjtu.o2o.dao;

import com.sjtu.o2o.BaseTest;
import com.sjtu.o2o.entity.Area;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author ：Fang Jiangjing
 * @date ：Created in 2022/10/3 03:29
 * @description：
 * @modified By：
 * @version: $
 */
public class AreaDaoTest extends BaseTest {
    @Resource
    private AreaDao areaDao;

    @Test
    public void testQueryArea(){
        List<Area> areaList = areaDao.queryArea();
        assertEquals(2,areaList.size());
    }
}
