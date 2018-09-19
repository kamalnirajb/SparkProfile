package com.niraj.sparkprofile;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler(Looper.getMainLooper()).postDelayed(()->{
            Intent profileIntent = new Intent(SplashActivity.this, HomeActivity.class);
            startActivity(profileIntent);
        },1000);
    }
}
