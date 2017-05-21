package com.shane_taylor.truthtables;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public String[] sentence1 = {"The wall is red"};

    public String[] sentence2 = {"The lamp is on"};

    public String[] sentence3 = {"If the lamp is on then the wall is red"};

    public void graphActivity(View view){
        Intent intent = new Intent(this, Graph1.class);

        startActivity(intent);
    }
}
