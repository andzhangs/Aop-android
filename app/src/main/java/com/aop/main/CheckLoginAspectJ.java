package com.aop.main;

import android.content.Context;
import android.util.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * 注：被Aspect注解的类名必须以"AspectJ"结尾
 */
@Aspect
class CheckLoginAspectJ {

    private static final String TAG = "CheckLoginAspectJ";

    /**
     * 切面点
     */
    @Pointcut("execution(@com.aop.main.IsCheckLogin  * *(..))")
    public void executionCheckLogin(){
        Log.i(TAG, "进入切面点: ");
    }



    @Around("executionCheckLogin()")
    public Object checkLogin(ProceedingJoinPoint joinPoint) throws Throwable{
        Log.i(TAG, "checkLogin: ");

        MethodSignature signature= (MethodSignature) joinPoint.getSignature();
        IsCheckLogin checkLogin=signature.getMethod().getAnnotation(IsCheckLogin.class);
        if (checkLogin != null) {
            Context context= (Context) joinPoint.getThis();
            if (context != null) {
                if (SharedPreferencesHelper.getInstance().getBooleanValueByKey(MainActivity.isLogin)) {
                    Log.i(TAG, "登录成功!: ");
                    return joinPoint.proceed();   //运行此函数， 则执行被注解方法
                }
                Log.w(TAG, "请先登录!: ");
            }else {
                Log.w(TAG, "上下文空!: ");
            }
        }else {
            Log.w(TAG, "checkLogin = null!: ");
        }
        return null;
    }





}
