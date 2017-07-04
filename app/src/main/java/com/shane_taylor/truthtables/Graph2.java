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
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Random;

/**
 TODO: implement the onSaveInstanceState() method
 TODO: fix reset graph to work with chromebook
 */

/**
 * Lines Graph
 */
public class Graph2 extends Activity {

    private EditText x1, y1, x2, y2;
    private int Ux1, Uy1, Ux2, Uy2, X1rand, Y1rand, X2rand, Y2rand;
    double uSlope, RandSlope;
    private TextView lineInstructions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_graph2);
        createRandomLine();
        setGraphScale();
        hideSoftKeyboard();
    }

    protected void createRandomLine(){ /** random line generator */
        Random generator = new Random();
        X1rand = -10 + generator.nextInt(20);
        Y1rand = -10 + generator.nextInt(20);
        X2rand = -10 + generator.nextInt(20);
        Y2rand = -10 + generator.nextInt(20);

        GraphView graph = (GraphView) findViewById(R.id.linegraph);

        /** The graph library doesn't respond to all scenarios of completely vertical or completely horizontal lines */
        if(X1rand == X2rand){
            X2rand += 1;
        }
        if (Y1rand == Y2rand){
            Y2rand += 1;
        }
        /**
         * This graph library expects lines to be drawn from left to right.  The first X coordinate MUST be less than the second.
         * The below logic swaps the two values if this is not the case, along with the appropriate Y values.
         * Vertical lines are not affected no matter the relation of the start and end X values.
         */
        if(X1rand > X2rand) {
            int temp = X1rand;
            X1rand = X2rand;
            X2rand = temp;

            temp = Y1rand;
            Y1rand = Y2rand;
            Y2rand = temp;
        }

        LineGraphSeries<DataPoint> randomLine = new LineGraphSeries<>(new DataPoint[] {

                new DataPoint(X1rand, Y1rand),
                new DataPoint(X2rand, Y2rand)
        });
        graph.addSeries(randomLine);
        randomLine.setColor(Color.BLUE);
        randomLine.setDrawDataPoints(true);
        randomLine.setDataPointsRadius(0);
        randomLine.setThickness(5);
    }

    protected void setGraphScale(){
        GraphView graph = (GraphView) findViewById(R.id.linegraph);

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

    protected void onClickPlot(View v) { /** button click method **/

        getUserCoordinates();
        createUserLine();
    }

    protected void createUserLine(){  // Using user coordinates to create user line
        GraphView graph = (GraphView) findViewById(R.id.linegraph);

        /**
         * This graph library expects lines to be drawn from left to right.  The first X coordinate MUST be less than the second.
         * The below logic swaps the two values if this is not the case, along with the appropriate Y values.
         * Vertical lines are not affected no matter the relation of the start and end X values.
         */
        if(Ux1 > Ux2) {
            int temp = 0;
            temp = Ux1;
            Ux1 = Ux2;
            Ux2 = temp;

            temp = Uy1;
            Uy1 = Uy2;
            Uy2 = temp;
        }
        LineGraphSeries<DataPoint> userLine = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(Ux1, Uy1),
                new DataPoint(Ux2, Uy2)
        });

        graph.addSeries(userLine);
        userLine.setColor(Color.parseColor("#ff8a05"));
    }

    protected void getSlopes(){
        RandSlope = (double) (Y2rand - Y1rand) / (double)(X2rand - X1rand);  // Y2-Y1/X2-X1
        uSlope = (double)(Uy2 - Uy1) / (double)(Ux2 - Ux1);
        lineInstructions = (TextView) findViewById(R.id.lineInstructions);

        if(RandSlope == uSlope && lineInstructions.getText() == getResources().getString(R.string.lineInstruction1)) {

            Toast toast = new Toast(this);
            ImageView view = new ImageView(this);
            view.setImageResource(R.drawable.correct_large);
            toast.setView(view);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.show();
            lineInstructions.setText(getResources().getString(R.string.lineInstruction2));

            clearForm((ViewGroup) findViewById(R.id.enterCoordinatesLayout));
        }
        else if(RandSlope * uSlope == -1 && lineInstructions.getText() == getResources().getString(R.string.lineInstruction2)){

            Toast toast = new Toast(this);
            ImageView view = new ImageView(this);
            view.setImageResource(R.drawable.correct_large);
            toast.setView(view);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.show();
            lineInstructions.setText(getResources().getString(R.string.newLine));

            clearForm((ViewGroup) findViewById(R.id.enterCoordinatesLayout));
        }
        else {

            Toast toast = new Toast(this);
            ImageView view = new ImageView(this);
            view.setImageResource(R.drawable.try_again_large);
            toast.setView(view);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    protected void getUserCoordinates(){  // Getting user coordinates
        x1 = (EditText) findViewById(R.id.x1);
        y1 = (EditText) findViewById(R.id.y1A);
        x2 = (EditText) findViewById(R.id.x1B);
        y2 = (EditText) findViewById(R.id.y1B);

        try{
            Ux1 = Integer.parseInt(x1.getText().toString());
            Uy1 = Integer.parseInt(y1.getText().toString());
            Ux2 = Integer.parseInt(x2.getText().toString());
            Uy2 = Integer.parseInt(y2.getText().toString());
        }
        catch(Exception e){
            Toast.makeText(this, "Please enter integer values", Toast.LENGTH_SHORT).show();
        }
        getSlopes();
    }

    protected void onClickTriangleGraph(View view){
        Intent intent = new Intent(this, Graph3.class);
        startActivity(intent);
    }

    /**
     * Hides the soft keyboard
     */
    protected void hideSoftKeyboard() { // this isn't working on all devices
        if(getCurrentFocus()!=null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    /**
     * Shows the soft keyboard
     */

    protected void onClickReset(View view) {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    /** clears the editTexts **/
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

