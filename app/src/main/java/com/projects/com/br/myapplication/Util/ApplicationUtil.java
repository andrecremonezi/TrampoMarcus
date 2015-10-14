package com.projects.com.br.myapplication.Util;

import android.content.Context;

public class ApplicationUtil {

    private static Context applicationContext;

    private ApplicationUtil(){
        super();
    }

    public static Context getApplicationContext() {
        return applicationContext;
    }

    public static void setApplicationContext(Context applicationContext) {
        ApplicationUtil.applicationContext = applicationContext;
    }
}