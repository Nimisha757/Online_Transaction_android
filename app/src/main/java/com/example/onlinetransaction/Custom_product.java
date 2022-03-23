package com.example.onlinetransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Custom_product extends BaseAdapter {
    String[] productid,catid,catname,productname,price,pimage;
    private Context context;
    Button b1;

    public Custom_product(Context appcontext, String[]productid,String[]catid,String[]catname,String[]productname,String[]price,String[]pimage)
    {
        this.context=appcontext;
        this.productid=productid;
        this.catid=catid;
        this.productname=productname;
        this.price=price;
        this.pimage=pimage;
        this.catname=catname;


    }

    @Override
    public int getCount() {
        return price.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflator=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if(view==null)
        {
            gridView=new View(context);
            //gridView=inflator.inflate(R.layout.customview, null);
            gridView=inflator.inflate(R.layout.activity_custom_productview,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tvname=(TextView)gridView.findViewById(R.id.textView10);
        TextView tvcatname=(TextView)gridView.findViewById(R.id.textView8);
        TextView tvprice=(TextView)gridView.findViewById(R.id.textView9) ;
        Button B=(Button)gridView.findViewById(R.id.button10);
        B.setTag(productid[i]);
        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
                SharedPreferences.Editor ed= sh.edit();;
                ed.putString("prdtid",productid[i]);
                ed.putString("price",price[i]);

                ed.commit();
                Toast.makeText(context.getApplicationContext(), "Enter Quantity you needed...", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(context.getApplicationContext(),add_to_cart.class);
                context.startActivity(i);



            }
        });

        tvname.setText(productname[i]);
        tvcatname.setText(catname[i]);
        tvprice.setText(price[i]);
        

        ImageView im=(ImageView) gridView.findViewById(R.id.imageView3);

        tvname.setTextColor(Color.BLACK);


        tvname.setText(productname[i]);


        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
        String ip=sh.getString("ip","");
//
        String url="http://" + ip + ":5000"+pimage[i];


        Picasso.with(context).load(url). into(im);

        return gridView;
    }
}
