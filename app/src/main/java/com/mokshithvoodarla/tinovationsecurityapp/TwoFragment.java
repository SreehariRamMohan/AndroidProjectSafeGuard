package com.mokshithvoodarla.tinovationsecurityapp;

/**
 * Created by Mokshith on 11/24/16.
 */

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TwoFragment extends Fragment implements OnChartGestureListener{
    PieChart mChart;
    LineChart nLineChart;
    String username;
    ArrayList<String> people;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_two, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        people = new ArrayList<>();
        username = getActivity().getIntent().getExtras().get("Username").toString();

        final DatabaseReference root = FirebaseDatabase.getInstance().getReference().child(username).child("Stream");
        root.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator i = dataSnapshot.getChildren().iterator();

                while(i.hasNext()){
                    String k = ((DataSnapshot)i.next()).getKey().toString();
                    root.child(k).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Iterator i = dataSnapshot.getChildren().iterator();
                            String k1 = ((DataSnapshot)i.next()).getValue().toString();
                            people.add(k1);
                            System.out.println(k1 + "LOL");
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
                makechart();


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        super.onActivityCreated(savedInstanceState);
    }
    public void makechart() {
        int total = people.size();
        //find how many of the entries are the same, that shows the frequency of each name
        //people.

        nLineChart = (LineChart) getView().findViewById(R.id.lineChart1);
        /*
        mChart = (PieChart) getView().findViewById(R.id.pieChart1);
        mChart.setOnChartGestureListener(this);
        mChart.setTouchEnabled(true);
        //mChart.setUsePercentValues(true);
        mChart.getDescription().setEnabled(false);
        mChart.setExtraOffsets(5, 10, 5, 5);

        mChart.setDragDecelerationFrictionCoef(0.95f);

        mChart.setCenterTextTypeface(Typeface.SERIF);

        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColor(Color.WHITE);

        mChart.setTransparentCircleColor(Color.WHITE);
        mChart.setTransparentCircleAlpha(110);

        mChart.setHoleRadius(1f);
        mChart.setTransparentCircleRadius(1f);

        mChart.setDrawCenterText(true);

        mChart.setRotationAngle(0);
        mChart.setRotationEnabled(false);
        mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        mChart.setHighlightPerTapEnabled(true);
        List<PieEntry> entries = new ArrayList<>();


        PieDataSet set = new PieDataSet(entries, "People");
        PieEntry p = new Pie
        entries.add()
        int[] c = {Color.RED, Color.BLUE, Color.parseColor("#FFC107"), Color.parseColor("#009688")};
        set.setColors(c);
        set.setValueTextColor(Color.WHITE);
        PieData data = new PieData(set);
        mChart.setData(data);
        mChart.invalidate();
        */

    }
    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

    }

    @Override
    public void onChartLongPressed(MotionEvent me) {

    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {

    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {

    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {

    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {

    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {

    }
}