package com.example.flyingfish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Thread thread=new Thread()
        {
            @Override
            public void run() {
                try{
                    sleep(4000);





                }
                catch (Exception e)
                {
                   e.printStackTrace();
                }
                finally {
                    Intent mainintent = new Intent(SplashScreenActivity.this,MainActivity.class);
                    startActivity(mainintent);
                }
                }
               };
        thread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}