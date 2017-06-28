package com.shane_taylor.truthtables;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import static java.lang.Math.sqrt;


import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Random;

/** TODO: implement SAS test logic - which means - create triangle minus last line - get slope for each line -
 * If the slopes of the two lines are m1 m2, the angle θ is obtained from
 * tanθ=( m1−m21 / 1 +m1*m2 }.
 *
 */

public class Graph4 extends Activity {

    protected double randX1A, randY1A, randX2A, randY2A, randX1B, randY1B, randX2B, randY2B, randX1C, randY1C, randX2C, randY2C,
            randAlength, randBlength, randClength, userAlengthDbl, userBlengthDbl, userClengthDbl,
            randSlopeA, randSlopeB, randAngle, userAngle;

    protected TextView results, userResults, lineA, lineB, lineC, similarInstructions;
    protected EditText userAlength, userBlength, userClength;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_graph4);
        setGraphScale();
        hideSoftKeyboard();
        createRandomTriangle();
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
                fineLinesX.setDataPointsRadius(0);
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

    protected void createRandomTriangle(){ /** random triangle generator - in quadrant I*/

        similarInstructions = (TextView) findViewById(R.id.similarInstructions);
        Random random = new Random();
        randX1A = -10 + random.nextInt(20);
        randY1A = -10 + random.nextInt(20);
        randX2A = -10 + random.nextInt(20);
        randY2A = -10 + random.nextInt(20);

        randX1B = randX2A;
        randY1B = randY2A;
        randX2B = -10 + random.nextInt(20);
        randY2B = -10 + random.nextInt(20);

        randX1C = randX1A;
        randY1C = randY1A;
        randX2C = randX2B;
        randY2C = randY2B;

        /**
         * This graph library expects lines to be drawn from left to right.  The first X coordinate MUST be less than the second.
         * The below logic swaps the two values if this is not the case, along with the appropriate Y values.
         * Vertical lines are not affected no matter the relation of the start and end X values.
         */

        GraphView graph = (GraphView) findViewById(R.id.linegraph);
        graph.removeAllSeries();
        setGraphScale();
        if(randX1A > randX2A) {
            double temp = randX1A;
            randX1A = randX2A;
            randX2A = temp;

            temp = randY1A;
            randY1A = randY2A;
            randY2A = temp;
        }
        LineGraphSeries<DataPoint> LineA = new LineGraphSeries<>(new DataPoint[] {

                new DataPoint(randX1A, randY1A),
                new DataPoint(randX2A, randY2A)
        });
        graph.addSeries(LineA);
        LineA.setColor(Color.BLUE);
        LineA.setDrawDataPoints(true);
        LineA.setDataPointsRadius(0);
        LineA.setThickness(3);

        if(randX1B > randX2B) {
            double temp = randX1B;
            randX1B = randX2B;
            randX2B = temp;

            temp = randY1B;
            randY1B = randY2B;
            randY2B = temp;
        }
        LineGraphSeries<DataPoint> LineB = new LineGraphSeries<>(new DataPoint[] {

                new DataPoint(randX1B, randY1B),
                new DataPoint(randX2B, randY2B)
        });
        graph.addSeries(LineB);
        LineB.setColor(Color.RED);
        LineB.setDrawDataPoints(true);
        LineB.setDataPointsRadius(0);
        LineB.setThickness(3);

        if(randX1C > randX2C) {
            double temp = randX1C;
            randX1C = randX2C;
            randX2C = temp;

            temp = randY1C;
            randY1C = randY2C;
            randY2C = temp;
        }

        if(similarInstructions.getText()
                == getResources().getString(R.string.similarTriangleInstructions1)){
            LineGraphSeries<DataPoint> LineC = new LineGraphSeries<>(new DataPoint[] {

                    new DataPoint(randX1C, randY1C),
                    new DataPoint(randX2C, randY2C)
            });
            graph.addSeries(LineC);
            LineC.setColor(Color.rgb(3,126,3));
            LineC.setDrawDataPoints(true);
            LineC.setDataPointsRadius(0);
            LineC.setThickness(3);
        }


        /** for debugging */
        results = (TextView) findViewById(R.id.random_values_results);
        results.setText("randX1A: " + randX1A + " randY1A: " + randY1A  + "\n" +
                "randX2A: " + randX2A + " randY2A: " + randY2A + "\n" +
                "randX1B: " + randX1B + " randY1B: " + randY1B + "\n" +
                "randX2B: " + randX2B + " randY2B: " + randY2B + "\n" +
                "randX1C: " + randX1C + " randY1C; " + randY1C + "\n" +
                "randX2C: " + randX2C + " randY2C: " + randY2C);

        populateTextViews();
    }

    protected void getRandTriangleSideLengths(){
        randAlength = Math.round(sqrt( Math.pow(randX2A - randX1A, 2) + Math.pow(randY2A - randY1A, 2)));
        randBlength = Math.round(sqrt( Math.pow(randX2B - randX1B, 2) + Math.pow(randY2B - randY1B, 2)));
        randClength = Math.round(sqrt( Math.pow(randX2C - randX1C, 2) + Math.pow(randY2C - randY1C, 2)));
    }

    protected void populateTextViews(){
        getRandTriangleSideLengths();
        lineA = (TextView) findViewById(R.id.txtLineA);
        lineB = (TextView) findViewById(R.id.txtLineB);
        lineC = (TextView) findViewById(R.id.txtLineC);
        lineA.setTextColor(Color.BLUE);
        lineB.setTextColor(Color.RED);
        lineC.setTextColor(Color.rgb(3,126,3));

        lineA.setText("Line A has a length of " + (int)randAlength);
        lineB.setText("Line B has a length of " + (int)randBlength);
        lineC.setText("Line C has a length of " + (int)randClength);
    }

    protected void getSlopes(){
        randSlopeA = (randY1A - randY2A) / (randX1A - randX2A);  // Y2-Y1/X2-X1
        randSlopeB = (randY1B - randY2B) / (randX1B - randX2B);  // Y2-Y1/X2-X1
        randAngle = Math.atan((randSlopeA - randSlopeB) / (1 - (randSlopeA * randSlopeB)));
    }

    protected void onClickVerify(View v){
        userAlength = (EditText) findViewById(R.id.userLineA);
        userBlength = (EditText) findViewById(R.id.userLineB);
        userClength = (EditText) findViewById(R.id.userLineC);

        try{
            userAlengthDbl = Double.parseDouble(userAlength.getText().toString());
            userBlengthDbl = Double.parseDouble(userBlength.getText().toString());
            userClengthDbl = Double.parseDouble(userClength.getText().toString());
        }
        catch(Exception e){
            Toast.makeText(this, "Please enter integer values", Toast.LENGTH_SHORT).show();
        }

        similarInstructions = (TextView) findViewById(R.id.similarInstructions);

        try{
            if(     randAlength == userAlengthDbl && randBlength == userBlengthDbl &&
                    randClength == userClengthDbl
                    ) {
                Toast.makeText(this, "Please enter lengths different than those of the blue triangle.", Toast.LENGTH_SHORT).show();
            }
            else if(randAlength / userAlengthDbl == randBlength / userBlengthDbl &&
                    randAlength / userAlengthDbl == randClength / userClengthDbl &&
                    randBlength / userBlengthDbl == randClength / userClengthDbl &&
                    (similarInstructions.getText()
                    == getResources().getString(R.string.similarTriangleInstructions1))
                    ){
                Toast toast = new Toast(this);
                ImageView view = new ImageView(this);
                view.setImageResource(R.drawable.correct_large);
                toast.setView(view);
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.show();
                similarInstructions.setText(getResources().getString(R.string.similarTriangleInstructions2));
                clearForm((ViewGroup) findViewById(R.id.enterCoordinatesLayout));

                /**
                 * create a new triangle with side C missing
                 * get the slopes of the two
                 * set the text color of lineC to black and change the text ot show angle
                 */
                createRandomTriangle();
                getSlopes();
                lineC.setTextColor(Color.BLACK);
                lineC.setText( "Their angle is: " +Double.toString(randAngle) );  //"The angle between line A and B is: "
            }
            else if(similarInstructions.getText()
                    == getResources().getString(R.string.similarTriangleInstructions2)
                    ){
                Toast.makeText(this, "else if instructions2 was reached", Toast.LENGTH_SHORT).show();
                //TODO: change labels to ask for degree - change decimal to degree
            }
            else{
                // Try again toast image
                Toast toast = new Toast(this);
                ImageView view = new ImageView(this);
                view.setImageResource(R.drawable.try_again_large);
                toast.setView(view);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.show();
            }
        }
        catch(Exception e){
            Toast.makeText(this, "that didn't work", Toast.LENGTH_SHORT).show();
        }
    }

    protected void clearForm(ViewGroup group){
        for (int i = 0, count = group.getChildCount(); i < count; ++i) {
            View view = group.getChildAt(i);
            if (view instanceof EditText) {
                ((EditText)view).setText("");
            }
            if(view instanceof ViewGroup && (((ViewGroup)view).getChildCount() > 0))
                clearForm((ViewGroup)view);
        }
    }

    protected void onClickReset(View view) {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    protected void hideSoftKeyboard() { // this isn't working on all devices
        if(getCurrentFocus()!=null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

}
