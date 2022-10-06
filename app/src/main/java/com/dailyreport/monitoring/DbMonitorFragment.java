package com.dailyreport.monitoring;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dailyreport.R;
import com.dailyreport.chart.BarChartImple;
import com.dailyreport.chart.LineChartImple;
import com.dailyreport.chart.PieChartImple;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class DbMonitorFragment extends Fragment {
    public ViewGroup rootview;
    public BarChart barChart;
    public LineChart lineChart;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = (ViewGroup) inflater.inflate(R.layout.fragment_shap_five_main, container, false);
        if (savedInstanceState != null) { }

        initSettion();
        //showBarChart();
        return rootview;
    }

    private void initSettion() {
        //barChart = rootview.findViewById(R.id.graph_one_ly);

        //로딩
        //데이터 통신을 하고 리턴을 데깅
        BarChartImple barChartImple = new BarChartImple(rootview);
        LineChartImple lineChartImple = new LineChartImple(rootview);
        PieChartImple pieChartImple = new PieChartImple(rootview);
    }
/*
* BarEntry   : x, y 쌍으로 실제 데이터 조합
* BarDataSet : 단순데이터를 막대모양으로 표시해 주기 위한 막대모양 설정
* BarData    : 보여질 데이터 구성
* */
    private void showBarChart(){

        ArrayList<Double> valueList = new ArrayList<Double>();
        ArrayList<BarEntry> entries = new ArrayList<>();
        String title = "전자사명 완료건수";

        //index
        for(int i = 0; i < 6; i++){
            valueList.add(i * 100.1);
        }

        //#1.X, Y 데이터 BarEntry(x,y) 담기 y값은 float
        for (int i = 0; i < valueList.size(); i++) {
            BarEntry barEntry = new BarEntry(i, valueList.get(i).floatValue());
            entries.add(barEntry);
        }
        //#2.
        BarDataSet barDataSet = new BarDataSet(entries, title);
        barDataSet.setColor(Color.parseColor("#21E312"));
        barDataSet.setDrawIcons(false);


        BarData data = new BarData(barDataSet);
        barChart.setData(data);
        barChart.invalidate();
    }

}
