package com.shane_taylor.Graphy;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

import java.util.Random;

/**
 * Points Graph
 */

public class Graph1 extends Activity {

    private double Xuser;
    private double Yuser;
    private double Xrand;
    private double Yrand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_graph1);
        createRandomPoint();
        createGraphScale();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    private void createRandomPoint() {
        Random generator = new Random();
        Xrand = -10 + generator.nextInt(20);
        Yrand = -10 + generator.nextInt(20);

        GraphView graph = (GraphView) findViewById(R.id.graph);
        PointsGraphSeries<DataPoint> randomPoint = new PointsGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(Xrand, Yrand)
        });
        graph.addSeries(randomPoint);
        randomPoint.setShape(PointsGraphSeries.Shape.POINT);
        randomPoint.setColor(Color.BLUE);
        randomPoint.setSize(7);
    }

    private void createGraphScale() {
        GraphView graph = (GraphView) findViewById(R.id.graph);

        PointsGraphSeries<DataPoint> graphScale = new PointsGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(-10, -10),
                new DataPoint(10, 10)
        });
        graph.addSeries(graphScale);
        graphScale.setShape(PointsGraphSeries.Shape.POINT);
        graphScale.setColor(Color.BLACK);
        graphScale.setSize(0001);

        try {
            int x = 10;
            for (int y = 10; y > -10; y--) {
                LineGraphSeries<DataPoint> fineLinesX = new LineGraphSeries<>(new DataPoint[]{
                        new DataPoint(x * -1, y),
                        new DataPoint(x, y)
                });

                LineGraphSeries<DataPoint> fineLinesY = new LineGraphSeries<>(new DataPoint[]{
                        new DataPoint(y, -1 * x),
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
        } catch (Exception e) {
            Toast.makeText(this, "Creating fineLines didn't work", Toast.LENGTH_SHORT).show();
        }
    }

    private void validate() {
        TextView pointInstructions = (TextView) findViewById(R.id.pointInstructions);
        if (Xuser == (Xrand * -1) && Yuser == Yrand && pointInstructions.getText() == getResources().getString(R.string.pointInstruction1)) {
            Toast toast = new Toast(this);
            ImageView view = new ImageView(this);
            view.setImageResource(R.drawable.correct_large);
            toast.setView(view);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.show();
            pointInstructions.setText(R.string.pointInstruction2);
            clearForm((ViewGroup) findViewById(R.id.pointsGridLayout));
        } else if (Xuser == Xrand && Yuser == (Yrand * -1) && (pointInstructions.getText() == getResources().getString(R.string.pointInstruction2))) {
            Toast toast = new Toast(this);
            ImageView view = new ImageView(this);
            view.setImageResource(R.drawable.correct_large);
            toast.setView(view);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.show();
            pointInstructions.setText(R.string.newPoint);
            clearForm((ViewGroup) findViewById(R.id.pointsGridLayout));
        } else {
            Toast toast = new Toast(this);
            ImageView view = new ImageView(this);
            view.setImageResource(R.drawable.try_again_large);
            toast.setView(view);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private void getCoordinates() {
        EditText xcoordinate = (EditText) findViewById(R.id.xplot);
        EditText ycoordinate = (EditText) findViewById(R.id.yplot);
        try {
            Xuser = Double.parseDouble(xcoordinate.getText().toString());
            Yuser = Double.parseDouble(ycoordinate.getText().toString());
            if (Xuser > 10 || Yuser > 10) {
                Toast.makeText(this, R.string.outOfBounds, Toast.LENGTH_SHORT).show();

            }
            else {
                validate();
                createUserPoint();
            }
        } catch (Exception e) {
            Toast.makeText(this, R.string.blank, Toast.LENGTH_SHORT).show();
        }
    }

    private void createUserPoint() {
        GraphView graph = (GraphView) findViewById(R.id.graph);
        PointsGraphSeries<DataPoint> userSeries = new PointsGraphSeries<>(new DataPoint[]{
                new DataPoint(Xuser, Yuser)
        });
        graph.addSeries(userSeries);
        userSeries.setColor(Color.parseColor("#ff8a05"));
        userSeries.setSize(5);
        userSeries.setShape(PointsGraphSeries.Shape.POINT);
        /*
        userSeries.setCustomShape(new PointsGraphSeries.CustomShape() {
        *@Override
        *public void draw(Canvas canvas, Paint paint, float x, float y, DataPointInterface dataPoint) {
        *       paint.setStrokeWidth(5);
        *       canvas.drawLine(x-10, y-10, x+10, y+10, paint);
        *       canvas.drawLine(x+10, y-10, x-10, y+10, paint);
        *   }
        *});
        */
    }

    private void clearForm(ViewGroup group) {
        for (int i = 0, count = group.getChildCount(); i < count; ++i) {
            View view = group.getChildAt(i);
            if (view instanceof EditText) {
                ((EditText) view).setText("");
            }

            if (view instanceof ViewGroup && (((ViewGroup) view).getChildCount() > 0))
                clearForm((ViewGroup) view);
        }
    }

    public void onClickPlot(View view) {
        getCoordinates();
    }

    public void onClickReset(View view) {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    public void onClickLineGraph(View view) {
        Intent intent = new Intent(this, Graph2.class);
        startActivity(intent);
    }

    public void onClickMenu(View view) {
        Intent intent = new Intent(this, MenuPageActivity.class);
        startActivity(intent);
    }
}
