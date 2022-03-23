package com.example.onlinetransaction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class loginpage extends AppCompatActivity implements View.OnClickListener {
    EditText ed1;
    EditText ed2;
    Button btn;
    TextView tx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);
        ed1 = (EditText) findViewById(R.id.editTextTextPersonName5);
        ed2 = (EditText) findViewById(R.id.editTextTextPersonName11);
        btn = (Button) findViewById(R.id.button4);
        tx = (TextView) findViewById(R.id.textView7);
        btn.setOnClickListener(this);
        tx.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if(view==tx){
            Intent i=new Intent(getApplicationContext(),Userregistration.class);
            startActivity(i);

        }
        else if(view==btn) {
            final String username = ed1.getText().toString();
            final String password = ed2.getText().toString();
            if (username.length() == 0) {
                ed1.setError("Field cannot be empty!");
            } else if (password.length() == 0) {

                ed2.setError("Field cannot be empty!");

            } else {

                Toast.makeText(this, "--------else", Toast.LENGTH_SHORT).show();

                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

                String hu = sh.getString("ip", "");
                String url = "http://" + hu + ":5000/and_login_post";

                Toast.makeText(this, "url====" + url, Toast.LENGTH_SHORT).show();
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                                // response
                                try {
                                    JSONObject jsonObj = new JSONObject(response);
                                    if (jsonObj.getString("status").equalsIgnoreCase("ok")) {
                                        String lid = jsonObj.getString("lid");
                                        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                        SharedPreferences.Editor ed = sh.edit();
                                        ;
                                        ed.putString("lid", lid);
                                        ed.commit();

                                        Intent i = new Intent(getApplicationContext(), TRANSACTION.class);
                                        startActivity(i);

                                    } else {
                                        Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();
                                    }

                                } catch (Exception e) {
                                    Toast.makeText(getApplicationContext(), "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // error
                                Toast.makeText(getApplicationContext(), "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                ) {
                    @Override
                    protected Map<String, String> getParams() {
                        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        Map<String, String> params;
                        params = new HashMap<String, String>();


                        params.put("uname", username);

                        params.put("psw", password);
//                params.put("mac",maclis);

                        return params;
                    }
                };

                int MY_SOCKET_TIMEOUT_MS = 100000;

                postRequest.setRetryPolicy(new DefaultRetryPolicy(
                        MY_SOCKET_TIMEOUT_MS,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                requestQueue.add(postRequest);
            }
        }

    }
}