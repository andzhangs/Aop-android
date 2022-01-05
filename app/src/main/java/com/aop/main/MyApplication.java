package com.aop.main;

import android.app.Application;
import android.content.Context;

/**
 * Code of ZHANG/ 2018/11/8
 */
public class MyApplication extends Application {

    private static MyApplication mApplication=null;
    @Override
    public void onCreate() {
        super.onCreate();
        mApplication=this;
        SharedPreferencesHelper.init(this,this.getPackageName(),Context.MODE_PRIVATE);
    }
    public static synchronized MyApplication getInstance(){
        return mApplication;
    }
}
