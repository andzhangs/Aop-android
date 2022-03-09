package com.aop.main;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * 注：被Aspect注解的类名必须以"AspectJ"结尾
 */
@Aspect
class CheckLoginAspectJ {

    public static final String TAG = "CheckLoginAspectJ";

    /**
     * 切面点
     */
    @Pointcut(value = "execution(@com.aop.main.IsCheckLogin  * *(..))",argNames="executionCheckLogin")
    public void executionCheckLogin(){
        Log.i(TAG, "进入切面点: "+Thread.currentThread().getName());
    }

    /**
     * 前置通知, 在目标执行之前执行通知
     */
    @Before(value = "executionCheckLogin()",argNames="CheckBefore")
    public void CheckBefore(){
        Log.i(TAG, "CheckBefore: "+Thread.currentThread().getName());
    }

    /**
     * 环绕通知, 在目标执行中执行通知, 控制目标执行时机
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(value = "executionCheckLogin()",argNames="checkLogin")
    public Object checkLogin(ProceedingJoinPoint joinPoint) throws Throwable{
        Log.i(TAG, "checkLogin: "+Thread.currentThread().getName());

        Context context= null;

        final Object object=joinPoint.getThis();
        if (joinPoint.getThis() instanceof Context) {
            context= (Context) object;
        }

        if (context == null) {
            return new NullPointerException("the context is null");
        }

        MethodSignature signature= (MethodSignature) joinPoint.getSignature();
        IsCheckLogin checkLogin=signature.getMethod().getAnnotation(IsCheckLogin.class);
        if (checkLogin != null) {
            if (SharedPreferencesHelper.getInstance().getBooleanValueByKey(MainActivity.isLogin)) {
                Log.i(TAG, "登录成功!");
                Toast.makeText(context, "登录成功!", Toast.LENGTH_SHORT).show();
                return joinPoint.proceed();   //运行此函数， 则执行被注解方法
            }
            Log.w(TAG, "请先登录!");
            Toast.makeText(context, "请先登录!", Toast.LENGTH_SHORT).show();
        }else {
            Log.w(TAG, "checkLogin = null! ");
        }
        return null;
    }

    /**
     * joinPoint.proceed（）调用后 才可调用
     * 后置通知, 目标执行后执行通知
     */
    @After(value = "executionCheckLogin()")
    public void CheckAfter(){
        Log.i(TAG, "CheckAfter: "+Thread.currentThread().getName());
    }

    /**
     * 后置返回通知, 目标返回时执行通知
     */
    @AfterReturning(value ="executionCheckLogin()")
    public void CheckAfterReturning(){
        Log.i(TAG, "CheckAfterReturning: "+Thread.currentThread().getName());
    }

    /**
     * 异常通知, 目标抛出异常时执行通知
     */
    @AfterThrowing(value = "executionCheckLogin()",throwing = "ex",argNames = "CheckAfterThrowing")
    public void CheckAfterThrowing(Throwable ex){
        Log.e(TAG, "CheckAfterThrowing: "+Thread.currentThread().getName()+", "+ex);
    }


}
