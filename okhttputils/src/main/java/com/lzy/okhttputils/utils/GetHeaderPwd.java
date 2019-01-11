package com.lzy.okhttputils.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class GetHeaderPwd {

    private static List<String> strList;

    public static String getMd5(Map<String,String> map, String[] array,String time){
        if(strList!=null){
            strList.clear();
        }
        strList = new ArrayList<>();
        Arrays.sort(array);//对数组进行排序

        for(int k=0;k<array.length;k++){
            //System.out.println("排序的Key"+"  "+array[k]+"对应的value"+" "+map.get(array[k]));
            strList.add(map.get(array[k]));
        }
        strList.add(time);
        strList.add("65298da12c499300");//添加secret
        //把集合转化为数组
        String[] array1 = strList.toArray(new String[strList.size()]);
        //把Value值数组转化为字符串
        StringBuffer sb = new StringBuffer();
        for(int x=0;x<array1.length;x++){
            sb.append(array1[x]);
        }
        String valueStr = sb.toString();
       // System.out.println("需要机密的字符串"+valueStr);
        String headerMd5 = Base64Utils.MD5(valueStr);
        //System.out.println("加密后的header"+headerMd5);
        return  headerMd5;
    }




    public static String getMd5(Map<String,String> map, String[] array){
        if(strList!=null){
            strList.clear();
        }
        strList = new ArrayList<>();
        Arrays.sort(array);//对数组进行排序

        for(int k=0;k<array.length;k++){
            strList.add(map.get(array[k]));
        }

        strList.add("65298da12c499300");//添加secret
        //把集合转化为数组
        String[] array1 = strList.toArray(new String[strList.size()]);
        //把Value值数组转化为字符串
        StringBuffer sb = new StringBuffer();
        for(int x=0;x<array1.length;x++){
            sb.append(array1[x]);
        }
        String valueStr = sb.toString();
       // System.out.println("需要机密的字符串"+valueStr);
        String headerMd5 = Base64Utils.MD5(valueStr);
       // System.out.println("加密后的header"+headerMd5);
        return  headerMd5;
    }

    public static  String getTimeFlag(){
        long time=System.currentTimeMillis()/1000;//获取系统时间的10位的时间戳
        String  xxx=String.valueOf(time);
       // System.out.println("时间戳"+xxx);
        return xxx;
    }

}
