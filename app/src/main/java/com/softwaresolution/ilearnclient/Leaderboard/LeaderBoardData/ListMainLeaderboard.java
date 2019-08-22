package com.softwaresolution.ilearnclient.Leaderboard.LeaderBoardData;

import android.app.Activity;
import android.graphics.Color;
import android.os.Debug;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.softwaresolution.ilearnclient.JsonData.AllData;
import com.softwaresolution.ilearnclient.JsonData.DataLeaderboard;
import com.softwaresolution.ilearnclient.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListMainLeaderboard extends ArrayAdapter<String> {
    Activity context;
    List<String> name;
    List<String> score;
    int max = 0 ;
    public ListMainLeaderboard(Activity context, List<String> name,List<String> score) {
        super(context, R.layout.list_main_leaderboard,name);
        this.context = context;
        this.name = name;
        this.score = score;
        for(int i = 0 ; i < score.size(); i++){
            if(Integer.parseInt(score.get(i)) > max){
                max = Integer.parseInt(score.get(i));
            }
        }
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View gridviewitem = inflater.inflate(R.layout.list_main_leaderboard,null,true);
        TextView txt_no = (TextView) gridviewitem.findViewById(R.id.txt_no);
        TextView txt_head = (TextView) gridviewitem.findViewById(R.id.txt_head);
        TextView txt_subhead = (TextView) gridviewitem.findViewById(R.id.txt_subhead);
        HorizontalBarChart chart = (HorizontalBarChart) gridviewitem.findViewById(R.id.chart);
        List<BarEntry> barEntries = new ArrayList<BarEntry>();
        barEntries.add(new BarEntry(0f, Float.parseFloat(score.get(position))));
        txt_no.setText(String.valueOf(position+1));
        txt_head.setText("Name : "+ name.get(position));
        txt_subhead.setText("Score : "+ score.get(position));
        Log.d("Score",score.get(position)+" " +max);
        XAxis xAxis = chart.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setEnabled(false);
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
        data.setDrawValues(false);
        chart.setData(data);
        chart.animateXY(1000, 1000);
        chart.setDrawValueAboveBar(false);
        chart.getDescription().setEnabled(false);
        chart.getLegend().setEnabled(false);
        chart.setDrawBarShadow(false);
        chart.setPinchZoom(false);
        chart.setDoubleTapToZoomEnabled(false);
        chart.setTouchEnabled(false);
        barDataSet.setColors(ColorTemplate.JOYFUL_COLORS[position]);
        data.setValueTextSize(13f);
        data.setValueTextColor(Color.DKGRAY);
        chart.invalidate();

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.removeAllLimitLines();
        leftAxis.setAxisMaximum(max);
        leftAxis.setAxisMinimum(0f);
        leftAxis.enableGridDashedLine(10f,10f,0f);
        leftAxis.setDrawLimitLinesBehindData(true);
        return gridviewitem;
    }
}
