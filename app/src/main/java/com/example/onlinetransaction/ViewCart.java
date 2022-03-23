package com.example.onlinetransaction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ViewCart extends AppCompatActivity {
    ListView Lv;
    Button b1;
    String[]cartid,productid,quantity,lid,catname,productname,price,pimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cart);
        Lv=(ListView) findViewById(R.id.LV);
        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());



        String hu = sh.getString("ip", "");
        String url = "http://" + hu + ":5000/and_cartview";
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
                                JSONArray js= jsonObj.getJSONArray("users");
                                cartid=new String[js.length()];
                                productid=new String[js.length()];
                                quantity=new String[js.length()];
                                lid=new String[js.length()];
                                catname=new  String[js.length()];
                                productname=new  String[js.length()];
                                price=new  String[js.length()];
                                pimage=new  String[js.length()];



                                for(int i=0;i<js.length();i++)
                                {
                                    JSONObject u=js.getJSONObject(i);
                                    cartid[i]=u.getString("cartid");
                                    productid[i]=u.getString("productid");

                                    quantity[i]=u.getString("quantity");
                                    lid[i]=u.getString("lid");
                                    catname[i]=u.getString("catname");
                                    productname[i]=u.getString("productname");
                                    price[i]=u.getString("price");
                                    pimage[i]=u.getString("pimage");

                                }
                              Lv.setAdapter(new custom_cartview(getApplicationContext(),cartid,productid,quantity,lid,catname,productname,price,pimage));

                            }


                            // }
                            else {
                                Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();
                            }

                        }    catch (Exception e) {
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


                params.put("lid",sh.getString("lid",""));

//                params.put("psw",password);
//                params.put("mac",maclis);

                return params;
            }
        };

        int MY_SOCKET_TIMEOUT_MS=100000;

        postRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(postRequest);
    }

}





