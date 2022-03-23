package com.example.onlinetransaction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

public class ViewPurchaseHistory extends AppCompatActivity {
    ListView Lv;
    String[]omid,lid,totalamt,date,place,pin,post,district;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

//        Lv=(ListView) findViewById(R.id.LV);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_purchase_history);

        Lv=(ListView) findViewById(R.id.LV);
        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        String hu = sh.getString("ip", "");
        String url = "http://" + hu + ":5000/and_purchasehistory";
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
                                totalamt=new String[js.length()];
                                date=new String[js.length()];
                                place=new String[js.length()];
                                pin=new String[js.length()];
                                post=new String[js.length()];
                                district=new String[js.length()];
                                omid=new  String[js.length()];
                                lid=new String[js.length()];




                                for(int i=0;i<js.length();i++)
                                {
                                    JSONObject u=js.getJSONObject(i);
                                    omid[i]=u.getString("omid");
                                    lid[i]=u.getString("lid");
                                    totalamt[i]=u.getString("totalamt");
                                    date[i]=u.getString("date");

                                    place[i]=u.getString("place");

                                    pin[i]=u.getString("pin");
                                    post[i]=u.getString("post");
                                    district[i]=u.getString("district");




//                                    type[i]=u.getString("type");
//                                    discription[i]=u.getString("description");
//                                    image[i]=u.getString("image");
//                                    status[i]=u.getString("status");


                                }
//
                                // ArrayAdapter<String> adpt=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,name);
//                                gv.setAdapter(new Custom_view_visited_game(getApplicationContext(),name,gamecode));
                                Lv.setAdapter(new custom_viewpurchase(getApplicationContext(),omid,lid,totalamt,date,place,pin,post,district));
                                // l1.setAdapter(new Custom(getApplicationContext(),gamecode,name,type,discription,image,status));

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







