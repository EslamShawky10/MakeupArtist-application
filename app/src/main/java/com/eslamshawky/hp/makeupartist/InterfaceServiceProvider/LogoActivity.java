package com.eslamshawky.hp.makeupartist.InterfaceServiceProvider;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.eslamshawky.hp.makeupartist.R;

public class LogoActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(LogoActivity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        },SPLASH_TIME_OUT);
    }
}
