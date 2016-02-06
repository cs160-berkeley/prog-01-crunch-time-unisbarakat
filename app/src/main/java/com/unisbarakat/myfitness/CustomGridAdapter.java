package com.unisbarakat.myfitness;

/**
 * Created by unisbarakat on 1/31/16.
 */

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;

public class CustomGridAdapter extends BaseAdapter{

    Context ctx ;
    String items[] = {"Pushup", "Situp", "Jumping Jacks", "Jogging", "Pushup", "Situp", "Jumping Jacks", "Jogging", "Pushup", "Situp", "Jumping Jacks", "Jogging"}; //Can be images or video or any other content

    public CustomGridAdapter(Context c) {
        ctx = c;
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int position) {
        return items[position] ;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        EditText et = new EditText(ctx);

        if (position % 2 == 0) {
            et.setInputType(0x00000002);
//            et.setPadding(0, 0, 0, 0);
        } else {
            et.setInputType(0x00000024);
//            et.setPadding(0, 0, 0, 0);
        }

        et.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.cat, 0, 0);



        DisplayMetrics metrics = ctx.getResources().getDisplayMetrics();
        int width = metrics.widthPixels / 3;
        int height = metrics.heightPixels / 3;
        et.setLayoutParams(new GridView.LayoutParams(width, height));

        et.setGravity(Gravity.CENTER);




        return et;
    }

}

