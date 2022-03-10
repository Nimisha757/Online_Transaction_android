package com.example.onlinetransaction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class Userregistration extends AppCompatActivity implements View.OnClickListener {
    EditText edp1;
    EditText edp2;
    EditText edp3;
    EditText edp4;
    EditText edp5;
    EditText edp6;
    EditText edp7;
    Button btn;
    ImageView imv;
    String path, atype, fname, attach, attatch1;
    byte[] byteArray = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userregistration);
        edp1 = (EditText) findViewById(R.id.editTextTextPersonName3);
        edp2 = (EditText) findViewById(R.id.editTextTextPersonName6);
        edp3 = (EditText) findViewById(R.id.editTextTextPersonName7);
        edp4 = (EditText) findViewById(R.id.editTextTextPersonName8);
        edp5 = (EditText) findViewById(R.id.editTextTextPersonName10);
        edp7 = (EditText) findViewById(R.id.editTextTextPersonName12);
        edp6 = (EditText) findViewById(R.id.editTextTextPersonName4);
        btn = (Button) findViewById(R.id.button3);
        imv = (ImageView) findViewById(R.id.imageView);
        btn.setOnClickListener(this);
        imv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == imv) {


        } else {
            final String name = edp1.getText().toString();
            final String place = edp2.getText().toString();
            final String pin = edp3.getText().toString();
            final String post = edp4.getText().toString();
            final String phone = edp5.getText().toString();
            final String email = edp6.getText().toString();
            final String password = edp7.getText().toString();

            if (name.length() == 0) {
                edp1.setError("Field cannot be empty!");


            } else if (place.length() == 0) {
                edp2.setError("Field cannot be empty!");


            } else if (pin.length() == 0) {
                edp3.setError("Field cannot be empty!");

            } else if (post.length() == 0) {
                edp4.setError("Field cannot be empty!");

            } else if (phone.length() == 0) {
                edp5.setError("Field cannot be empty!");

            } else if (email.length() == 0) {
                edp6.setError("Field cannot be empty!");

            } else if (password.length() == 0) {
                edp7.setError("Field cannot be empty!");
            } else {
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

                String hu = sh.getString("ip", "");
                String url = "http://" + hu + ":5000/and_signup";
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


                                        Intent i = new Intent(getApplicationContext(), loginpage.class);
                                        startActivity(i);
                                    }


                                    // }
                                    else {
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


                        params.put("uname", name);
                        params.put("place", place);
                        params.put("pin", pin);
                        params.put("post", post);
                        params.put("photo",attach);
                        params.put("phone", phone);
                        params.put("email", email);
                        params.put("password", password);


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



    void showfilechooser(int string) {
        // TODO Auto-generated method stub
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        //getting all types of files

        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(Intent.createChooser(intent, "Select a File to Upload"), string);
        } catch (android.content.ActivityNotFoundException ex) {
            // Potentially direct the user to the Market with a Dialog
            Toast.makeText(getApplicationContext(), "Please install a File Manager.", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                ////
                Uri uri = data.getData();

                try {
                    path = FileUtils.getPath(this, uri);

                    File fil = new File(path);
                    float fln = (float) (fil.length() / 1024);
                    atype = path.substring(path.lastIndexOf(".") + 1);


                    fname = path.substring(path.lastIndexOf("/") + 1);
                   // ed15.setText(fname);
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }

                try {

                    File imgFile = new File(path);

                    if (imgFile.exists()) {

                        Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                        imv.setImageBitmap(myBitmap);

                    }


                    File file = new File(path);
                    byte[] b = new byte[8192];
                    Log.d("bytes read", "bytes read");

                    InputStream inputStream = new FileInputStream(file);
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();

                    int bytesRead = 0;

                    while ((bytesRead = inputStream.read(b)) != -1) {
                        bos.write(b, 0, bytesRead);
                    }
                    byteArray = bos.toByteArray();

                    String str = Base64.encodeToString(byteArray, Base64.NO_WRAP);
                    attach = str;


                } catch (Exception e) {
                    Toast.makeText(this, "String :" + e.getMessage().toString(), Toast.LENGTH_LONG).show();
                }

                ///

            }
        }

    }

}










