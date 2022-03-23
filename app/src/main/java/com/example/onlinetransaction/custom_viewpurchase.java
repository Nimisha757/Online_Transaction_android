package com.example.onlinetransaction;
import android.widget.BaseAdapter;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;



public class custom_viewpurchase extends  BaseAdapter {
    String[] omid, lid, totalamt, date, place, pin, post, district;

    private Context context;

    public custom_viewpurchase(Context appcontext, String[] omid, String[] lid, String[] totalamt, String[] date, String[] place, String[] pin, String[] post, String[] district) {
        this.context = appcontext;
        this.date = date;
        this.place = place;
        this.omid = omid;
        this.pin = pin;
        this.totalamt = totalamt;
        this.district = district;
        this.post = post;
        this.lid=lid;

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
        return 0;}



        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater inflator=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View gridView;
            if(view==null)
            {
                gridView=new View(context);
                //gridView=inflator.inflate(R.layout.customview, null);
                gridView=inflator.inflate(R.layout.activity_custom_viewpurchase1,null);

            }
            else
            {
                gridView=(View)view;

            }
        TextView tvdate=(TextView)gridView.findViewById(R.id.textView15);
        TextView tvtotalamt=(TextView)gridView.findViewById(R.id.textView16) ;


        tvdate.setText(date[i]);
        tvtotalamt.setText(totalamt[i]);





//        ImageView im=(ImageView) gridView.findViewById(R.id.imageView3);

//        tvcatname.setTextColor(Color.BLACK);





        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
        String ip=sh.getString("ip","");
//
//        String url="http://" + ip + ":5000/static/game/"+gamecode[i]+".jpg";
//
//
//        Picasso.with(context).load(url).transform(new CircleTransform()). into(im);

        return gridView;
    }
}



