package com.sjtu.o2o.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ：Fang Jiangjing
 * @date ：Created in 2022/10/8 17:14
 * @description：
 * @modified By：
 * @version: $
 */
public class HttpServletRequestUtil {
    private static final Logger logger = LoggerFactory.getLogger(HttpServletRequestUtil.class);

    public static int getInt(HttpServletRequest request,String key){
        try{
            return Integer.decode(request.getParameter(key));
        }catch (Exception e){
            logger.error("提取[{}]的整型值失败！！！",key);
            logger.error(e.getMessage());
            return -1;
        }
    }
    public static Long getLong(HttpServletRequest request,String key){
        try{
            return Long.valueOf(request.getParameter(key));
        }catch (Exception e){
            logger.error("提取[{}]的Long值失败！！！",key);
            logger.error(e.getMessage());
            return -1L;
        }
    }
    public static Double getDouble(HttpServletRequest request,String key){
        try{
            return Double.valueOf(request.getParameter(key));
        }catch (Exception e){
            logger.error("提取[{}]的Double值失败！！！",key);
            logger.error(e.getMessage());
            return -1d;
        }
    }
    public static Boolean getBoolean(HttpServletRequest request,String key){
        try{
            return Boolean.valueOf(request.getParameter(key));
        }catch (Exception e){
            logger.error("提取[{}]的Boolean值失败！！！",key);
            logger.error(e.getMessage());
            return false;
        }
    }
    public static String getString(HttpServletRequest request,String key){
        try{
            String result = request.getParameter(key);
            if(result != null){
                result = result.trim();
            }
            if("".equals(result)){
                result = null;
            }
            return result;
        }catch (Exception e){
            logger.error("提取[{}]的String值失败！！！",key);
            logger.error(e.getMessage());
            return null;
        }
    }
}
