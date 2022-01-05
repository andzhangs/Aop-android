package com.aopaspectj.android.aop.app;

import android.app.Application;
import android.util.Log;

import com.aopaspectj.android.aop.aop.TrackPoint;
import com.aopaspectj.android.aop.aop.TrackPointAspect;
import com.aopaspectj.android.aop.aop.TrackPointCallBack;

/**
 * @Author zhangshuai
 * @Date 2022/1/5
 * @Emial zhangshuai@dushu365.com
 * @Description
 */
public class TrackApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        TrackPoint.init(new TrackPointCallBack() {
            @Override
            public void onClick(final String pageClassName, final String viewIdName) {
                Log.i(TrackPointAspect.TAG, "onClick: "+pageClassName+", "+viewIdName);
            }

            @Override
            public void onPageOpen(final String pageClassName) {
                Log.i(TrackPointAspect.TAG, "onPageOpen: "+pageClassName);
            }

            @Override
            public void onPageClose(final String pageClassName) {
                Log.i(TrackPointAspect.TAG, "onPageClose: "+pageClassName);
            }
        });
    }
}
