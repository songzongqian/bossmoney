package com.byx.xiuboss.xiuboss.Jgim.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by ASUS on 2018/7/23.
 */

public class ToastUtil {
    public static void shortToast(Context context, String desc) {
        Toast.makeText(context, desc, Toast.LENGTH_SHORT).show();
    }
}
