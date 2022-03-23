package com.example.onlinetransaction;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Home_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page_activity);
//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.container, HomePageFragment.newInstance())
//                    .commitNow();
//        }
    }
}