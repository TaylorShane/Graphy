package com.shane_taylor.truthtables;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class Graph2 extends Activity {

    public EditText x1;
    public EditText y1;
    public EditText x2;
    public EditText y2;
    public int A = 0;
    public int B = 0;
    public int C = 0;
    public int D = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_graph2);
        setGraphScale();
        hideSoftKeyboard();
    }

    public void setGraphScale(){
        GraphView graph = (GraphView) findViewById(R.id.linegraph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(-20, 0),
                new DataPoint(20, 0)
        });
        LineGraphSeries<DataPoint> series2 = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, -20),
                new DataPoint(0, 20)
        });
        graph.addSeries(series);
        series.setTitle("Random Curve 1");
        series.setColor(Color.GREEN);
        series.setDrawDataPoints(false);
        series.setDataPointsRadius(0);
        series.setThickness(0);

        graph.addSeries(series2);
        series2.setTitle("Random Curve 1");
        series2.setColor(Color.GREEN);
        series2.setDrawDataPoints(false);
        series2.setDataPointsRadius(0);
        series2.setThickness(0);
    }

    public void onClickPlot(View v) {

        getCoordinates();
    }

    public void getCoordinates(){
        x1 = (EditText) findViewById(R.id.x1);
        y1 = (EditText) findViewById(R.id.y1);
        x2 = (EditText) findViewById(R.id.x2);
        y2 = (EditText) findViewById(R.id.y2);

        A = Integer.parseInt(x1.getText().toString());
        B = Integer.parseInt(y1.getText().toString());
        C = Integer.parseInt(x2.getText().toString());
        D = Integer.parseInt(y2.getText().toString());

        GraphView graph = (GraphView) findViewById(R.id.linegraph);
        LineGraphSeries<DataPoint> series2 = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(A, B),
                new DataPoint(C, D)
        });

        graph.addSeries(series2);
    }

    /**
     * Hides the soft keyboard
     */
    public void hideSoftKeyboard() {
        if(getCurrentFocus()!=null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    /**
     * Shows the soft keyboard
     */
    public void showSoftKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        view.requestFocus();
        inputMethodManager.showSoftInput(view, 0);
    }
}
