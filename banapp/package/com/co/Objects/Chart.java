package com.example.proiect_android_ionanamaria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Chart extends AppCompatActivity {

    ArrayList<Card> list;
    LinearLayout layout;
    Map<String, Integer> source;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        Intent intent = getIntent();

        list = (ArrayList<Card>) intent.getSerializableExtra("listCh");

        source = getSource(list);

        layout = findViewById(R.id.layoutBar);
        layout.addView(new ChartView(getApplicationContext(), source));
    }

    private Map<String, Integer> getSource(List<Card> Cards)
    {
        if(Cards == null || Cards.isEmpty())
            return new HashMap<>();
        else
        {
            Map<String, Integer> results = new HashMap<>();
            for(Card Card: Cards)
                if(results.containsKey(Card.getBalance()))
                    results.put(Card.getCurrency(), results.get(Card.getBalance())+1);
                else
                    results.put(Card.getCurrency(), 1);
            return results;
        }
    }
}

