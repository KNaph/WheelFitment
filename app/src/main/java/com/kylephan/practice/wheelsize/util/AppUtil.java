package com.kylephan.practice.wheelsize.util;

import android.content.Context;
import android.util.TypedValue;

public class AppUtil implements Util {

    private static volatile AppUtil instance;

    public static AppUtil getInstance() {
        if (instance == null) {
            synchronized (AppUtil.class) {
                if (instance == null)
                    instance = new AppUtil();
            }
        }
        return instance;
    }

    private AppUtil() {}

    @Override
    public int dp2px(Context context, float dip) {
        int value = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, context.getResources().getDisplayMetrics());
        return value;
    }
}
