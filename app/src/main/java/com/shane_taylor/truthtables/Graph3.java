package com.shane_taylor.truthtables;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.View;
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

public class Graph3 extends Activity {

    public EditText Userx1A, Usery1A, Userx2A, Usery2A, Userx1B, Usery1B, Userx2B, Usery2B, Userx1C, Usery1C, Userx2C, Usery2C;
    public int Ux1A, Uy1A, Ux2A, Uy2A, Ux1B, Uy1B, Ux2B, Uy2B, Ux1C, Uy1C, Ux2C, Uy2C;
    public int randX1A, randY1A, randX2A, randY2A, randX1B, randY1B, randX2B, randY2B, randX1C, randY1C, randX2C, randY2C;
    double userTriangle, randTriangle;
    public TextView triangleInstructions, results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_graph3);
        createRandomTriangle();
        setGraphScale();
        hideSoftKeyboard();
    }
    private void createRandomTriangle(){ /** random triangle generator - in quadrant I*/

        Random random = new Random();
        randX1A = random.nextInt(11);
        randY1A = random.nextInt(11);
        randX2A = random.nextInt(11);
        randY2A = random.nextInt(11);

        randX1B = randX2A;
        randY1B = randY2A;
        randX2B = random.nextInt(11);
        randY2B = random.nextInt(11);

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
        if(randX1A > randX2A) {
            int temp = randX1A;
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
        LineA.setThickness(5);

        if(randX1B > randX2B) {
            int temp = randX1B;
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
        LineB.setColor(Color.BLUE);
        LineB.setDrawDataPoints(true);
        LineB.setDataPointsRadius(0);
        LineB.setThickness(5);

        if(randX1C > randX2C) {
            int temp = randX1C;
            randX1C = randX2C;
            randX2C = temp;

            temp = randY1C;
            randY1C = randY2C;
            randY2C = temp;
        }
        LineGraphSeries<DataPoint> LineC = new LineGraphSeries<>(new DataPoint[] {

                new DataPoint(randX1C, randY1C),
                new DataPoint(randX2C, randY2C)
        });
        graph.addSeries(LineC);
        LineC.setColor(Color.BLUE);
        LineC.setDrawDataPoints(true);
        LineC.setDataPointsRadius(0);
        LineC.setThickness(5);

        /** for debugging */
         results = (TextView) findViewById(R.id.values_results);
         results.setText("randX1A: " + randX1A + " randY1A: " + randY1A + " randX2A: " + randX2A + " randY2A: " + randY2A + "\n"
         + "randX1B: " + randX1B + " randY1B: " + randY1B + " randX2B: " + randX2B + " randY2B: " + randY2B + "\n"
         + "randX1C: " + randX1C + " randY1C; " + randY1C + " randX2C: " + randX2C + " randY2C: " + randY2C);

    }

    public void setGraphScale(){
        // set instructions to default
        //triangleInstructions.setText(getResources().getString(R.string.triangleInstruction1));

        GraphView graph = (GraphView) findViewById(R.id.linegraph);
        //createCustomLine();

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

    public void onClickPlot(View v) { /** button click method **/
        getUserCoordinates();
        createUserLine();
    }

    public void isReflexive(){
        randTriangle = (double) (randY2A - randY1A) / (double)(randX2A - randX1A);  // Y2-Y1/X2-X1
        userTriangle = (double)(Uy2A - Uy1A) / (double)(Ux2A - Ux1A);
        triangleInstructions = (TextView) findViewById(R.id.triangleInstructions);

        if(randTriangle == userTriangle) {
            //Toast.makeText(this, "They are parallel", Toast.LENGTH_LONG).show();
            triangleInstructions.setText(getResources().getString(R.string.triangleInstruction1));
            Toast toast = new Toast(this);
            ImageView view = new ImageView(this);
            view.setImageResource(R.drawable.correct_large);
            toast.setView(view);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.show();
        }
        else if(randTriangle * userTriangle == -1 ){
            Toast.makeText(this, "They are perpendicular", Toast.LENGTH_LONG).show();
            triangleInstructions.setText(getResources().getString(R.string.triangleInstruction1));
        }
        else
            Toast.makeText(this, "Try again!" , Toast.LENGTH_LONG).show();

    }

    public void createUserLine(){  // Using user coordinates to create user line
        GraphView graph = (GraphView) findViewById(R.id.linegraph);

        /**
         * This graph library expects lines to be drawn from left to right.  The first X coordinate MUST be less than the second.
         * The below logic swaps the two values if this is not the case, along with the appropriate Y values.
         * Vertical lines are not affected no matter the relation of the start and end X values.
         */
        if(Ux1A > Ux2A) {
            int temp = Ux1A;
            Ux1A = Ux2A;
            Ux2A = temp;

            temp = Uy1A;
            Uy1A = Uy2A;
            Uy2A = temp;
        }
        LineGraphSeries<DataPoint> userLineA = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(Ux1A, Uy1A),
                new DataPoint(Ux2A, Uy2A)
        });

        graph.addSeries(userLineA);
        userLineA.setColor(Color.parseColor("#ff8a05"));

        if(Ux1B > Ux2B) {
            int temp = Ux1B;
            Ux1B = Ux2B;
            Ux2B = temp;

            temp = Uy1B;
            Uy1B = Uy2B;
            Uy2B = temp;
        }
        LineGraphSeries<DataPoint> userLineB = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(Ux1B, Uy1B),
                new DataPoint(Ux2B, Uy2B)
        });

        graph.addSeries(userLineB);
        userLineB.setColor(Color.parseColor("#ff8a05"));

        if(Ux1C > Ux2C) {
            int temp = Ux1C;
            Ux1C = Ux2C;
            Ux2C = temp;

            temp = Uy1C;
            Uy1C = Uy2C;
            Uy2C = temp;
        }
        LineGraphSeries<DataPoint> userLineC = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(Ux1C, Uy1C),
                new DataPoint(Ux2C, Uy2C)
        });

        graph.addSeries(userLineC);
        userLineC.setColor(Color.parseColor("#ff8a05"));

        isReflexive();
    }

    public void getUserCoordinates(){  // Getting user coordinates
        Userx1A = (EditText) findViewById(R.id.x1A);
        Usery1A = (EditText) findViewById(R.id.y1A);
        Userx2A = (EditText) findViewById(R.id.x2A);
        Usery2A = (EditText) findViewById(R.id.y2A);

        Userx1B = (EditText) findViewById(R.id.x1B);
        Usery1B = (EditText) findViewById(R.id.y1B);
        Userx2B = (EditText) findViewById(R.id.x2B);
        Usery2B = (EditText) findViewById(R.id.y2B);

        Userx1C = (EditText) findViewById(R.id.x1C);
        Usery1C = (EditText) findViewById(R.id.y1C);
        Userx2C = (EditText) findViewById(R.id.x2C);
        Usery2C = (EditText) findViewById(R.id.y2C);

        try{
            Ux1A = Integer.parseInt(Userx1A.getText().toString());
            Uy1A = Integer.parseInt(Usery1A.getText().toString());
            Ux2A = Integer.parseInt(Userx2A.getText().toString());
            Uy2A = Integer.parseInt(Usery2A.getText().toString());

            Ux1B = Integer.parseInt(Userx1B.getText().toString());
            Uy1B = Integer.parseInt(Usery1B.getText().toString());
            Ux2B = Integer.parseInt(Userx2B.getText().toString());
            Uy2B = Integer.parseInt(Usery2B.getText().toString());

            Ux1C = Integer.parseInt(Userx1C.getText().toString());
            Uy1C = Integer.parseInt(Usery1C.getText().toString());
            Ux2C = Integer.parseInt(Userx2C.getText().toString());
            Uy2C = Integer.parseInt(Usery2C.getText().toString());
        }
        catch(Exception e){
            Toast.makeText(this, "Please enter integer values", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Hides the soft keyboard
     */
    public void hideSoftKeyboard() { // this isn't working on all devices
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
