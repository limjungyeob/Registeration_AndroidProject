package com.example.euihyeon.hira;

import android.app.TabActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends TabActivity {

    private final String[] disease = {"췌장암", "식도암", "위암", "간암"};
    private final String[] localName = {"경기", "서울", "인천", "부산", "대구", "광주", "대전", "울산", "강원", "충복", "충남", "전북", "전남", "경북", "경남", "제주", "세종"};
    private final String[] localName2 = {"경기", "서울", "충청도", "강원도", "경상도", "전라도", "제주도"};

    AppraisalData[] appraisalData1, appraisalData2, appraisalData3, appraisalData4, appraisalData5, appraisalData6, appraisalData7, appraisalData8, appraisalData9, appraisalData10, appraisalData11, appraisalData12, appraisalData13, appraisalData14, appraisalData15, appraisalData16, appraisalData17, appraisalData18;
    ArrayList<AppraisalData> dataSet1, dataSet2, dataSet3, dataSet4, dataSet5, dataSet6, dataSet7, dataSet8;


    private Toolbar ToolBar;
    private Spinner spinner1, spinner2, spinner3;
    private BarChart barChart;
    private LineChart lineChart;
    private Button btnSearch;
    private ListView listView;
    private ListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.enableDefaults();

        /* implement a Toolbar */
        ToolBar = findViewById(R.id.my_toolbar);
        ToolBar.setTitleTextColor(Color.WHITE);
        setActionBar(ToolBar);

        /* implement a Tab */
        TabHost tabHost = getTabHost();

        TabHost.TabSpec tabSpecTab1 = tabHost.newTabSpec("TAB1").setIndicator("의료 질 평가");
        tabSpecTab1.setContent(R.id.tab1);
        tabHost.addTab(tabSpecTab1);

        TabHost.TabSpec tabSpecTab2 = tabHost.newTabSpec("TAB2").setIndicator("지역내 병원");
        tabSpecTab2.setContent(R.id.tab2);
        tabHost.addTab(tabSpecTab2);

        TabHost.TabSpec tabSpecTab3 = tabHost.newTabSpec("TAB3").setIndicator("향후 개발");
        tabSpecTab3.setContent(R.id.tab3);
        tabHost.addTab(tabSpecTab3);

        TabHost.TabSpec tabSpecTab4 = tabHost.newTabSpec("TAB4").setIndicator("개발자");
        tabSpecTab4.setContent(R.id.tab4);
        tabHost.addTab(tabSpecTab4);

        tabHost.setCurrentTab(0);


        /* Change the color of text on a tab */
        for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
            LinearLayout relLayout = (LinearLayout) tabHost.getTabWidget().getChildAt(i);
            TextView tv = (TextView) relLayout.getChildAt(1);
            tv.setTextColor(Color.WHITE);
        }

        /* View Reference */
        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);
        spinner3 = findViewById(R.id.spinner3);
        barChart = findViewById(R.id.barChart);
        lineChart = findViewById(R.id.lineChart);
        btnSearch = findViewById(R.id.btnSearch);
        listView = findViewById(R.id.list);

        /* Run Features */
        firstTab();
        secondTab();
        thirdTab();
    }

    /* Statistics on the quality of care by disease (by region) */
    public void firstTab() {
        /* Create Spinner List */
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, disease);
        spinner1.setAdapter(adapter);

        /* Implement spinner function */
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                ArrayList<BarEntry> entries;
                BarDataSet dataSet;
                BarData barData;
                ArrayList<String> labels = new ArrayList<>();

                for (int i = 0; i < localName2.length; i++) {
                    labels.add(localName2[i]);
                }

                switch (spinner1.getItemAtPosition(position).toString()) {
                    case "간암":
                        Toast.makeText(MainActivity.this, "선택된 아이템 : " + spinner1.getItemAtPosition(position), Toast.LENGTH_SHORT).show(); //Test 1 : 아이템 선택 확인

                        entries = new ArrayList<>();
                        entries.add(new BarEntry((float) 51 / 15, 0));
                        entries.add(new BarEntry((float) 52 / 15, 1));
                        entries.add(new BarEntry((float) 54 / 19, 2));
                        entries.add(new BarEntry((float) 8 / 4, 3));
                        entries.add(new BarEntry((float) 29 / 13, 4));
                        entries.add(new BarEntry((float) 4 / 3, 5));
                        entries.add(new BarEntry((float) 1, 6));

                        dataSet = new BarDataSet(entries, spinner1.getSelectedItem().toString());
                        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

                        barData = new BarData(labels, dataSet);

                        barChart.animateY(1000);
                        barChart.setDescription(null);
                        barChart.setData(barData);
                        break;

                    case "위암":
                        Toast.makeText(MainActivity.this, "선택된 아이템 : " + spinner1.getItemAtPosition(position), Toast.LENGTH_SHORT).show(); //Test 1 : 아이템 선택 확인

                        entries = new ArrayList<>();
                        entries.add(new BarEntry((float) 80 / 45, 0)); //1-6, 4-1, 5-23 34+23 = 80/45
                        entries.add(new BarEntry((float) 67 / 28, 1)); // 1-9, 4-1, 5-18 49+18 = 67/28
                        entries.add(new BarEntry((float) 86 / 36, 2)); //1-11, 4-2, 5-23 63+23 = 86/36
                        entries.add(new BarEntry((float) 9 / 5, 3)); // 1-1, 5-4 9/5
                        entries.add(new BarEntry((float) 58 / 35, 4)); // 1-5,4-1, 5-29 29+29 = 58/35
                        entries.add(new BarEntry((float) 23 / 18, 5)); //1-1, 5-17 5+17 = 23/18
                        entries.add(new BarEntry((float) 5 / 4, 6)); //4-1, 5-3 5/4

                        dataSet = new BarDataSet(entries, spinner1.getSelectedItem().toString());
                        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

                        barData = new BarData(labels, dataSet);

                        barChart.animateY(1000);
                        barChart.setDescription(null);
                        barChart.setData(barData);
                        break;

                    case "식도암":
                        Toast.makeText(MainActivity.this, "선택된 아이템 : " + spinner1.getItemAtPosition(position), Toast.LENGTH_SHORT).show(); //Test 1 : 아이템 선택 확인

                        entries = new ArrayList<>();
                        entries.add(new BarEntry((float) 16 / 12, 0)); //1-1, 5-11 16/12
                        entries.add(new BarEntry((float) 19 / 15, 1)); // 1-1, 5-14 19/15
                        entries.add(new BarEntry((float) 23 / 18, 2)); // 1-1, 5-17 23 /18
                        entries.add(new BarEntry((float) 1, 3));
                        entries.add(new BarEntry((float) 11 / 6, 4)); //1-1 4-1,5-4 11/6
                        entries.add(new BarEntry((float) 1, 5)); // 5-2
                        entries.add(new BarEntry((float) 1, 6));

                        dataSet = new BarDataSet(entries, spinner1.getSelectedItem().toString());
                        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

                        barData = new BarData(labels, dataSet);

                        barChart.animateY(1000);
                        barChart.setDescription(null);
                        barChart.setData(barData);
                        break;

                    case "췌장암":
                        Toast.makeText(MainActivity.this, "선택된 아이템 : " + spinner1.getItemAtPosition(position), Toast.LENGTH_SHORT).show(); //Test 1 : 아이템 선택 확인

                        entries = new ArrayList<>();
                        entries.add(new BarEntry((float) 36 / 20, 0)); //1-4 , 5-16 36/20
                        entries.add(new BarEntry((float) 43 / 19, 1)); //1-6 , 5-13 43/19
                        entries.add(new BarEntry((float) 48 / 23, 2)); // 1-6 4-1, 5-16 32+16 =48/23
                        entries.add(new BarEntry((float) 8 / 4, 3));
                        entries.add(new BarEntry((float) 17 / 13, 4)); // 1-1 5-12 17/13
                        entries.add(new BarEntry((float) 9 / 5, 5)); //1 -1, 5-4 9 / 5
                        entries.add(new BarEntry((float) 1, 6));

                        dataSet = new BarDataSet(entries, spinner1.getSelectedItem().toString());
                        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

                        barData = new BarData(labels, dataSet);

                        barChart.animateY(1000);
                        barChart.setDescription(null);
                        barChart.setData(barData);
                        break;

                }
   /*
   *
   *
   *
   *
   *
                appraisalData2 = new AppraisalData[index]; //&#xacbd;&#xae30;
                appraisalData3 = new AppraisalData[index]; //&#xc11c;&#xc6b8;
                appraisalData4 = new AppraisalData[index]; //&#xcda9;&#xccad;
                appraisalData5 = new AppraisalData[index]; //&#xac15;&#xc6d0;
                appraisalData6 = new AppraisalData[index]; //&#xacbd;&#xc0c1;
                appraisalData7 = new AppraisalData[index]; //&#xc804;&#xb77c;
                appraisalData8 = new AppraisalData[index]; //&#xc81c;&#xc8fc;
                appraisalData9 = new AppraisalData[index]; //&#xc81c;&#xc8fc;
                appraisalData10 = new AppraisalData[index]; //&#xc81c;&#xc8fc;
                appraisalData11 = new AppraisalData[index]; //&#xc81c;&#xc8fc;
                appraisalData12 = new AppraisalData[index]; //&#xc81c;&#xc8fc;
                appraisalData13 = new AppraisalData[index]; //&#xc81c;&#xc8fc;
                appraisalData14 = new AppraisalData[index]; //&#xc81c;&#xc8fc;
                appraisalData15 = new AppraisalData[index]; //&#xc81c;&#xc8fc;
                appraisalData16 = new AppraisalData[index]; //&#xc81c;&#xc8fc;
                appraisalData17 = new AppraisalData[index]; //&#xc81c;&#xc8fc;
                appraisalData18 = new AppraisalData[index]; //&#xc81c;&#xc8fc;

                for (int i = 0; i < index; i++) {
                    appraisalData2[i] = new AppraisalData();
                    appraisalData3[i] = new AppraisalData();
                    appraisalData4[i] = new AppraisalData();
                    appraisalData5[i] = new AppraisalData();
                    appraisalData6[i] = new AppraisalData();
                    appraisalData7[i] = new AppraisalData();
                    appraisalData8[i] = new AppraisalData();
                    appraisalData9[i] = new AppraisalData();
                    appraisalData10[i] = new AppraisalData();
                    appraisalData11[i] = new AppraisalData();
                    appraisalData12[i] = new AppraisalData();
                    appraisalData13[i] = new AppraisalData();
                    appraisalData14[i] = new AppraisalData();
                    appraisalData15[i] = new AppraisalData();
                    appraisalData16[i] = new AppraisalData();
                    appraisalData17[i] = new AppraisalData();
                    appraisalData18[i] = new AppraisalData();
                }

                dataSet2 = new ArrayList<>();
                dataSet3 = new ArrayList<>();
                dataSet4 = new ArrayList<>();
                dataSet5 = new ArrayList<>();
                dataSet6 = new ArrayList<>();
                dataSet7 = new ArrayList<>();
                dataSet8 = new ArrayList<>();


                switch (spinner1.getItemAtPosition(position).toString()) {
                    case "&#xcdcc;&#xc7a5;&#xc554;":
                        Toast.makeText(MainActivity.this, "&#xc120;&#xd0dd;&#xb41c; &#xc544;&#xc774;&#xd15c; : " + spinner1.getItemAtPosition(position), Toast.LENGTH_SHORT).show(); //Test 1 : &#xc544;&#xc774;&#xd15c; &#xc120;&#xd0dd; &#xd655;&#xc778;


                        for (int i = 0; i < localName2.length; i++) {
                            switch (localName2[i]) {
                                case "&#xacbd;&#xae30;":
                                    appraisalData2 = xmlParsingB(appraisalData2, "310000");
                                    appraisalData3 = xmlParsingB(appraisalData3, "220000");
                                    for (int j = 0; j < appraisalData2.length; i++) {
                                        if (!appraisalData2[j].getAsmGrd2().equals("")) {
                                            dataSet2.add(appraisalData2[i]);
                                        }
                                    }
                                    for (int j = 0; j < appraisalData3.length; i++) {
                                        if (!appraisalData3[j].getAsmGrd2().equals("")) {
                                            dataSet2.add(appraisalData3[i]);
                                        }
                                    }
                                    break;

                                case "&#xc11c;&#xc6b8;":
                                    appraisalData4 = xmlParsingB(appraisalData4, "110000");
                                    for (int j = 0; j < appraisalData4.length; i++) {
                                        if (!appraisalData4[j].getAsmGrd2().equals("")) {
                                            dataSet3.add(appraisalData4[i]);
                                        }
                                    }
                                    break;

                                case "&#xcda9;&#xccad;&#xb3c4;":
                                    appraisalData5 = xmlParsingB(appraisalData5, "330000");
                                    appraisalData6 = xmlParsingB(appraisalData6, "340000");
                                    appraisalData7 = xmlParsingB(appraisalData7, "250000");
                                    appraisalData8 = xmlParsingB(appraisalData8, "410000");
                                    for (int j = 0; j < appraisalData5.length; i++) {
                                        if (!appraisalData5[j].getAsmGrd2().equals("")) {
                                            dataSet4.add(appraisalData5[i]);
                                        }
                                    }
                                    for (int j = 0; j < appraisalData6.length; i++) {
                                        if (!appraisalData6[j].getAsmGrd2().equals("")) {
                                            dataSet4.add(appraisalData6[i]);
                                        }
                                    }
                                    for (int j = 0; j < appraisalData7.length; i++) {
                                        if (!appraisalData7[j].getAsmGrd2().equals("")) {
                                            dataSet4.add(appraisalData7[i]);
                                        }
                                    }
                                    for (int j = 0; j < appraisalData8.length; i++) {
                                        if (!appraisalData8[j].getAsmGrd2().equals("")) {
                                            dataSet4.add(appraisalData8[i]);
                                        }
                                    }
                                    break;
                                case "&#xac15;&#xc6d0;&#xb3c4;":
                                    appraisalData9 = xmlParsingB(appraisalData9, "320000");
                                    for (int j = 0; j < appraisalData9.length; i++) {
                                        if (!appraisalData9[j].getAsmGrd2().equals("")) {
                                            dataSet5.add(appraisalData9[i]);
                                        }
                                    }
                                    break;
                                case "&#xacbd;&#xc0c1;&#xb3c4;":
                                    appraisalData10 = xmlParsingB(appraisalData10, "370000");
                                    appraisalData11 = xmlParsingB(appraisalData11, "380000");
                                    appraisalData12 = xmlParsingB(appraisalData12, "210000");
                                    appraisalData13 = xmlParsingB(appraisalData13, "230000");
                                    appraisalData14 = xmlParsingB(appraisalData14, "260000");
                                    for (int j = 0; j < appraisalData10.length; i++) {
                                        if (!appraisalData10[j].getAsmGrd2().equals("")) {
                                            dataSet6.add(appraisalData10[i]);
                                        }
                                    }
                                    for (int j = 0; j < appraisalData11.length; i++) {
                                        if (!appraisalData11[j].getAsmGrd2().equals("")) {
                                            dataSet6.add(appraisalData11[i]);
                                        }
                                    }
                                    for (int j = 0; j < appraisalData12.length; i++) {
                                        if (!appraisalData12[j].getAsmGrd2().equals("")) {
                                            dataSet6.add(appraisalData12[i]);
                                        }
                                    }
                                    for (int j = 0; j < appraisalData13.length; i++) {
                                        if (!appraisalData13[j].getAsmGrd2().equals("")) {
                                            dataSet6.add(appraisalData13[i]);
                                        }
                                    }
                                    for (int j = 0; j < appraisalData14.length; i++) {
                                        if (!appraisalData14[j].getAsmGrd2().equals("")) {
                                            dataSet6.add(appraisalData14[i]);
                                        }
                                    }
                                    break;
                                case "&#xc804;&#xb77c;&#xb3c4;":
                                    appraisalData15 = xmlParsingB(appraisalData15, "240000");
                                    appraisalData16 = xmlParsingB(appraisalData16, "350000");
                                    appraisalData17 = xmlParsingB(appraisalData17, "360000");
                                    for (int j = 0; j < appraisalData15.length; i++) {
                                        if (!appraisalData15[j].getAsmGrd2().equals("")) {
                                            dataSet7.add(appraisalData15[i]);
                                        }
                                    }
                                    for (int j = 0; j < appraisalData16.length; i++) {
                                        if (!appraisalData16[j].getAsmGrd2().equals("")) {
                                            dataSet7.add(appraisalData16[i]);
                                        }
                                    }
                                    for (int j = 0; j < appraisalData17.length; i++) {
                                        if (!appraisalData17[j].getAsmGrd2().equals("")) {
                                            dataSet7.add(appraisalData17[i]);
                                        }
                                    }
                                    break;
                                case "&#xc81c;&#xc8fc;&#xb3c4;":
                                    appraisalData18 = xmlParsingB(appraisalData18, "390000");
                                    for (int j = 0; j < appraisalData18.length; i++) {
                                        if (!appraisalData18[j].getAsmGrd2().equals("")) {
                                            dataSet8.add(appraisalData18[i]);
                                        }
                                    }
                                    break;
                            }
                        }
                        break;

                    case "&#xc2dd;&#xb3c4;&#xc554;":
                        Toast.makeText(MainActivity.this, "&#xc120;&#xd0dd;&#xb41c; &#xc544;&#xc774;&#xd15c; : " + spinner1.getItemAtPosition(position), Toast.LENGTH_SHORT).show(); //Test 1 : &#xc544;&#xc774;&#xd15c; &#xc120;&#xd0dd; &#xd655;&#xc778;


                        for (int i = 0; i < localName2.length; i++) {
                            switch (localName2[i]) {
                                case "&#xacbd;&#xae30;":
                                    appraisalData2 = xmlParsingB(appraisalData2, "310000");
                                    appraisalData3 = xmlParsingB(appraisalData3, "220000");
                                    for (int j = 0; j < appraisalData2.length; i++) {
                                        if (!appraisalData2[j].getAsmGrd3().equals("")) {
                                            dataSet2.add(appraisalData2[i]);
                                        }
                                    }
                                    for (int j = 0; j < appraisalData3.length; i++) {
                                        if (!appraisalData3[j].getAsmGrd3().equals("")) {
                                            dataSet2.add(appraisalData3[i]);
                                        }
                                    }
                                    break;
                                case "&#xc11c;&#xc6b8;":
                                    appraisalData4 = xmlParsingB(appraisalData4, "110000");
                                    for (int j = 0; j < appraisalData4.length; i++) {
                                        if (!appraisalData4[j].getAsmGrd3().equals("")) {
                                            dataSet3.add(appraisalData4[i]);
                                        }
                                    }

                                    break;
                                case "&#xcda9;&#xccad;&#xb3c4;":
                                    appraisalData5 = xmlParsingB(appraisalData5, "330000");
                                    appraisalData6 = xmlParsingB(appraisalData6, "340000");
                                    appraisalData7 = xmlParsingB(appraisalData7, "250000");
                                    appraisalData8 = xmlParsingB(appraisalData8, "410000");
                                    for (int j = 0; j < appraisalData5.length; i++) {
                                        if (!appraisalData5[j].getAsmGrd3().equals("")) {
                                            dataSet4.add(appraisalData5[i]);
                                        }
                                    }
                                    for (int j = 0; j < appraisalData6.length; i++) {
                                        if (!appraisalData6[j].getAsmGrd3().equals("")) {
                                            dataSet4.add(appraisalData6[i]);
                                        }
                                    }
                                    for (int j = 0; j < appraisalData7.length; i++) {
                                        if (!appraisalData7[j].getAsmGrd3().equals("")) {
                                            dataSet4.add(appraisalData7[i]);
                                        }
                                    }
                                    for (int j = 0; j < appraisalData8.length; i++) {
                                        if (!appraisalData8[j].getAsmGrd3().equals("")) {
                                            dataSet4.add(appraisalData8[i]);
                                        }
                                    }
                                    break;
                                case "&#xac15;&#xc6d0;&#xb3c4;":
                                    appraisalData9 = xmlParsingB(appraisalData9, "320000");
                                    for (int j = 0; j < appraisalData9.length; i++) {
                                        if (!appraisalData9[j].getAsmGrd3().equals("")) {
                                            dataSet5.add(appraisalData9[i]);
                                        }
                                    }
                                    break;
                                case "&#xacbd;&#xc0c1;&#xb3c4;":
                                    appraisalData10 = xmlParsingB(appraisalData10, "370000");
                                    appraisalData11 = xmlParsingB(appraisalData11, "380000");
                                    appraisalData12 = xmlParsingB(appraisalData12, "210000");
                                    appraisalData13 = xmlParsingB(appraisalData13, "230000");
                                    appraisalData14 = xmlParsingB(appraisalData14, "260000");
                                    for (int j = 0; j < appraisalData10.length; i++) {
                                        if (!appraisalData10[j].getAsmGrd3().equals("")) {
                                            dataSet6.add(appraisalData10[i]);
                                        }
                                    }
                                    for (int j = 0; j < appraisalData11.length; i++) {
                                        if (!appraisalData11[j].getAsmGrd3().equals("")) {
                                            dataSet6.add(appraisalData11[i]);
                                        }
                                    }
                                    for (int j = 0; j < appraisalData12.length; i++) {
                                        if (!appraisalData12[j].getAsmGrd3().equals("")) {
                                            dataSet6.add(appraisalData12[i]);
                                        }
                                    }
                                    for (int j = 0; j < appraisalData13.length; i++) {
                                        if (!appraisalData13[j].getAsmGrd3().equals("")) {
                                            dataSet6.add(appraisalData13[i]);
                                        }
                                    }
                                    for (int j = 0; j < appraisalData14.length; i++) {
                                        if (!appraisalData14[j].getAsmGrd3().equals("")) {
                                            dataSet6.add(appraisalData14[i]);
                                        }
                                    }
                                    break;
                                case "&#xc804;&#xb77c;&#xb3c4;":
                                    appraisalData15 = xmlParsingB(appraisalData15, "240000");
                                    appraisalData16 = xmlParsingB(appraisalData16, "350000");
                                    appraisalData17 = xmlParsingB(appraisalData17, "360000");
                                    for (int j = 0; j < appraisalData15.length; i++) {
                                        if (!appraisalData15[j].getAsmGrd3().equals("")) {
                                            dataSet7.add(appraisalData15[i]);
                                        }
                                    }
                                    for (int j = 0; j < appraisalData16.length; i++) {
                                        if (!appraisalData16[j].getAsmGrd3().equals("")) {
                                            dataSet7.add(appraisalData16[i]);
                                        }
                                    }
                                    for (int j = 0; j < appraisalData17.length; i++) {
                                        if (!appraisalData17[j].getAsmGrd3().equals("")) {
                                            dataSet7.add(appraisalData17[i]);
                                        }
                                    }
                                    break;
                                case "&#xc81c;&#xc8fc;&#xb3c4;":
                                    appraisalData18 = xmlParsingB(appraisalData18, "390000");
                                    for (int j = 0; j < appraisalData18.length; i++) {
                                        if (!appraisalData18[j].getAsmGrd3().equals("")) {
                                            dataSet8.add(appraisalData18[i]);
                                        }
                                    }
                                    break;
                            }
                        }
                        break;

                    case "&#xc704;&#xc554;":
                        Toast.makeText(MainActivity.this, "&#xc120;&#xd0dd;&#xb41c; &#xc544;&#xc774;&#xd15c; : " + spinner1.getItemAtPosition(position), Toast.LENGTH_SHORT).show(); //Test 1 : &#xc544;&#xc774;&#xd15c; &#xc120;&#xd0dd; &#xd655;&#xc778;


                        for (int i = 0; i < localName2.length; i++) {
                            switch (localName2[i]) {
                                case "&#xacbd;&#xae30;":
                                    appraisalData2 = xmlParsingB(appraisalData2, "310000");
                                    appraisalData3 = xmlParsingB(appraisalData3, "220000");
                                    for (int j = 0; j < appraisalData2.length; i++) {
                                        if (!appraisalData2[j].getAsmGrd5().equals("")) {
                                            dataSet2.add(appraisalData2[i]);
                                        }
                                    }
                                    for (int j = 0; j < appraisalData3.length; i++) {
                                        if (!appraisalData3[j].getAsmGrd5().equals("")) {
                                            dataSet2.add(appraisalData3[i]);
                                        }
                                    }
                                    break;
                                case "&#xc11c;&#xc6b8;":
                                    appraisalData4 = xmlParsingB(appraisalData4, "110000");
                                    for (int j = 0; j < appraisalData4.length; i++) {
                                        if (!appraisalData4[j].getAsmGrd5().equals("")) {
                                            dataSet3.add(appraisalData4[i]);
                                        }
                                    }

                                    break;
                                case "&#xcda9;&#xccad;&#xb3c4;":
                                    appraisalData5 = xmlParsingB(appraisalData5, "330000");
                                    appraisalData6 = xmlParsingB(appraisalData6, "340000");
                                    appraisalData7 = xmlParsingB(appraisalData7, "250000");
                                    appraisalData8 = xmlParsingB(appraisalData8, "410000");
                                    for (int j = 0; j < appraisalData5.length; i++) {
                                        if (!appraisalData5[j].getAsmGrd5().equals("")) {
                                            dataSet4.add(appraisalData5[i]);
                                        }
                                    }
                                    for (int j = 0; j < appraisalData6.length; i++) {
                                        if (!appraisalData6[j].getAsmGrd5().equals("")) {
                                            dataSet4.add(appraisalData6[i]);
                                        }
                                    }
                                    for (int j = 0; j < appraisalData7.length; i++) {
                                        if (!appraisalData7[j].getAsmGrd5().equals("")) {
                                            dataSet4.add(appraisalData7[i]);
                                        }
                                    }
                                    for (int j = 0; j < appraisalData8.length; i++) {
                                        if (!appraisalData8[j].getAsmGrd5().equals("")) {
                                            dataSet4.add(appraisalData8[i]);
                                        }
                                    }
                                    break;
                                case "&#xac15;&#xc6d0;&#xb3c4;":
                                    appraisalData9 = xmlParsingB(appraisalData9, "320000");
                                    for (int j = 0; j < appraisalData9.length; i++) {
                                        if (!appraisalData9[j].getAsmGrd5().equals("")) {
                                            dataSet5.add(appraisalData9[i]);
                                        }
                                    }
                                    break;
                                case "&#xacbd;&#xc0c1;&#xb3c4;":
                                    appraisalData10 = xmlParsingB(appraisalData10, "370000");
                                    appraisalData11 = xmlParsingB(appraisalData11, "380000");
                                    appraisalData12 = xmlParsingB(appraisalData12, "210000");
                                    appraisalData13 = xmlParsingB(appraisalData13, "230000");
                                    appraisalData14 = xmlParsingB(appraisalData14, "260000");
                                    for (int j = 0; j < appraisalData10.length; i++) {
                                        if (!appraisalData10[j].getAsmGrd5().equals("")) {
                                            dataSet6.add(appraisalData10[i]);
                                        }
                                    }
                                    for (int j = 0; j < appraisalData11.length; i++) {
                                        if (!appraisalData11[j].getAsmGrd5().equals("")) {
                                            dataSet6.add(appraisalData11[i]);
                                        }
                                    }
                                    for (int j = 0; j < appraisalData12.length; i++) {
                                        if (!appraisalData12[j].getAsmGrd5().equals("")) {
                                            dataSet6.add(appraisalData12[i]);
                                        }
                                    }
                                    for (int j = 0; j < appraisalData13.length; i++) {
                                        if (!appraisalData13[j].getAsmGrd5().equals("")) {
                                            dataSet6.add(appraisalData13[i]);
                                        }
                                    }
                                    for (int j = 0; j < appraisalData14.length; i++) {
                                        if (!appraisalData14[j].getAsmGrd5().equals("")) {
                                            dataSet6.add(appraisalData14[i]);
                                        }
                                    }
                                    break;
                                case "&#xc804;&#xb77c;&#xb3c4;":
                                    appraisalData15 = xmlParsingB(appraisalData15, "240000");
                                    appraisalData16 = xmlParsingB(appraisalData16, "350000");
                                    appraisalData17 = xmlParsingB(appraisalData17, "360000");
                                    for (int j = 0; j < appraisalData15.length; i++) {
                                        if (!appraisalData15[j].getAsmGrd5().equals("")) {
                                            dataSet7.add(appraisalData15[i]);
                                        }
                                    }
                                    for (int j = 0; j < appraisalData16.length; i++) {
                                        if (!appraisalData16[j].getAsmGrd5().equals("")) {
                                            dataSet7.add(appraisalData16[i]);
                                        }
                                    }
                                    for (int j = 0; j < appraisalData17.length; i++) {
                                        if (!appraisalData17[j].getAsmGrd5().equals("")) {
                                            dataSet7.add(appraisalData17[i]);
                                        }
                                    }
                                    break;
                                case "&#xc81c;&#xc8fc;&#xb3c4;":
                                    appraisalData18 = xmlParsingB(appraisalData18, "390000");
                                    for (int j = 0; j < appraisalData18.length; i++) {
                                        if (!appraisalData18[j].getAsmGrd5().equals("")) {
                                            dataSet8.add(appraisalData18[i]);
                                        }
                                    }
                                    break;
                            }
                        }
                        break;

                    case "&#xac04;&#xc554;":
                        Toast.makeText(MainActivity.this, "&#xc120;&#xd0dd;&#xb41c; &#xc544;&#xc774;&#xd15c; : " + spinner1.getItemAtPosition(position), Toast.LENGTH_SHORT).show(); //Test 1 : &#xc544;&#xc774;&#xd15c; &#xc120;&#xd0dd; &#xd655;&#xc778;


                        for (int i = 0; i < localName2.length; i++) {
                            switch (localName2[i]) {
                                case "&#xacbd;&#xae30;":
                                    appraisalData2 = xmlParsingB(appraisalData2, "310000");
                                    appraisalData3 = xmlParsingB(appraisalData3, "220000");
                                    for (int j = 0; j < appraisalData2.length; i++) {
                                        if (!appraisalData2[j].getAsmGrd6().equals("")) {
                                            dataSet2.add(appraisalData2[i]);
                                        }
                                    }
                                    for (int j = 0; j < appraisalData3.length; i++) {
                                        if (!appraisalData3[j].getAsmGrd6().equals("")) {
                                            dataSet2.add(appraisalData3[i]);
                                        }
                                    }
                                    break;
                                case "&#xc11c;&#xc6b8;":
                                    appraisalData4 = xmlParsingB(appraisalData4, "110000");
                                    for (int j = 0; j < appraisalData4.length; i++) {
                                        if (!appraisalData4[j].getAsmGrd6().equals("")) {
                                            dataSet3.add(appraisalData4[i]);
                                        }
                                    }

                                    break;
                                case "&#xcda9;&#xccad;&#xb3c4;":
                                    appraisalData5 = xmlParsingB(appraisalData5, "330000");
                                    appraisalData6 = xmlParsingB(appraisalData6, "340000");
                                    appraisalData7 = xmlParsingB(appraisalData7, "250000");
                                    appraisalData8 = xmlParsingB(appraisalData8, "410000");
                                    for (int j = 0; j < appraisalData5.length; i++) {
                                        if (!appraisalData5[j].getAsmGrd6().equals("")) {
                                            dataSet4.add(appraisalData5[i]);
                                        }
                                    }
                                    for (int j = 0; j < appraisalData6.length; i++) {
                                        if (!appraisalData6[j].getAsmGrd6().equals("")) {
                                            dataSet4.add(appraisalData6[i]);
                                        }
                                    }
                                    for (int j = 0; j < appraisalData7.length; i++) {
                                        if (!appraisalData7[j].getAsmGrd6().equals("")) {
                                            dataSet4.add(appraisalData7[i]);
                                        }
                                    }
                                    for (int j = 0; j < appraisalData8.length; i++) {
                                        if (!appraisalData8[j].getAsmGrd6().equals("")) {
                                            dataSet4.add(appraisalData8[i]);
                                        }
                                    }
                                    break;
                                case "&#xac15;&#xc6d0;&#xb3c4;":
                                    appraisalData9 = xmlParsingB(appraisalData9, "320000");
                                    for (int j = 0; j < appraisalData9.length; i++) {
                                        if (!appraisalData9[j].getAsmGrd6().equals("")) {
                                            dataSet5.add(appraisalData9[i]);
                                        }
                                    }
                                    break;
                                case "&#xacbd;&#xc0c1;&#xb3c4;":
                                    appraisalData10 = xmlParsingB(appraisalData10, "370000");
                                    appraisalData11 = xmlParsingB(appraisalData11, "380000");
                                    appraisalData12 = xmlParsingB(appraisalData12, "210000");
                                    appraisalData13 = xmlParsingB(appraisalData13, "230000");
                                    appraisalData14 = xmlParsingB(appraisalData14, "260000");
                                    for (int j = 0; j < appraisalData10.length; i++) {
                                        if (!appraisalData10[j].getAsmGrd6().equals("")) {
                                            dataSet6.add(appraisalData10[i]);
                                        }
                                    }
                                    for (int j = 0; j < appraisalData11.length; i++) {
                                        if (!appraisalData11[j].getAsmGrd6().equals("")) {
                                            dataSet6.add(appraisalData11[i]);
                                        }
                                    }
                                    for (int j = 0; j < appraisalData12.length; i++) {
                                        if (!appraisalData12[j].getAsmGrd6().equals("")) {
                                            dataSet6.add(appraisalData12[i]);
                                        }
                                    }
                                    for (int j = 0; j < appraisalData13.length; i++) {
                                        if (!appraisalData13[j].getAsmGrd6().equals("")) {
                                            dataSet6.add(appraisalData13[i]);
                                        }
                                    }
                                    for (int j = 0; j < appraisalData14.length; i++) {
                                        if (!appraisalData14[j].getAsmGrd6().equals("")) {
                                            dataSet6.add(appraisalData14[i]);
                                        }
                                    }
                                    break;
                                case "&#xc804;&#xb77c;&#xb3c4;":
                                    appraisalData15 = xmlParsingB(appraisalData15, "240000");
                                    appraisalData16 = xmlParsingB(appraisalData16, "350000");
                                    appraisalData17 = xmlParsingB(appraisalData17, "360000");
                                    for (int j = 0; j < appraisalData15.length; i++) {
                                        if (!appraisalData15[j].getAsmGrd6().equals("")) {
                                            dataSet7.add(appraisalData15[i]);
                                        }
                                    }
                                    for (int j = 0; j < appraisalData16.length; i++) {
                                        if (!appraisalData16[j].getAsmGrd6().equals("")) {
                                            dataSet7.add(appraisalData16[i]);
                                        }
                                    }
                                    for (int j = 0; j < appraisalData17.length; i++) {
                                        if (!appraisalData17[j].getAsmGrd6().equals("")) {
                                            dataSet7.add(appraisalData17[i]);
                                        }
                                    }
                                    break;
                                case "&#xc81c;&#xc8fc;&#xb3c4;":
                                    appraisalData18 = xmlParsingB(appraisalData18, "390000");
                                    for (int j = 0; j < appraisalData18.length; i++) {
                                        if (!appraisalData18[j].getAsmGrd6().equals("")) {
                                            dataSet8.add(appraisalData18[i]);
                                        }
                                    }
                                    break;
                            }
                        }
                        break;

                }
                Float[] value = new Float[7];
                for (int i = 0; i < value.length; i++) {
                    value[i] = new Float(0);
                }
*/
   /*
                for (int i = 0; i < dataSet2.size(); i++) {
                    value[0] = value[0] + Float.parseFloat(dataSet2.get(i).getAsmGrd2());
                }
                for (int i = 0; i < dataSet3.size(); i++) {
                    value[1] = value[1] + Float.parseFloat(dataSet3.get(i).getAsmGrd2());
                }
                for (int i = 0; i < dataSet4.size(); i++) {
                    value[2] = value[2] + Float.parseFloat(dataSet4.get(i).getAsmGrd2());
                }
                for (int i = 0; i < dataSet5.size(); i++) {
                    value[3] = value[3] + Float.parseFloat(dataSet5.get(i).getAsmGrd2());
                }
                for (int i = 0; i < dataSet6.size(); i++) {
                    value[4] = value[4] + Float.parseFloat(dataSet6.get(i).getAsmGrd2());
                }
                for (int i = 0; i < dataSet7.size(); i++) {
                    value[5] = value[5] + Float.parseFloat(dataSet7.get(i).getAsmGrd2());
                }
                for (int i = 0; i < dataSet8.size(); i++) {
                    value[6] = value[6] + Float.parseFloat(dataSet8.get(i).getAsmGrd2());
                }

 */

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    /* List of medical institution recommendations by disease (in region) */
    public void secondTab() {
        /* Create Spinner List */
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, localName);
        spinner2.setAdapter(adapter2);

        /* Create Spinner List */
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, disease);
        spinner3.setAdapter(adapter3);



        /* List Output */
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* Apply Adapter */
                adapter = new ListViewAdapter();
                listView.setAdapter(adapter);

                /* Create Data Set */
                appraisalData1 = new AppraisalData[50];
                dataSet1 = new ArrayList<>();

                for (int i = 0; i < appraisalData1.length; i++)
                    appraisalData1[i] = new AppraisalData();

                String selectDisease = spinner3.getSelectedItem().toString();
                String selectRegion;

                /* Selected Value */
                switch (spinner2.getSelectedItem().toString()) {
                    case "경기":
                        selectRegion = "310000";
                        break;
                    case "서울":
                        selectRegion = "110000";
                        break;
                    case "인천":
                        selectRegion = "220000";
                        break;
                    case "부산":
                        selectRegion = "210000";
                        break;
                    case "대구":
                        selectRegion = "230000";
                        break;
                    case "광주":
                        selectRegion = "240000";
                        break;
                    case "대전":
                        selectRegion = "250000";
                        break;
                    case "울산":
                        selectRegion = "260000";
                        break;
                    case "강원":
                        selectRegion = "320000";
                        break;
                    case "충북":
                        selectRegion = "330000";
                        break;
                    case "충남":
                        selectRegion = "340000";
                        break;
                    case "전북":
                        selectRegion = "350000";
                        break;
                    case "전남":
                        selectRegion = "360000";
                        break;
                    case "경북":
                        selectRegion = "370000";
                        break;
                    case "경남":
                        selectRegion = "380000";
                        break;
                    case "제주":
                        selectRegion = "390000";
                        break;
                    case "세종":
                        selectRegion = "410000";
                        break;
                    default:
                        selectRegion = "110000";
                }

                /* Parsing */
                xmlParsingA(selectRegion);



                /* Extract Data (Remove space data)*/
                switch (selectDisease) {
                    case "췌장암":
                        for (int i = 0; i < appraisalData1.length; i++) {
                            if (!appraisalData1[i].getAsmGrd2().equals("")) {
                                switch (appraisalData1[i].getAsmGrd2()) {
                                    case "1":
                                        appraisalData1[i].setGrade("1");
                                        break;
                                    case "2":
                                        appraisalData1[i].setGrade("2");
                                        break;
                                    case "3":
                                        appraisalData1[i].setGrade("3");
                                        break;
                                    case "4":
                                        appraisalData1[i].setGrade("4");
                                        break;
                                    case "5":
                                        appraisalData1[i].setGrade("5");
                                        break;
                                    default:
                                        appraisalData1[i].setGrade("");
                                }
                                dataSet1.add(appraisalData1[i]);
                            }
                        }
                        break;

                    case "식도암":
                        for (int i = 0; i < appraisalData1.length; i++) {
                            if (!appraisalData1[i].getAsmGrd3().equals("")) {
                                switch (appraisalData1[i].getAsmGrd3()) {
                                    case "1":
                                        appraisalData1[i].setGrade("1");
                                        break;
                                    case "2":
                                        appraisalData1[i].setGrade("2");
                                        break;
                                    case "3":
                                        appraisalData1[i].setGrade("3");
                                        break;
                                    case "4":
                                        appraisalData1[i].setGrade("4");
                                        break;
                                    case "5":
                                        appraisalData1[i].setGrade("5");
                                        break;
                                    default:
                                        appraisalData1[i].setGrade("");
                                }
                                dataSet1.add(appraisalData1[i]);
                            }
                        }
                        break;

                    case "위암":
                        for (int i = 0; i < appraisalData1.length; i++) {
                            if (!appraisalData1[i].getAsmGrd5().equals("")) {
                                switch (appraisalData1[i].getAsmGrd5()) {
                                    case "1":
                                        appraisalData1[i].setGrade("1");
                                        break;
                                    case "2":
                                        appraisalData1[i].setGrade("2");
                                        break;
                                    case "3":
                                        appraisalData1[i].setGrade("3");
                                        break;
                                    case "4":
                                        appraisalData1[i].setGrade("4");
                                        break;
                                    case "5":
                                        appraisalData1[i].setGrade("5");
                                        break;
                                    default:
                                        appraisalData1[i].setGrade("");
                                }
                                dataSet1.add(appraisalData1[i]);
                            }
                        }
                        break;

                    case "간암":
                        for (int i = 0; i < appraisalData1.length; i++) {
                            if (!appraisalData1[i].getAsmGrd6().equals("")) {
                                switch (appraisalData1[i].getAsmGrd6()) {
                                    case "1":
                                        appraisalData1[i].setGrade("1");
                                        break;
                                    case "2":
                                        appraisalData1[i].setGrade("2");
                                        break;
                                    case "3":
                                        appraisalData1[i].setGrade("3");
                                        break;
                                    case "4":
                                        appraisalData1[i].setGrade("4");
                                        break;
                                    case "5":
                                        appraisalData1[i].setGrade("5");
                                        break;
                                    default:
                                        appraisalData1[i].setGrade("");
                                }
                                dataSet1.add(appraisalData1[i]);
                            }
                        }
                        break;
                }

                /* DataSet Sort */
                dataSet1 = sortData(dataSet1);

                /* Extract Data (Change Data) */
                for (int i = 0; i < dataSet1.size(); i++) {
                    switch (dataSet1.get(i).getGrade()) {
                        case "1":
                            dataSet1.get(i).setGrade("최우수");
                            break;
                        case "2":
                            dataSet1.get(i).setGrade("우수");
                            break;
                        case "3":
                            dataSet1.get(i).setGrade("양호");
                            break;
                        case "4":
                            dataSet1.get(i).setGrade("보통");
                            break;
                        case "5":
                            dataSet1.get(i).setGrade("미흡");
                            break;
                    }
                }

                /* Data Output */
                for (int i = 0; i < dataSet1.size(); i++) {
                    adapter.addItem(dataSet1.get(i).getGrade(), dataSet1.get(i).getYadmName(), dataSet1.get(i).getAddr(), "등급 : " +
                            (selectDisease.equals("췌장암") ? dataSet1.get(i).getAsmGrd2() : selectDisease.equals("식도암") ? dataSet1.get(i).getAsmGrd3() : selectDisease.equals("위암") ?
                                    dataSet1.get(i).getAsmGrd5() : dataSet1.get(i).getAsmGrd6()));
                }

            }

        });

    }

    /* Third Screen */
    public void thirdTab() {
        ArrayList<LineDataSet> dataSets;
        ArrayList<Entry> entries;
        ArrayList<Entry> entries1;
        LineDataSet dataSet;
        LineDataSet dataSet1;
        LineData lineData;
        ArrayList<String> labels = new ArrayList<>();

        for (int i = 1; i < 13; i++) {
            labels.add(i + "월");
        }

        entries = new ArrayList<>();
        entries.add(new Entry((float) 12, 0));
        entries.add(new Entry((float) 12, 1));
        entries.add(new Entry((float) 12, 2));
        entries.add(new Entry((float) 12, 3));
        entries.add(new Entry((float) 12, 4));
        entries.add(new Entry((float) 13, 5));
        entries.add(new Entry((float) 13, 6));
        entries.add(new Entry((float) 15, 7));
        entries.add(new Entry((float) 18, 8));
        entries.add(new Entry((float) 17, 9));
        entries.add(new Entry((float) 18, 10));
        entries.add(new Entry((float) 17, 11));

        entries1 = new ArrayList<>();
        entries1.add(new Entry((float) 16, 0));
        entries1.add(new Entry((float) 15, 1));
        entries1.add(new Entry((float) 16, 2));
        entries1.add(new Entry((float) 17, 3));
        entries1.add(new Entry((float) 17, 4));
        entries1.add(new Entry((float) 16, 5));
        entries1.add(new Entry((float) 15, 6));
        entries1.add(new Entry((float) 14, 7));
        entries1.add(new Entry((float) 13, 8));
        entries1.add(new Entry((float) 12, 9));
        entries1.add(new Entry((float) 15, 10));
        entries1.add(new Entry((float) 15, 11));

        dataSets = new ArrayList<>();

        dataSet = new LineDataSet(entries, "프로포폴 경서제약");
        dataSet1 = new LineDataSet(entries1, "프로포폴 경동제약");

        dataSet.setColor(Color.BLUE);
        dataSet1.setColor(Color.GREEN);
        dataSet.setDrawCubic(true);
        dataSet1.setDrawCubic(true);

        dataSets.add(dataSet);
        dataSets.add(dataSet1);

        lineData = new LineData(labels, dataSet);
        lineData.addDataSet(dataSet1);

        lineChart.animateY(1000);
        lineChart.setDescription(null);
        lineChart.setData(lineData);


    }

    /* Parsing Data */
    public void xmlParsingA(String selectRegion) {
        String identity = ""; // Tag Separator
        int i = 0;

        try {
            URL url = new URL("http://apis.data.go.kr/B551182/hospAsmRstInfoService/getSoprDiagQtyAsmRstList?serviceKey=iCGRcn5BqABDMxoNutE4bpu6zLAvWh7EIWsGsfW6nVhME9xmo%2Fqfbht0SluR5%2F58ycLb1YTvi65E2onEVN4asg%3D%3D&numOfRows=244&pageNo=1&clCd=11&sidoCd=" + selectRegion);

            XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserCreator.newPullParser();

            parser.setInput(url.openStream(), "utf-8");
            int parserEvent = parser.getEventType();

            while (parserEvent != XmlPullParser.END_DOCUMENT) {

                switch (parserEvent) {
                    case XmlPullParser.START_TAG:
                        if (parser.getName().equals("yadmNm"))
                            identity = "yadmNm";
                        if (parser.getName().equals("addr"))
                            identity = "addr";
                        if (parser.getName().equals("asmGrd2"))
                            identity = "asmGrd2";
                        if (parser.getName().equals("asmGrd3"))
                            identity = "asmGrd3";
                        if (parser.getName().equals("asmGrd5"))
                            identity = "asmGrd5";
                        if (parser.getName().equals("asmGrd6"))
                            identity = "asmGrd6";
                        break;

                    case XmlPullParser.TEXT:
                        if (identity.equals("yadmNm")) {
                            appraisalData1[i].setYadmName(parser.getText());
                            identity = "";
                        }
                        if (identity.equals("addr")) {
                            appraisalData1[i].setAddr(parser.getText());
                            identity = "";
                        }
                        if (identity.equals("asmGrd2")) {
                            appraisalData1[i].setAsmGrd2(parser.getText());
                            identity = "";
                        }
                        if (identity.equals("asmGrd3")) {
                            appraisalData1[i].setAsmGrd3(parser.getText());
                            identity = "";
                        }
                        if (identity.equals("asmGrd5")) {
                            appraisalData1[i].setAsmGrd5(parser.getText());
                            identity = "";
                        }
                        if (identity.equals("asmGrd6")) {
                            appraisalData1[i].setAsmGrd6(parser.getText());
                            identity = "";
                        }
                        break;

                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("item")) {
                            identity = "";
                            i++;
                        }
                        break;
                }
                parserEvent = parser.next();
            }

            Log.v("테스트1", "---------------- PARSING 이후 값  -----------------");

            for (int j = 0; j < appraisalData1.length; j++) {
                Log.v("테스트1", "---------------" + j + "------------------");
                Log.v("테스트0", appraisalData1[j].getYadmName());
                Log.v("테스트0", appraisalData1[j].getAddr());
                Log.v("테스트0", appraisalData1[j].getAsmGrd2());
                Log.v("테스트0", appraisalData1[j].getAsmGrd3());
                Log.v("테스트0", appraisalData1[j].getAsmGrd5());
                Log.v("테스트0", appraisalData1[j].getAsmGrd6());
                Log.v("테스트0", appraisalData1[j].getGrade());
            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Parsing Error : " + e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    /* Parsing Data */
    public AppraisalData[] xmlParsingB(AppraisalData[] temp, String selectRegion) {
        String identity = ""; // Tag Separator
        int i = 0;

        try {
            URL url = new URL("http://apis.data.go.kr/B551182/hospAsmRstInfoService/getSoprDiagQtyAsmRstList?serviceKey=iCGRcn5BqABDMxoNutE4bpu6zLAvWh7EIWsGsfW6nVhME9xmo%2Fqfbht0SluR5%2F58ycLb1YTvi65E2onEVN4asg%3D%3D&numOfRows=244&pageNo=1&clCd=11&sidoCd=" + selectRegion);

            XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserCreator.newPullParser();

            parser.setInput(url.openStream(), "utf-8");
            int parserEvent = parser.getEventType();

            while (parserEvent != XmlPullParser.END_DOCUMENT) {

                switch (parserEvent) {
                    case XmlPullParser.START_TAG:
                        if (parser.getName().equals("yadmNm"))
                            identity = "yadmNm";
                        if (parser.getName().equals("addr"))
                            identity = "addr";
                        if (parser.getName().equals("asmGrd2"))
                            identity = "asmGrd2";
                        if (parser.getName().equals("asmGrd3"))
                            identity = "asmGrd3";
                        if (parser.getName().equals("asmGrd5"))
                            identity = "asmGrd5";
                        if (parser.getName().equals("asmGrd6"))
                            identity = "asmGrd6";
                        break;

                    case XmlPullParser.TEXT:
                        if (identity.equals("yadmNm")) {
                            temp[i].setYadmName(parser.getText());
                            identity = "";
                        }
                        if (identity.equals("addr")) {
                            temp[i].setAddr(parser.getText());
                            identity = "";
                        }
                        if (identity.equals("asmGrd2")) {
                            temp[i].setAsmGrd2(parser.getText());
                            identity = "";
                        }
                        if (identity.equals("asmGrd3")) {
                            temp[i].setAsmGrd3(parser.getText());
                            identity = "";
                        }
                        if (identity.equals("asmGrd5")) {
                            temp[i].setAsmGrd5(parser.getText());
                            identity = "";
                        }
                        if (identity.equals("asmGrd6")) {
                            temp[i].setAsmGrd6(parser.getText());
                            identity = "";
                        }
                        break;

                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("item")) {
                            identity = "";
                            i++;
                        }
                        break;
                }
                parserEvent = parser.next();
            }

            Log.v("테스트1", "---------------- PARSING 이후 값  -----------------");

            for (int j = 0; j < temp.length; j++) {
                Log.v("테스트1", "---------------" + j + "------------------");
                Log.v("테스트0", temp[j].getYadmName());
                Log.v("테스트0", temp[j].getAddr());
                Log.v("테스트0", temp[j].getAsmGrd2());
                Log.v("테스트0", temp[j].getAsmGrd3());
                Log.v("테스트0", temp[j].getAsmGrd5());
                Log.v("테스트0", temp[j].getAsmGrd6());
                Log.v("테스트0", temp[j].getGrade());
            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Parsing Error : " + e.toString(), Toast.LENGTH_LONG).show();
        }
        return temp;
    }

    public ArrayList<AppraisalData> sortData(ArrayList<AppraisalData> sortData) {

        Collections.sort(sortData, new Comparator<AppraisalData>() {
            @Override
            public int compare(AppraisalData appraisalData, AppraisalData t1) {
                return appraisalData.getGrade().compareTo(t1.getGrade());
            }
        });

        return sortData;
    }


}

