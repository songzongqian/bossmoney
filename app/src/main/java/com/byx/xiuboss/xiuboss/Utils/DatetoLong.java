package com.byx.xiuboss.xiuboss.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DatetoLong {

    private static long time1;

    public static String getLong(String time){
        try{
            SimpleDateFormat sdf =   new SimpleDateFormat( "yyyyMMdd" );
            Date date= sdf.parse(time);
            time1 = date.getTime();
        }catch(Exception e){
            e.printStackTrace();
        }

        return  time1+"";
    }
}
