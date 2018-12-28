package com.byx.xiuboss.xiuboss.Utils;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

public class Base64Utils {
     // 加密  
    public static String getBase64(String str) {  
        String result = "";  
        if( str != null) {
             try {  
                result = new String(Base64.encode(str.getBytes("utf-8"), Base64.NO_WRAP),"utf-8");
             } catch (UnsupportedEncodingException e) {
                 e.printStackTrace();  
             }  
        }
        return result;  
    }  

    // 解密  
    public static String getFromBase64(String str) {  
        String result = "";  
        if (str != null) {  
            try {
                result = new String(Base64.decode(str, Base64.NO_WRAP), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }  
        }  
        return result;  
    }

    public static String MD5(String sourceStr) {
        try {
            // 获得MD5摘要算法的 MessageDigest对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(sourceStr.getBytes());
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            StringBuffer buf = new StringBuffer();
            for (int i = 0; i < md.length; i++) {
                int tmp = md[i];
                if (tmp < 0)
                    tmp += 256;
                if (tmp < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(tmp));
            }
            //return buf.toString().substring(8, 24);// 16位加密
            return buf.toString().toUpperCase();// 32位大加密
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}