package com.shane_taylor.Graphy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.winsontan520.wversionmanager.library.WVersionManager;

public class Splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

        checkVersion();

        Thread splash = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(4000);
                    Intent mainIntent = new Intent(getApplicationContext(), MenuPageActivity.class);
                    startActivity(mainIntent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        splash.start();

    }

    private void checkVersion() {
        WVersionManager versionManager = new WVersionManager(this);
        versionManager.setVersionContentUrl("http://www.shane-taylor.com/Graphy_version.txt"); // your update content url, see the response format below
        versionManager.checkVersion();
    }
}
