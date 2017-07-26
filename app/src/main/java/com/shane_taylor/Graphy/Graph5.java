package com.shane_taylor.Graphy;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Random;

import static java.lang.Math.sqrt;

/*
 Graph5 method call order

  onCreate ->
   hideSoftKeyboard
   createRandomTriangle ->
   	graph.removeAllSeries
       setGraphScale
       getRandTriangleSideLengths
       populateTextViews

  onClickVerify ->
   clearForm
 */

/**
 * Pythagorean theorem triangle
 */
public class Graph5 extends Activity {

    public int randXA;
    public int randYA;
    public int randXB;
    public int randYB;
    public int randXC;
    public int randYC;
    public int randAlength;
    public int randBlength;
    public double randClength;
    public double userClengthDbl;
    public TextView lineA;
    public TextView lineB;
    public TextView instructions;
    public EditText userClength;

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph5);
        createRandomTriangle();
        setGraphScale();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    public void createRandomTriangle() { /* random triangle generator */
        Random generator = new Random();
        randXA = 0;
        randYA = 0;
        while (randXA == 0 || randYA == 0) {
            randXA = -10 + generator.nextInt(20);
            randYA = -10 + generator.nextInt(20);

            randXB = randXA;
            randYB = randYA * -3;
            if (randXB < 1)
                randXC = randXB * -3;
            else
                randXC = randXB * 3;
            randYC = randYA * -3;
        }


        //randSlopeA = (randY1A - randY2A) / (randX1A - randX2A);  // Y2-Y1/X2-X1
        //randSlopeB = (randY1B - randY2B) / (randX1B - randX2B);  // Y2-Y1/X2-X1

        /*
          This graph library expects lines to be drawn from left to right.  The first X coordinate MUST be less than the second.
          The below logic swaps the two values if this is not the case, along with the appropriate Y values.
          Vertical lines are not affected no matter the relation of the start and end X values.
         */

        GraphView graph = (GraphView) findViewById(R.id.linegraph);
        graph.removeAllSeries();

        if (randXA > randXB) {
            int temp = randXA;
            randXA = randXB;
            randXB = temp;

            temp = randYA;
            randYA = randYB;
            randYB = temp;
        }
        LineGraphSeries<DataPoint> LineA = new LineGraphSeries<>(new DataPoint[]{

                new DataPoint(randXA, randYA),
                new DataPoint(randXB, randYB),
                new DataPoint(randXA, randYA)
        });
        graph.addSeries(LineA);
        LineA.setColor(Color.BLUE);
        LineA.setDrawDataPoints(true);
        LineA.setDataPointsRadius(0);
        LineA.setThickness(3);

        if (randXB > randXC) {
            int temp = randXB;
            randXB = randXC;
            randXC = temp;

            temp = randYB;
            randYB = randYC;
            randYC = temp;
        }
        LineGraphSeries<DataPoint> LineB = new LineGraphSeries<>(new DataPoint[]{

                new DataPoint(randXB, randYB),
                new DataPoint(randXC, randYC)
        });
        graph.addSeries(LineB);
        LineB.setColor(Color.RED);
        LineB.setDrawDataPoints(true);
        LineB.setDataPointsRadius(0);
        LineB.setThickness(3);

        if (randXC > randXA) {
            int temp = randXC;
            randXC = randXA;
            randXA = temp;

            temp = randYC;
            randYC = randYA;
            randYA = temp;
        }

        LineGraphSeries<DataPoint> LineC = new LineGraphSeries<>(new DataPoint[]{

                new DataPoint(randXC, randYC),
                new DataPoint(randXA, randYA)
        });
        graph.addSeries(LineC);
        LineC.setColor(Color.rgb(3, 126, 3));
        LineC.setDrawDataPoints(true);
        LineC.setDataPointsRadius(0);
        LineC.setThickness(3);

        getRandTriangleSideLengths();
        populateTextViews();

        /* for debugging
         results = (TextView) findViewById(R.id.random_values_results);
         results.setText("PLEASE IGNORE THIS TEST DATA" + "\n" +
         "randXA: " + randXA + " randYA: " + randYA  + "\n" +
         "randXB: " + randXB + " randYB: " + randYB + "\n" +
         "randXC: " + randXC + " randYC: " + randYC + "\n" +
         "randClength" + randClength);
        */

    }

    public void getRandTriangleSideLengths() {

        randAlength = (int) Math.round(sqrt(Math.pow(randXB - randXA, 2) + Math.pow(randYB - randYA, 2)));
        randBlength = (int) Math.round(sqrt(Math.pow(randXC - randXB, 2) + Math.pow(randYC - randYB, 2)));
        randClength = round((sqrt(Math.pow(randXA - randXC, 2) + Math.pow(randYA - randYC, 2))), 2);
    }

    public void setGraphScale() {

        GraphView graph = (GraphView) findViewById(R.id.linegraph);

        LineGraphSeries<DataPoint> Xaxis = new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(-20, 0),
                new DataPoint(20, 0)
        });

        LineGraphSeries<DataPoint> Yaxis = new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(0, -20),
                new DataPoint(0, 20)
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
    }

    public void clearForm(ViewGroup group) {
        for (int i = 0, count = group.getChildCount(); i < count; ++i) {
            View view = group.getChildAt(i);
            if (view instanceof EditText) {
                ((EditText) view).setText("");
            }
            if (view instanceof ViewGroup && (((ViewGroup) view).getChildCount() > 0))
                clearForm((ViewGroup) view);
        }
    }

    public void onClickVerify(View v) {
        userClength = (EditText) findViewById(R.id.userLineClength);
        instructions = (TextView) findViewById(R.id.pythagoreaninstructions);

        try {
            userClengthDbl = Double.parseDouble(userClength.getText().toString());
            verify();
        } catch (Exception e) {
            Toast.makeText(this, "Please enter integer values", Toast.LENGTH_SHORT).show();
        }
    }

    public void verify() {
        if (randClength == userClengthDbl) {
            Toast toast = new Toast(this);
            ImageView view = new ImageView(this);
            view.setImageResource(R.drawable.correct_large);
            toast.setView(view);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.show();
            clearForm((ViewGroup) findViewById(R.id.enterCoordinatesLayout));
            instructions.setText(getResources().getString(R.string.newTriangle));
        } else {
            // Try again toast image
            Toast toast = new Toast(this);
            ImageView view = new ImageView(this);
            view.setImageResource(R.drawable.try_again_large);
            toast.setView(view);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.show();
        }
    }

    public void populateTextViews() {
        getRandTriangleSideLengths();
        lineA = (TextView) findViewById(R.id.txtLineA);
        lineB = (TextView) findViewById(R.id.txtLineB);

        lineA.setTextColor(Color.BLUE);
        lineB.setTextColor(Color.RED);

        lineA.setText(getString(R.string.displayAlength) + " " + randAlength);
        lineB.setText(getString(R.string.displayBlength) + " " + randBlength);
    }

    public void onClickReset(View view) {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    public void onClickMenu(View view) {
        Intent intent = new Intent(this, MenuPageActivity.class);
        startActivity(intent);
    }
}
