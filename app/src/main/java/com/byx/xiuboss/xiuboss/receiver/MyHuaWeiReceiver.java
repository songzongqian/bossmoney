package com.byx.xiuboss.xiuboss.receiver;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.huawei.hms.support.api.push.PushReceiver;

import java.io.UnsupportedEncodingException;

public class MyHuaWeiReceiver extends PushReceiver {



    @Override
    public boolean onPushMsg(Context context, byte[] msg, Bundle extras) {
        try {
            String content = new String(msg, "UTF-8");
            System.out.println("华为推送的内容1"+content);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return false;
    }


    @Override
    public void onPushMsg(Context context, byte[] msg, String token) {
        try {
            String content = new String(msg, "UTF-8");
            System.out.println("华为推送的内容2"+content);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取推送设备的token
     */

    @Override
    public void onToken(Context context, String token, Bundle extras) {
        super.onToken(context, token, extras);
        System.out.println("项目token"+token);
    }

    /**
     * 接收通知栏消息
     */
    @Override
    public void onEvent(Context context, Event event, Bundle extras) {

        int notifyId = extras.getInt(BOUND_KEY.pushNotifyId, 0);
        String message = extras.getString(BOUND_KEY.pushMsgKey);
        Log.i("bqt", "华为【onEvent】event=" + event + "  notifyId=" + notifyId + "  message=" + message);
        System.out.println("推送的内容"+message);

        if (Event.NOTIFICATION_OPENED.equals(event) || Event.NOTIFICATION_CLICK_BTN.equals(event)) {
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            if (0 != notifyId && manager != null){
                manager.cancel(notifyId);
            }
        }
        super.onEvent(context, event, extras);
    }

    /**
     * 获取华为推送的连接状态
     */
    @Override
    public void onPushState(Context context, boolean pushState) {
        super.onPushState(context, pushState);
    }
}
