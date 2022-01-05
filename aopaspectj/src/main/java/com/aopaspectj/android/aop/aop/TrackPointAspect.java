package com.aopaspectj.android.aop.aop;

import android.util.Log;
import android.view.View;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author zhangshuai
 */
@Aspect
public class TrackPointAspect {

    public static final String TAG = "TrackPointAspects";
    private String className;

    @Pointcut("execution(* onClick(..))")
    public void methodPointcut() {
        Log.i(TAG, "methodPointcut: ");
    }

    @Pointcut("execution(* androidx.appcompat.app.AppCompatActivity+.onCreate(..))")
    public void activityOnCreatePointcut() {
        Log.i(TAG, "activityOnCreatePointcut: ");
    }
    @Pointcut("execution(* androidx.appcompat.app.AppCompatActivity+.onDestroy(..))")
    public void activityOnDestroyPointcut() {
        Log.i(TAG, "activityOnDestroyPointcut: ");
    }


    @Pointcut("execution(* androidx.fragment.app.Fragment+.onCreate(..))")
    public void fragmentOnCreatePointcut() {
        Log.i(TAG, "fragmentOnCreatePointcut: ");
    }
    @Pointcut("execution(* androidx.fragment.app.Fragment+.onDestroy(..))")
    public void fragmentOnDestroyPointcut() {
        Log.i(TAG, "fragmentOnDestroyPointcut: ");
    }


//    @Pointcut("execution(* android.support.v4.app.Fragment+.onCreate(..))")
//    public void fragmentV4OnCreatePointcut() {
//        Log.i(TAG, "fragmentV4OnCreatePointcut: ");
//    }
//    @Pointcut("execution(* android.support.v4.app.Fragment+.onDestroy(..))")
//    public void fragmentV4OnDestroyPointcut() {
//        Log.i(TAG, "fragmentV4OnDestroyPointcut: ");
//    }


    @Around("onClickPointcut()")
    public void aroundJoinClickPoint(final ProceedingJoinPoint joinPoint) throws Throwable {
        getClassName(joinPoint);
        //获取点击事件view对象以及名称，可以对不同按钮的点击事件进行统计
        Object[] objs=joinPoint.getArgs();
        if (objs.length>=1 && objs[0] instanceof View) {
            View view= (View) objs[0];
            int id=view.getId();
            String entryName=view.getResources().getResourceEntryName(id);
            TrackPoint.onClick(className,entryName);
        }
        //执行原来的代码
        joinPoint.proceed();
    }

    @Around("activityOnCreatePointcut() || fragmentOnCreatePointcut() || fragmentV4OnCreatePointcut()")
    public void aroundJoinPageOpenPoint(final ProceedingJoinPoint joinPoint) throws Throwable {
        getClassName(joinPoint);
        TrackPoint.onPageOpen(className);
    }

    @Around("activityOnDestroyPointcut() || fragmentOnDestroyPointcut() || fragmentV4OnDestroyPointcut()")
    public void aroundJoinPageClosePoint(final ProceedingJoinPoint joinPoint) throws Throwable {
        getClassName(joinPoint);
        TrackPoint.onPageClose(className);
    }

    private void getClassName(final ProceedingJoinPoint joinPoint){
        Object target=joinPoint.getTarget();
        if (target != null) {
            className=target.getClass().getName();
        }
    }


}
