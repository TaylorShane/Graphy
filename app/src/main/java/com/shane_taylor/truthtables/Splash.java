package com.shane_taylor.truthtables;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

public class Splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

        Thread splash = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(4000);
                    Intent mainIntent = new Intent(getApplicationContext(),MenuPageActivity.class);
                    startActivity(mainIntent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        splash.start();
    }
}
