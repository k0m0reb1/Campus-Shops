package com.sjtu.o2o.util;

import com.google.code.kaptcha.Constants;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ：Fang Jiangjing
 * @date ：Created in 2022/10/9 15:12
 * @description：
 * @modified By：
 * @version: $
 */
public class CodeUtil {
    public static boolean checkVerifyCode(HttpServletRequest request){
        String verifyCodeExpected = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        String verifyCodeActual = HttpServletRequestUtil.getString(request,"verifyCodeActual");
        if(verifyCodeActual == null || !verifyCodeActual.equals(verifyCodeExpected)){
            return true;
        }else {
            return false;
        }
    }
}
