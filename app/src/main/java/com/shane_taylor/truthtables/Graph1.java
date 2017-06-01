package com.shane_taylor.truthtables;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Window;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

import static android.content.ContentValues.TAG;

/*
implement the onSaveInstanceState() method

The onSaveInstanceState() method takes one parameter, a Bundle.

A Bundle allows you to gather together different types of data into a single object:

Add the values of the running and seconds variables to the Bundle, then the onCreate()
method will be able to pick them up when the activity gets recreated!

*/

public class Graph1 extends Activity {

    public EditText xcoordinate;
    public EditText ycoordinate;
    public int X;
    public int Y;
    public int Xrand;
    public int Yrand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_graph1);
        randomPoints();
        createGraphScale();
        hideSoftKeyboard();
    }

    private void randomPoints(){
        Random generator = new Random();
        Xrand = -20 + generator.nextInt(40);
        Yrand = -20 + generator.nextInt(40);

    }

    private void createGraphScale() {
        GraphView graph = (GraphView) findViewById(R.id.graph);

        PointsGraphSeries<DataPoint> graphScale = new PointsGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(-20, -20),
                new DataPoint(20, 20)
        });
        graph.addSeries(graphScale);
        graphScale.setShape(PointsGraphSeries.Shape.POINT);
        graphScale.setColor(Color.BLACK);
        graphScale.setSize(0001);

        try{

            int x = 20;
            for(int y = 20; y> -20; y--){
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


        PointsGraphSeries<DataPoint> RandomSeries = new PointsGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(Xrand, Yrand)
        });
        graph.addSeries(RandomSeries);
        RandomSeries.setShape(PointsGraphSeries.Shape.POINT);
        RandomSeries.setColor(Color.BLUE);
        RandomSeries.setSize(5);
    }

    public void onClickPlot(View v) {
        getCoordinates();
        createUserPoint();
        // implement logic to see if user is correct plotting reflection across Y axis
        if(X== -1*Xrand && Y == Yrand){
            Toast.makeText(this, "Try again", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
        }
    }

    private void getCoordinates() {
        xcoordinate = (EditText) findViewById(R.id.xplot);
        ycoordinate = (EditText) findViewById(R.id.yplot);
        try{
            X = Integer.parseInt(xcoordinate.getText().toString());
            Y = Integer.parseInt(ycoordinate.getText().toString());
        }
        catch (Exception e){
            Toast.makeText(this, "Please enter integer values for X and Y ", Toast.LENGTH_SHORT).show();
        }
    }
    private void createUserPoint(){
        GraphView graph = (GraphView) findViewById(R.id.graph);
        //Green
        PointsGraphSeries<DataPoint> userSeries = new PointsGraphSeries<>(new DataPoint[] {
                new DataPoint(X, Y)
        });
        graph.addSeries(userSeries);
        userSeries.setColor(Color.GREEN);
        userSeries.setSize(3);
    }

    public void onClickLineGraph(View view){
        Intent intent = new Intent(this, Graph2.class);
        startActivity(intent);
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
