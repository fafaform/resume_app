package com.example.testing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ///////////////////////
        // TODO create background process call thread
        ///////////////////////
        Thread thread = new Thread() {
            @Override
            public void run() {
                try{
                    Thread.sleep(2000);
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
                ////////////////////////////////////
                // to make change in foreground
                ////////////////////////////////////
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Intent gotoMain = new Intent(getBaseContext(),MainActivity.class);
                        startActivity(gotoMain);
                        finish();
                    }
                });
            }
        };
        ////////////////////////////////////
        // starting thread
        ////////////////////////////////////
        thread.start();
    }
}
