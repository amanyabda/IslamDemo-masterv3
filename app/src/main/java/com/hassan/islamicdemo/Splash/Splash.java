package com.hassan.islamicdemo.Splash;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;

import com.hassan.islamicdemo.Activiteis.FirstScreen;
import com.hassan.islamicdemo.Base.BaseActivity;
import com.hassan.islamicdemo.Home.MainActivity;
import com.hassan.islamicdemo.R;
import com.hassan.islamicdemo.Utils.AppConstants;

public class Splash extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_splash);

        new Handler().postDelayed(() -> {
            startActivity(new Intent(Splash.this, FirstScreen.class));
            finish();
        },    1000);
    }
}