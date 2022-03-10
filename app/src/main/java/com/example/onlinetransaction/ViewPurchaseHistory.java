package com.example.onlinetransaction;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class ViewPurchaseHistory extends AppCompatActivity {
    ListView Lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Lv=(ListView) findViewById(R.id.LV);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_purchase_history);
    }
}