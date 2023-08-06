package com.shane_taylor.Graphy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import static com.shane_taylor.Graphy.R.id.privacyLink;

public class MenuPageActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_menu_page);

        // 1) How to replace link by text like "Click Here to visit Google" and
        // the text is linked with the website url ?
        TextView link = (TextView) findViewById(privacyLink);
        String linkText = "<a href='https://www.shanetaylor.dev/#/graphy-privacy-terms'>Privacy policy here.</a>";
        link.setText(Html.fromHtml(linkText));
        link.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public void onClickTruth(View view) {
        Intent intent = new Intent(this, TruthTables.class);
        startActivity(intent);
    }

    public void onClickPoint(View view) {
        Intent intent = new Intent(this, Graph1.class);
        startActivity(intent);
    }

    public void onClickLine(View view) {
        Intent intent = new Intent(this, Graph2.class);
        startActivity(intent);
    }

    public void onClickReflexive(View view) {
        Intent intent = new Intent(this, Graph3.class);
        startActivity(intent);
    }

    public void onClickSSS(View view) {
        Intent intent = new Intent(this, Graph4.class);
        startActivity(intent);
    }

    public void onClickPythag(View view) {
        Intent intent = new Intent(this, Graph5.class);
        startActivity(intent);
    }
}
