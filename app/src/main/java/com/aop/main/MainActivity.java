package com.aop.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences mPreferences=null;
    public static final  String isLogin="isLogin";
    private boolean loginStatus=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPreferences=getSharedPreferences(this.getPackageName(), Context.MODE_PRIVATE);
    }

    @IsCheckLogin()
    public void jump(){
        Log.i("CheckLoginAspectJ", "-------success------- ");
    }



    @SuppressLint("NonConstantResourceId")
    public void clickView(View view) {
        switch (view.getId()) {
            case R.id.btn_Init:{
                loginStatus =!loginStatus;
                SharedPreferencesHelper.getInstance().setBooleanValue(isLogin,loginStatus);
                String info="登录值：: "+SharedPreferencesHelper.getInstance().getBooleanValueByKey(isLogin);
                Log.i("CheckLoginAspectJ",info);
                Toast.makeText(this, info, Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.btn_Login:{
                jump();
                break;
            }
            default:break;
        }
    }
}
