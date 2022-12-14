package com.sjtu.o2o.web.superadmin;

import com.sjtu.o2o.entity.Area;
import com.sjtu.o2o.service.AreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：Fang Jiangjing
 * @date ：Created in 2022/10/3 04:43
 * @description：
 * @modified By：
 * @version: $
 */
@Controller
@RequestMapping("/superadmin")
public class AreaController {
    Logger logger = LoggerFactory.getLogger(AreaController.class);
    @Autowired
    private AreaService areaService;

    @RequestMapping("/listarea")
    @ResponseBody
    public Map<String,Object> listArea(){
        logger.info("=======start========");
        long startTime = System.currentTimeMillis();
        Map<String,Object> modelMap = new HashMap<String, Object>();
        List<Area> list = new ArrayList<Area>();
        try{
            list = areaService.getAreaList();
            modelMap.put("rows",list);
            modelMap.put("total",list.size());
        } catch (Exception e){
            e.printStackTrace();
            modelMap.put("success",false);
            modelMap.put("errMsg",e.toString());
        }
        logger.debug("costTime:{}ms",System.currentTimeMillis() - startTime);
        logger.info("======end======");
        return modelMap;
    }
}
