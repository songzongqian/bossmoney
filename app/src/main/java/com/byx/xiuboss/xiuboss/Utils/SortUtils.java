package com.byx.xiuboss.xiuboss.Utils;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class SortUtils {

     List <String> strList= new ArrayList<>();

    public void  getSign(Map<String,String> map, String [] str,String yan){
        Arrays.sort(str);
        for(int k=0;k<str.length;k++){
            strList.add(map.get(str[k]));
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String format = df.format(new Date());
        strList.add(format);
        strList.add(yan);
        String [] array = strList.toArray(new String[strList.size()]);
        StringBuffer sb = new StringBuffer();
        for(int k=0;k<array.length;k++){
            sb.append(array[k]);
        }

        //对字符串进行MD532位加密
    }

    }

