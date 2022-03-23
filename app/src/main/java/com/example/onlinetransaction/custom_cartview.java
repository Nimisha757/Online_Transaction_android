package com.example.onlinetransaction;

import android.content.Intent;
import android.widget.BaseAdapter;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class custom_cartview extends BaseAdapter implements View.OnClickListener {
    String [] cartid,productid,quantity,lid,catname,productname,price,pimage;
    private Context context;
    public custom_cartview(Context appcontext, String[]cartid, String[]productid,String[]quantity,String[]lid,String[]catname,String[]productname,String[]price,String[]pimage){
        this.context=appcontext;
        this.cartid=cartid;
        this.productid=productid;
        this.quantity=quantity;
        this.pimage=pimage;
        this.lid=lid;
        this.productname=productname;
        this.catname=catname;
        this.price=price;

    }



    @Override
    public int getCount() {
        return lid.length;
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
            gridView=inflator.inflate(R.layout.activity_custom_cartview,null);

        }
        else
        {
            gridView=(View)view;}

;
        TextView tvcatname=(TextView)gridView.findViewById(R.id.textView11);
        TextView tvproductname=(TextView)gridView.findViewById(R.id.textView12) ;
        TextView tvquantity=(TextView)gridView.findViewById(R.id.textView13) ;
        TextView tvprice=(TextView)gridView.findViewById(R.id.textView14) ;
        Button b1=(Button) gridView.findViewById(R.id.button12) ;
        b1.setOnClickListener(this);

        tvcatname.setTextColor(Color.BLACK);
        tvproductname.setTextColor(Color.BLACK);
        tvquantity.setTextColor(Color.BLACK);
        tvprice.setTextColor(Color.BLACK);




        tvcatname.setText(catname[i]);
        tvproductname.setText(productname[i]);
        tvquantity.setText(quantity[i]);
        tvprice.setText(price[i]);





        ImageView im=(ImageView) gridView.findViewById(R.id.imageView4);









        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
        String ip=sh.getString("ip","");
//
//        String url="http://" + ip + ":5000"+p;
        String url="http://" + ip + ":5000"+pimage[i];



        Picasso.with(context).load(url). into(im);

        return gridView;
    }

    @Override
    public void onClick(View view) {
//        Intent i=new Intent(getApplicationcontext(),add_to_cart.class);

    }
}

