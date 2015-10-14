package com.projects.com.br.myapplication.Manager;

import android.app.Application;

import com.projects.com.br.myapplication.Util.ApplicationUtil;

/**
 * Created by c1284528 on 14/10/2015.
 */
public class AplicationManager extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationUtil.setApplicationContext(getApplicationContext());
    }
}
