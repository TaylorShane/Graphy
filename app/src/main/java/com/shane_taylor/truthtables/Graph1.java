package com.shane_taylor.truthtables;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.PointsGraphSeries;


public class Graph1 extends AppCompatActivity {

    public EditText xcoordinate;
    public EditText ycoordinate;
    public int X = 0;
    public int Y = 0;
    private Button plot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph1);

        createGraph();
    }

    private void createGraph() {
        // Blue Dots
        GraphView graph = (GraphView) findViewById(R.id.graph);
        /*
        PointsGraphSeries<DataPoint> series = new PointsGraphSeries<>(new DataPoint[] {
                new DataPoint(X, Y)
        });
        graph.addSeries(series);
        series.setShape(PointsGraphSeries.Shape.POINT);
        */

        // Black Rectangles
        PointsGraphSeries<DataPoint> series2 = new PointsGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(-20, -20),
                new DataPoint(20, 20)
        });
        graph.addSeries(series2);
        series2.setShape(PointsGraphSeries.Shape.RECTANGLE);
        series2.setColor(Color.BLACK);
        series2.setSize(0001);

        /*
        // Yellow Triangles
        PointsGraphSeries<DataPoint> series3 = new PointsGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 0)
        });
        graph.addSeries(series3);
        series3.setShape(PointsGraphSeries.Shape.TRIANGLE);
        series3.setColor(Color.YELLOW);
    */

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
