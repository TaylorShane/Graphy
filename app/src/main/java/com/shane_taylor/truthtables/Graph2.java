package com.shane_taylor.truthtables;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Random;

public class Graph2 extends Activity {

    public EditText x1, y1, x2, y2;
    public int A, B, C, D;
    public int X1rand, Y1rand, X2rand, Y2rand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_graph2);
        randomLine();
        setGraphScale();
        hideSoftKeyboard();
    }


    private void randomLine(){
        Random generator = new Random();
        X1rand = -10 + generator.nextInt(20);
        Y1rand = -10 + generator.nextInt(20);
        X2rand = -10 + generator.nextInt(20);
        Y2rand = -10 + generator.nextInt(20);
    }

    public void setGraphScale(){
        GraphView graph = (GraphView) findViewById(R.id.linegraph);
        createCustomLine();
        LineGraphSeries<DataPoint> randomLine = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(X1rand, Y1rand),
                new DataPoint(X2rand, Y2rand)
        });
        graph.addSeries(randomLine);
        randomLine.setColor(Color.BLUE);
        randomLine.setDrawDataPoints(true);
        randomLine.setDataPointsRadius(5);
        randomLine.setThickness(5);

        LineGraphSeries<DataPoint> Xaxis = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(-10, 0),
                new DataPoint(10, 0)
        });

        LineGraphSeries<DataPoint> Yaxis = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, -10),
                new DataPoint(0, 10)
        });
        graph.addSeries(Xaxis);
        Xaxis.setTitle("X Axis");
        Xaxis.setColor(Color.BLACK);
        Xaxis.setDrawDataPoints(false);
        Xaxis.setDataPointsRadius(0);
        Xaxis.setThickness(0);

        graph.addSeries(Yaxis);
        Yaxis.setTitle("Y Axis");
        Yaxis.setColor(Color.BLACK);
        Yaxis.setDrawDataPoints(false);
        Yaxis.setDataPointsRadius(0);
        Yaxis.setThickness(0);

        try{
            int x = 10;
            for(int y = 10; y> -10; y--){
                LineGraphSeries<DataPoint> fineLinesX = new LineGraphSeries<>(new DataPoint[] {
                        new DataPoint(x*-1, y),
                        new DataPoint(x, y)
                });

                LineGraphSeries<DataPoint> fineLinesY = new LineGraphSeries<>(new DataPoint[] {
                        new DataPoint(y, -1*x),
                        new DataPoint(y, x)
                });

                graph.addSeries(fineLinesX);
                fineLinesX.setColor(Color.GRAY);
                fineLinesX.setDrawDataPoints(true);
                fineLinesX.setDataPointsRadius(1);
                fineLinesX.setThickness(1);

                graph.addSeries(fineLinesY);
                fineLinesY.setColor(Color.GRAY);
                fineLinesY.setDrawDataPoints(true);
                fineLinesY.setDataPointsRadius(0);
                fineLinesY.setThickness(1);
            }
        }
        catch(Exception e){
            Toast.makeText(this, "Creating fineLines didn't work", Toast.LENGTH_SHORT).show();
        }
    }

    public void createCustomLine(){
        /*
        Paint paint = new Paint();
        GraphView graph = (GraphView) findViewById(R.id.linegraph);
        graph.drawLine(0, 0, 20, 20, paint);
        graph.drawLine(20, 0, 0, 20, paint);
        */
    }
    public void onClickPlot(View v) {

        getCoordinates();
        createUserLine();
        Toast.makeText(this,
                "X1rand: " + X1rand +
                        "\n Y1rand: " + Y1rand +
                        "\n X2rand: " + X2rand +
                        "\n Y2rand: " + Y2rand
                , Toast.LENGTH_LONG).show();
    }

    public void createUserLine(){
        GraphView graph = (GraphView) findViewById(R.id.linegraph);
        LineGraphSeries<DataPoint> userLine = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(A, B),
                new DataPoint(C, D)
        });

        graph.addSeries(userLine);
        userLine.setColor(Color.parseColor("#ff8a05"));
    }
    public void getCoordinates(){
        x1 = (EditText) findViewById(R.id.x1);
        y1 = (EditText) findViewById(R.id.y1);
        x2 = (EditText) findViewById(R.id.x2);
        y2 = (EditText) findViewById(R.id.y2);

        try{
            A = Integer.parseInt(x1.getText().toString());
            B = Integer.parseInt(y1.getText().toString());
            C = Integer.parseInt(x2.getText().toString());
            D = Integer.parseInt(y2.getText().toString());
        }
        catch(Exception e){
            Toast.makeText(this, "Please enter integer values", Toast.LENGTH_SHORT).show();
        }
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
