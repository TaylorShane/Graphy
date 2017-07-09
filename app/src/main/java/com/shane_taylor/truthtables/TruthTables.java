package com.shane_taylor.truthtables;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.Window;

public class TruthTables extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_truth_tables);
    }

    public void onClickGraph1(View view) {
        Intent intent = new Intent(this, Graph1.class);
        startActivity(intent);
    }

    protected void onClickMenu(View view){
        Intent intent = new Intent(this, MenuPageActivity.class);
        startActivity(intent);
    }

}
