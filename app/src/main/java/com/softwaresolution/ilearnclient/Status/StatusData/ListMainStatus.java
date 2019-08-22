package com.softwaresolution.ilearnclient.Status.StatusData;

import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.softwaresolution.ilearnclient.JsonData.AllData;
import com.softwaresolution.ilearnclient.R;

import java.util.ArrayList;
import java.util.List;

public class ListMainStatus extends ArrayAdapter<String> {

    Activity context;
    List<BarEntry> barEntries;
    ArrayList<String> xVals;
    List<String> maintopic;
    List<String> subtopic;
    List<String> score;
    public ListMainStatus(Activity context, List<String> maintopic, List<String> subtopic
                          , List<String> score) {
        super(context, R.layout.list_main_status,AllData.dataStatus.maintopic);
        this.context = context;
        this.maintopic = maintopic;
        this.subtopic = subtopic;
        this.score = score;
    }
    int heightchart;
    int heightlinear;
    int linearget;
    @NonNull
    @Override
    public View getView(int position, View convertView,ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View gridviewitem = inflater.inflate(R.layout.list_main_status, null, true);
        TextView txt_main = (TextView) gridviewitem.findViewById(R.id.txt_main);
        HorizontalBarChart chart = (HorizontalBarChart) gridviewitem.findViewById(R.id.chart);
        LinearLayout linearLayout = (LinearLayout) gridviewitem.findViewById(R.id.linear);
        ViewGroup.LayoutParams params = linearLayout.getLayoutParams();
        ViewGroup.LayoutParams chartLayoutParams = chart.getLayoutParams();
        barEntries = new ArrayList<BarEntry>();
        xVals = new ArrayList<String>(subtopic);
        XAxis xAxis = chart.getXAxis();
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return xVals.get((int) value);
            }
        });
        xAxis.setGranularity(1f);
        txt_main.setText(maintopic.get(position));
        int minusheight = 80;
        for (int i = 0 ; i < subtopic.size();i++){
            barEntries.add(new BarEntry(i,
                    Float.parseFloat(score.get(i))));
            heightchart= heightchart + minusheight;
            minusheight = minusheight - 10;
        }
        chartLayoutParams.height = heightchart;
//        params.height = linearget;
        BarDataSet barDataSet =new BarDataSet(barEntries,"");
        barDataSet.setDrawValues(true);
        chart.getXAxis().setLabelCount(barEntries.size());
        barDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        YAxis yAxisLeft = chart.getAxisLeft();
        yAxisLeft.setDrawGridLines(false);
        yAxisLeft.setEnabled(false);


        YAxis yAxisRight = chart.getAxisRight();
        yAxisRight.setDrawGridLines(false);
        yAxisRight.setEnabled(false);


        BarData data = new BarData(barDataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setBarWidth(0.8f);
        chart.setData(data);
        chart.animateXY(1000, 1000);
        chart.setDrawValueAboveBar(false);
        chart.getDescription().setEnabled(false);
        chart.getLegend().setEnabled(false);
//
//        chart.setDrawBarShadow(false);
//        chart.setPinchZoom(false);
//        chart.setDoubleTapToZoomEnabled(false);
//        chart.setTouchEnabled(false);

        barDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        data.setValueTextSize(13f);
        data.setValueTextColor(Color.DKGRAY);
        chart.invalidate();

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.removeAllLimitLines();
        leftAxis.setAxisMinimum(0f);
        leftAxis.enableGridDashedLine(10f,10f,0f);
        leftAxis.setDrawLimitLinesBehindData(true);

        return  gridviewitem;
    }
}
