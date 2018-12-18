package com.byx.xiuboss.xiuboss.Utils;

import android.content.Context;
import android.view.WindowManager;

/**
 * Created by wangwenjie001 on 2018/9/3.
 */

public class EasyUtil {
    /**
     * 获取屏幕宽度
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        return width;
    }
}
