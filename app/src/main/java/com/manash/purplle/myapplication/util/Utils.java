package com.manash.purplle.myapplication.util;

import android.content.Context;

public class Utils {


    public static int getDeviceWith(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;

    }
}
