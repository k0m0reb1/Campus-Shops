package com.sjtu.o2o.service;

import com.sjtu.o2o.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

/**
 * @author ：Fang Jiangjing
 * @date ：Created in 2022/10/3 04:40
 * @description：
 * @modified By：
 * @version: $
 */
public class AreaServiceTest extends BaseTest {
    @Autowired
    private AreaService areaService;
    @Test
    public void testGetAreaList(){
        assertEquals(2,areaService.getAreaList().size());
    }
}
