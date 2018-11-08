package com.aop.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import javax.security.auth.login.LoginException;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences mPreferences=null;
    public static final  String isLogin="isLogin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPreferences=getSharedPreferences(this.getPackageName(), Context.MODE_PRIVATE);
    }

    @IsCheckLogin
    public void jump(){
        Log.i("CheckLoginAspectJ", "-------success------- ");
    }

    private boolean loginStatus=false;


    public void ClickView(View view) {
        switch (view.getId()) {
            case R.id.btn_Init:{
                SharedPreferencesHelper.getInstance().setBooleanValue(isLogin,loginStatus);
                loginStatus =!loginStatus;
                Log.i("CheckLoginAspectJ", "登录值：: "+SharedPreferencesHelper.getInstance().getBooleanValueByKey(isLogin));
                break;
            }
            case R.id.btn_Login:{
                jump();
                break;
            }
        }
    }
}
