package com.sjtu.o2o.util;

/**
 * @author ：Fang Jiangjing
 * @date ：Created in 2022/10/8 12:48
 * @description：
 * @modified By：
 * @version: $
 */
public class PathUtil {
    /**
     * 文件分隔符
     */
    public static String separator = System.getProperty("file.separator");

    /**
     * @return 返回项目图片根路径
     */
    public static String getImgBasePath(){
        //系统名称
        String os = System.getProperty("os.name");
        String basePath = "";
        if(os.toLowerCase().startsWith("win")){
            basePath = "D:/projectdev/image/";
        } else{
            basePath = "/Users/wall-f/codeTest/campus_shop_images/";
        }
        basePath = basePath.replace("/",separator);
        return basePath;
    }

    /**
     * @param shopId
     * @return 店铺图片路径
     */
    public static String getShopImgPath(long shopId){
        String imagePath = "upload/item/shop/" + shopId + "/";
        return imagePath.replace("/",separator);
    }
}
