package com.example.onlinetransaction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
EditText edip;
Button btip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edip=(EditText) findViewById(R.id.editTextTextPersonName);
        btip=(Button)  findViewById(R.id.button);
        btip.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        final String ip_connect = edip.getText().toString();
        if(ip_connect.length() ==0){
            edip.setError("Field cannot be empty!");
        }
        else {

            SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor ed= sh.edit();;
            ed.putString("ip",ip_connect);
            ed.commit();
            Intent i=new Intent(getApplicationContext(),loginpage.class);
            startActivity(i);

        }




    }
}