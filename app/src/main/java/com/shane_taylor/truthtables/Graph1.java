package com.shane_taylor.truthtables;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

/**
TODO: implement the onSaveInstanceState() method
*/

/**
 * Points Graph
 */
public class Graph1 extends Activity {

    private EditText xcoordinate, ycoordinate;
    private double X, Y, Xrand, Yrand;
    private TextView pointInstructions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_graph1);
        createRandomPoint();
        createGraphScale();
        hideSoftKeyboard();
    }

    protected void createRandomPoint(){
        Random generator = new Random();
        Xrand = -10 + generator.nextInt(20);
        Yrand = -10 + generator.nextInt(20);

        GraphView graph = (GraphView) findViewById(R.id.graph);
        PointsGraphSeries<DataPoint> randomPoint = new PointsGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(Xrand, Yrand)
        });
        graph.addSeries(randomPoint);
        randomPoint.setShape(PointsGraphSeries.Shape.POINT);
        randomPoint.setColor(Color.BLUE);
        randomPoint.setSize(7);
    }

    protected void createGraphScale() {
        GraphView graph = (GraphView) findViewById(R.id.graph);

        PointsGraphSeries<DataPoint> graphScale = new PointsGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(-10, -10),
                new DataPoint(10, 10)
        });
        graph.addSeries(graphScale);
        graphScale.setShape(PointsGraphSeries.Shape.POINT);
        graphScale.setColor(Color.BLACK);
        graphScale.setSize(0001);

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

    protected void onClickPlot(View v) {
        getCoordinates();
        createUserPoint();
        pointInstructions = (TextView) findViewById(R.id.pointInstructions);
        if(X == (Xrand * -1) && Y == Yrand && pointInstructions.getText() == getResources().getString(R.string.pointInstruction1)){
            Toast toast = new Toast(this);
            ImageView view = new ImageView(this);
            view.setImageResource(R.drawable.correct_large);
            toast.setView(view);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.show();
            pointInstructions.setText(R.string.pointInstruction2);
            clearForm((ViewGroup) findViewById(R.id.pointsGridLayout));
        }
        else if( X == Xrand  && Y == (Yrand *-1) && (pointInstructions.getText() == getResources().getString(R.string.pointInstruction2)) )
        {
            Toast toast = new Toast(this);
            ImageView view = new ImageView(this);
            view.setImageResource(R.drawable.correct_large);
            toast.setView(view);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.show();
            pointInstructions.setText(R.string.newPoint);
            clearForm((ViewGroup) findViewById(R.id.pointsGridLayout));
        }
        else{
            Toast toast = new Toast(this);
            ImageView view = new ImageView(this);
            view.setImageResource(R.drawable.try_again_large);
            toast.setView(view);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    protected void getCoordinates() {
        xcoordinate = (EditText) findViewById(R.id.xplot);
        ycoordinate = (EditText) findViewById(R.id.yplot);
        try{
            X = Double.parseDouble(xcoordinate.getText().toString());
            Y = Double.parseDouble(ycoordinate.getText().toString());
        }
        catch (Exception e){
            Toast.makeText(this, "Please enter integer values for X and Y ", Toast.LENGTH_SHORT).show();
        }
    }
    protected void createUserPoint(){
        GraphView graph = (GraphView) findViewById(R.id.graph);
        PointsGraphSeries<DataPoint> userSeries = new PointsGraphSeries<>(new DataPoint[] {
                new DataPoint(X, Y)
        });
        graph.addSeries(userSeries);
        userSeries.setColor(Color.parseColor("#ff8a05"));
        userSeries.setSize(5);
        userSeries.setShape(PointsGraphSeries.Shape.POINT);
        /**
        userSeries.setCustomShape(new PointsGraphSeries.CustomShape() {
            @Override
            public void draw(Canvas canvas, Paint paint, float x, float y, DataPointInterface dataPoint) {
                paint.setStrokeWidth(5);
                canvas.drawLine(x-10, y-10, x+10, y+10, paint);
                canvas.drawLine(x+10, y-10, x-10, y+10, paint);
            }
        });
         */
    }

    protected void onClickLineGraph(View view){
        Intent intent = new Intent(this, Graph2.class);
        startActivity(intent);
    }

    protected void onClickReset(View view) {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    /**
     * Hides the soft keyboard
     */

    protected void hideSoftKeyboard() {
        if(getCurrentFocus()!=null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    /**
     * Shows the soft keyboard
     * public void showSoftKeyboard(View view) {
     * InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
     * view.requestFocus();
     * inputMethodManager.showSoftInput(view, 0);
     * }
     */
    protected void clearForm(ViewGroup group)
    {
        for (int i = 0, count = group.getChildCount(); i < count; ++i) {
            View view = group.getChildAt(i);
            if (view instanceof EditText) {
                ((EditText)view).setText("");
            }

            if(view instanceof ViewGroup && (((ViewGroup)view).getChildCount() > 0))
                clearForm((ViewGroup)view);
        }
    }
}
