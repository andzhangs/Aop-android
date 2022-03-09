package com.aop.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author zhangshuai
 */
public class MainActivity extends AppCompatActivity {

    public static final  String isLogin="isLogin";
    private boolean loginStatus=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @IsCheckLogin()
    public void jump(){
        Log.i(CheckLoginAspectJ.TAG, "-------success------- ");
    }

    @IsCheckLogin()
    public void loginException(){
        throw new RuntimeException("登录异常");
    }

    @SuppressLint("NonConstantResourceId")
    public void clickView(View view) {
        switch (view.getId()) {
            case R.id.btn_Init:{
                loginStatus =!loginStatus;
                SharedPreferencesHelper.getInstance().setBooleanValue(isLogin,loginStatus);
                String info="登录状态: "+SharedPreferencesHelper.getInstance().getBooleanValueByKey(isLogin);
                Log.i(CheckLoginAspectJ.TAG,info);
                Toast.makeText(this, info, Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.btn_Login:{
                jump();
                break;
            }
            case R.id.btn_Logout:{
                loginStatus = false;
                SharedPreferencesHelper.getInstance().setBooleanValue(isLogin,loginStatus);
                Toast.makeText(this, "退出登录！", Toast.LENGTH_SHORT).show();
                jump();
                break;
            }
            case R.id.btn_Exception:{
                loginException();
                break;
            }
            default:break;
        }
    }
}
