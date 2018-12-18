package com.byx.xiuboss.xiuboss.Application;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;


import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;

import java.util.ArrayList;
import java.util.List;

import cn.jiguang.share.android.api.JShareInterface;
import cn.jiguang.share.android.api.PlatformConfig;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.model.Message;


/**
 * Created by wangwenjie001 on 2018/9/13.
 */

public class JgApplication extends MultiDexApplication {
    public static String PICTURE_DIR = "sdcard/JChatDemo/pictures/";
    public static long registerOrLogin = 1;
    public static List<Message> forwardMsg = new ArrayList<>();
    public static Context context;
    public static final String TARGET_ID = "targetId";
    public static final String TARGET_APP_KEY = "targetAppKey";
    public static final String CONV_TITLE = "conv_title";
    public static final int SCREEN_HEIGHT = 0;
    public static final int SCREEN_WIDTH = 0;
    @Override
    public void onCreate(){
        super.onCreate();
        context= getApplicationContext();
        JShareInterface.setDebugMode(true);
        JPushInterface.setDebugMode(true);
        JMessageClient.setDebugMode(true);
        JMessageClient.init(this);
        JPushInterface.init(this);
        SpeechUtility.createUtility(context, SpeechConstant.APPID +"=5b508ff4");
        PlatformConfig platformConfig= new PlatformConfig().setWechat("wxf184832284668a05","c66d35ff38e4c19b9c5b546f8bc35e84");
        JShareInterface.init(this,platformConfig);
        MultiDex.install(this); // Enable MultiDex.
    }


}
