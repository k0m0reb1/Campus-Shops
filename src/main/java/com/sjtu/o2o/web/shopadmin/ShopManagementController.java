package com.sjtu.o2o.web.shopadmin;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sjtu.o2o.dto.ShopExecution;
import com.sjtu.o2o.entity.Area;
import com.sjtu.o2o.entity.PersonInfo;
import com.sjtu.o2o.entity.Shop;
import com.sjtu.o2o.entity.ShopCategory;
import com.sjtu.o2o.enums.ShopStateEnum;
import com.sjtu.o2o.service.AreaService;
import com.sjtu.o2o.service.ShopCategoryService;
import com.sjtu.o2o.service.ShopService;
import com.sjtu.o2o.util.CodeUtil;
import com.sjtu.o2o.util.HttpServletRequestUtil;
import com.sun.media.sound.ModelDestination;
import com.sun.org.apache.bcel.internal.generic.IFNULL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.print.MultiDoc;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：Fang Jiangjing
 * @date ：Created in 2022/10/8 17:10
 * @description：
 * @modified By：
 * @version: $
 */
@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController {
    @Autowired
    private ShopService shopService;
    @Autowired
    private ShopCategoryService shopCategoryService;
    @Autowired
    private AreaService areaService;

    @RequestMapping("/getshopinitinfo")
    @ResponseBody
    public Map<String, Object> getShopInitInfo() {
        Map<String, Object> modelMap = new HashMap<>(10);
        List<ShopCategory> shopCategoryList = new ArrayList<>();
        List<Area> areaList = new ArrayList<>();
        try {
            shopCategoryList = shopCategoryService.getShopCategoryList(new ShopCategory());
            areaList = areaService.getAreaList();
            modelMap.put("shopCategoryList", shopCategoryList);
            modelMap.put("areaList", areaList);
            modelMap.put("success", true);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
        }
        return modelMap;

    }

    @RequestMapping(value = "/registershop", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> registerShop(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>(10);
        if (!CodeUtil.checkVerifyCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "验证码错误！");
            return modelMap;
        }
        //1.接收并转化相应的参数，包括店铺信息以及图片信息
        String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
        ObjectMapper mapper = new ObjectMapper();
        Shop shop = null;
        try {
            shop = mapper.readValue(shopStr, Shop.class);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
            return modelMap;
        }

        //处理图片
        CommonsMultipartFile shopImg = null;
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (commonsMultipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "上传图片不能为空");
            return modelMap;
        }
        //2.注册店铺
        if (shop != null && shopImg != null) {
            PersonInfo owner = (PersonInfo) request.getSession().getAttribute("user");
            shop.setOwner(owner);
            ShopExecution se = null;
            try {
                se = shopService.addShop(shop, shopImg.getInputStream(), shopImg.getOriginalFilename());
                //该用户可以操作的店铺列表
                List<Shop> shopList = (List<Shop>)request.getSession().getAttribute("shopList");
                if(shopList == null || shopList.size() == 0){
                    shopList = new ArrayList<>();
                }
                shopList.add(se.getShop());
                request.getSession().setAttribute("shopList",shopList);
            } catch (IOException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.getMessage());
                return modelMap;
            }
            if (se.getState() == ShopStateEnum.CHECK.getState()) {
                modelMap.put("success", true);
            } else {
                modelMap.put("success", false);
                modelMap.put("errMsg", se.getStateInfo());
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请输入店铺信息~");
        }
        return modelMap;
    }
    @RequestMapping(value = "/getshopbyid",method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getShopById(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        Long shopId = HttpServletRequestUtil.getLong(request, "shopId");
        if (shopId > -1) {
            try {
                Shop shop = shopService.getByShopId(shopId);
                List<Area> areaList = areaService.getAreaList();
                modelMap.put("shop", shop);
                modelMap.put("areaList", areaList);
                modelMap.put("success", true);
            } catch (Exception e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.toString());
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "shopId为空");
        }
        return modelMap;
    }
//    private static void inputStreamToFile(InputStream ins, File file) {
//        try (FileOutputStream os = new FileOutputStream(file); InputStream newIns = ins) {
//            int bytesRead = 0;
//            byte[] buffer = new byte[1024];
//            while ((bytesRead = newIns.read(buffer)) != -1) {
//                os.write(buffer, 0, bytesRead);
//            }
//        } catch (Exception e) {
//            throw new RuntimeException("调用inputStreamToFile产生异常:" + e.getMessage());
//        }
//    }
@RequestMapping(value = "/modifyshop", method = RequestMethod.POST)
@ResponseBody
private Map<String, Object> modifyShop(HttpServletRequest request) {
    Map<String, Object> modelMap = new HashMap<>(10);
    if (!CodeUtil.checkVerifyCode(request)) {
        modelMap.put("success", false);
        modelMap.put("errMsg", "验证码错误！");
        return modelMap;
    }
    //1.接收并转化相应的参数，包括店铺信息以及图片信息
    String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
    ObjectMapper mapper = new ObjectMapper();
    Shop shop = null;
    try {
        shop = mapper.readValue(shopStr, Shop.class);
    } catch (Exception e) {
        modelMap.put("success", false);
        modelMap.put("errMsg", e.getMessage());
        return modelMap;
    }

    //处理图片
    CommonsMultipartFile shopImg = null;
    CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
    if (commonsMultipartResolver.isMultipart(request)) {
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
    }
    //2.修改店铺信息
    if (shop != null && shop.getShopId() != null) {
        ShopExecution se = null;
        try {
            if (shopImg == null) {
                se = shopService.modifyShop(shop,null,null);
            }else{
                se = shopService.modifyShop(shop, shopImg.getInputStream(), shopImg.getOriginalFilename());
            }
        } catch (IOException e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
            return modelMap;
        }
        if (se.getState() == ShopStateEnum.SUCCESS.getState()) {
            modelMap.put("success", true);
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", se.getStateInfo());
        }
    } else {
        modelMap.put("success", false);
        modelMap.put("errMsg", "请输入店铺ID~");
    }
    return modelMap;
}
}
