package com.shane_taylor.truthtables;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.PointsGraphSeries;

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
    public int X = 0;
    public int Y = 0;
    private Button plot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_graph1);
        createGraphScale();
    }

    private void createGraph() {
        // Blue Dots
        GraphView graph = (GraphView) findViewById(R.id.graph);

        PointsGraphSeries<DataPoint> series = new PointsGraphSeries<>(new DataPoint[] {
                new DataPoint(-10, 10)
        });
        graph.addSeries(series);
        series.setShape(PointsGraphSeries.Shape.POINT);


        // Black Rectangles
        PointsGraphSeries<DataPoint> series2 = new PointsGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(10, 10)

        });
        graph.addSeries(series2);
        series2.setShape(PointsGraphSeries.Shape.RECTANGLE);
        series2.setColor(Color.BLACK);

        // Yellow Triangles
        PointsGraphSeries<DataPoint> series3 = new PointsGraphSeries<>(new DataPoint[] {
                new DataPoint(-10, -10)
        });
        graph.addSeries(series3);
        series3.setShape(PointsGraphSeries.Shape.TRIANGLE);
        series3.setColor(Color.YELLOW);

        // Black Rectangles
        PointsGraphSeries<DataPoint> graphScale = new PointsGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(10, 10)
        });
        graph.addSeries(graphScale);
        graphScale.setShape(PointsGraphSeries.Shape.POINT);
        graphScale.setColor(Color.BLACK);

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
    }

    public void onClickPlot(View v) {

        getCoordinates();
    }

    private void getCoordinates() {
        plot = (Button) findViewById(R.id.btnPlot);
        xcoordinate = (EditText) findViewById(R.id.xplot);
        ycoordinate = (EditText) findViewById(R.id.yplot);

        X = Integer.parseInt(xcoordinate.getText().toString());
        Y = Integer.parseInt(ycoordinate.getText().toString());

        //Toast.makeText(this, "The value entered for X is "+X, Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, "The value entered for Y is "+Y, Toast.LENGTH_LONG).show();

        GraphView graph = (GraphView) findViewById(R.id.graph);
        //Green
        PointsGraphSeries<DataPoint> series4 = new PointsGraphSeries<>(new DataPoint[] {
                new DataPoint(X, Y)
        });
        graph.addSeries(series4);
        series4.setColor(Color.GREEN);
        series4.setCustomShape(new PointsGraphSeries.CustomShape() {
            @Override
            public void draw(Canvas canvas, Paint paint, float x, float y, DataPointInterface dataPoint) {
                paint.setStrokeWidth(10);
                canvas.drawLine(x - 20, y - 20, x + 20, y + 20, paint);
                canvas.drawLine(x + 20, y - 20, x - 20, y + 20, paint);
            }
        });

        createGraph();
    }
}
