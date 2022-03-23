package com.example.onlinetransaction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity implements View.OnClickListener {
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btn1=(Button) findViewById(R.id.button5);
        btn2=(Button) findViewById(R.id.button6);
        btn3=(Button) findViewById(R.id.button7);
        btn4=(Button) findViewById(R.id.button8);
        btn5=(Button) findViewById(R.id.button9);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);





    }


    @Override
    public void onClick(View view) {
        if (view == btn1) {
            Intent i = new Intent(getApplicationContext(), ProfileView.class);
            startActivity(i);
        }


        else if (view==btn2){
                     Intent i=new Intent(getApplicationContext(),ViewProduct.class);
                     startActivity(i);
        }
        else if(view==btn3){
            Intent i=new Intent(getApplicationContext(),ViewCart.class);
            startActivity(i);
        }
        else if(view==btn4){
            Intent i=new Intent(getApplicationContext(),ViewPurchaseHistory.class);
            startActivity(i);
        }
        else if(view==btn5){
            Intent i=new Intent(getApplicationContext(),loginpage.class);
            startActivity(i);
        }


    }
}