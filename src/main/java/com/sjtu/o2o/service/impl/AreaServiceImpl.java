package com.sjtu.o2o.service.impl;

import com.sjtu.o2o.dao.AreaDao;
import com.sjtu.o2o.entity.Area;
import com.sjtu.o2o.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author ：Fang Jiangjing
 * @date ：Created in 2022/10/3 04:36
 * @description：
 * @modified By：
 * @version: $
 */
@Service
public class AreaServiceImpl implements AreaService {
    @Autowired
    private AreaDao areaDao;

    public List<Area> getAreaList() {
        return areaDao.queryArea();
    }

}
