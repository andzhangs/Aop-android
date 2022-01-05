package com.aopaspectj.android.aop.aop;

/**
 * @author zhangshuai
 */
public interface TrackPointCallBack {

    /**
     *
     * @param pageClassName
     * @param viewIdName
     */
    void onClick(String pageClassName,String viewIdName);

    /**
     *
     * @param pageClassName
     */
    void onPageOpen(String pageClassName);

    /**
     *
     * @param pageClassName
     */
    void onPageClose(String pageClassName);

}
