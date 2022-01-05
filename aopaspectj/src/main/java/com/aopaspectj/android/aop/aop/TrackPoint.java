package com.aopaspectj.android.aop.aop;

/**
 * @author zhangshuai
 */
public class TrackPoint {

    private static TrackPointCallBack sTrackPointCallBack;

    public TrackPoint() {
    }

    public static void init(TrackPointCallBack callback) {
        sTrackPointCallBack=callback;
    }

    static void onClick(String pageClassName,String viewIdName){
        if (sTrackPointCallBack == null) {
            return;
        }
        sTrackPointCallBack.onClick(pageClassName,viewIdName);
    }

    static void onPageOpen(String pageClassName){
        if (sTrackPointCallBack == null) {
            return;
        }
        sTrackPointCallBack.onPageOpen(pageClassName);
    }


    static void onPageClose(String pageClassName){
        if (sTrackPointCallBack == null) {
            return;
        }
        sTrackPointCallBack.onPageClose(pageClassName);
    }


}


