package com.byx.xiuboss.xiuboss.Utils;

import android.view.View;


import java.util.Collections;
import java.util.Comparator;
import java.util.List;
/**
 * Created by wangwenjie001 on 2018/10/8.
 */

public class CommonUtil {
    /**
     * 测量View的宽高
     *
     * @param view View
     */
    public static void measureWidthAndHeight(View view) {
        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(widthMeasureSpec, heightMeasureSpec);
    }

}
