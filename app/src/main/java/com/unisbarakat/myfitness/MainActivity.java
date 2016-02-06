package com.unisbarakat.myFitness;

/**
 * Created by unisbarakat on 2/4/16.
 */

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridView = (GridView)findViewById(R.id.gridview);
        gridView.setAdapter(new MyAdapter(this));

    }



    private class MyAdapter extends BaseAdapter
    {
        private List<Item> items = new ArrayList<Item>();
        private LayoutInflater inflater;
        private List<String> list = new ArrayList<String>();



        public MyAdapter(Context context)
        {
        inflater = LayoutInflater.from(context);

        items.add(new Item("0", R.drawable.push_up, "reps"));
        items.add(new Item("0", R.drawable.sit_up, "reps"));
        items.add(new Item("0", R.drawable.squats, "reps"));
        items.add(new Item("0", R.drawable.leg_lift));
        items.add(new Item("0", R.drawable.plank));
        items.add(new Item("0", R.drawable.jumping_jacks));
        items.add(new Item("0", R.drawable.pull_up, "reps"));
        items.add(new Item("0", R.drawable.cycling));
        items.add(new Item("0", R.drawable.walking));
        items.add(new Item("0", R.drawable.jogging));
        items.add(new Item("0", R.drawable.swimming));
        items.add(new Item("0", R.drawable.stair_climbing));

        list.add("Push Ups");
        list.add("Sit Ups");
        list.add("Squats");
        list.add("Leg Lifts");
        list.add("Planks");
        list.add("Jumping Jacks");
        list.add("Pull Ups");
        list.add("Cycling");
        list.add("Walking");
        list.add("Jogging");
        list.add("Swimming");
        list.add("Stair Climbing");
    }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int i)
        {
            return items.get(i);
        }

        @Override
        public long getItemId(int i)
        {
            return items.get(i).drawableId;
        }


        @Override
        public View getView(final int i, View view, ViewGroup viewGroup)
        {
            View v = view;
            ImageView picture;
            EditText et;

            if(v == null)
            {
                v = inflater.inflate(R.layout.gridview_item, viewGroup, false);
                v.setTag(R.id.picture, v.findViewById(R.id.picture));
                v.setTag(R.id.text, v.findViewById(R.id.text));
            }

            picture = (ImageView)v.getTag(R.id.picture);
            et = (EditText)v.getTag(R.id.text);

            Item item = (Item)getItem(i);

            picture.setImageResource(item.drawableId);
            et.setText(item.name);

            picture.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Toast.makeText(getApplicationContext()  , list.get(i)  , Toast.LENGTH_SHORT).show();
                }
            });


            et.setImeOptions(EditorInfo.IME_ACTION_DONE);

            TextView type = (TextView)v.findViewById(R.id.type);

            type.setText(item.type);

            et.setOnEditorActionListener(new OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    boolean handled = false;
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        calculate(v.getText().toString(), i);
                        v.clearFocus();

                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    }

                    return handled;
                }
            });


            EditText cet = (EditText) findViewById(R.id.calories);
            cet.setOnEditorActionListener(new OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    boolean handled = false;
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        updateViews(Double.parseDouble(v.getText().toString()));
                        v.clearFocus();

                        InputMethodManager imm =  (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    }

                    return handled;
                }
            });

            return v;
        }

        public void calculate (String string, int ID){

            System.out.println(ID + "");

            double calories;

            if (ID == 0){
                calories = ((Double.parseDouble(string) * 100) / 350);
            } else if (ID == 1){
                calories = ((Double.parseDouble(string) * 100) / 200);
            } else if (ID == 2){
                calories = (( Double.parseDouble(string) * 100) / 225);
            }else if (ID == 3){
                calories = ((Double.parseDouble(string) * 100) / 25);
            }else if (ID == 4){
                calories = ((Double.parseDouble(string) * 100) / 25);
            }else if (ID == 5){
                calories = ((Double.parseDouble(string) * 100) / 10);
            }else if (ID == 6){
                calories = (Double.parseDouble(string));
            }else if (ID == 7){
                calories = ((Double.parseDouble(string) * 100) / 12);
            }else if (ID == 8){
                calories = ((Double.parseDouble(string) * 100) / 20);
            }else if (ID == 9){
                calories = ((Double.parseDouble(string) * 100) / 12);
            }else if (ID == 10){
                calories = ((Double.parseDouble(string) * 100) / 13);
            }else if (ID == 11){
                calories = ((Double.parseDouble(string) * 100) / 15);
            }else{
                System.out.println("something went wrong");
                calories = 0;
            }

            EditText cet = (EditText) findViewById(R.id.calories);
            cet.setText((Math.round(calories) + ""), TextView.BufferType.EDITABLE);

            updateViews(calories);



        }

        public void updateViews(Double calories){
            items.get(0).name = (Math.round(calories * 350 / 100)) > 0 ? Math.round(calories * 350 / 100) + "" : 1 + "";
            items.get(1).name = (Math.round(calories * 200 / 100)) > 0 ? (Math.round(calories * 200 / 100)) + "" : 1 + "";
            items.get(2).name = (Math.round(calories * 225 / 100)) > 0 ? (Math.round(calories * 225 / 100)) + "" : 1 + "";
            items.get(3).name = (Math.round(calories * 25 / 100)) > 0 ? (Math.round(calories * 25 / 100)) + "" : 1 + "";
            items.get(4).name = (Math.round(calories * 25 / 100)) > 0 ? (Math.round(calories * 25 / 100)) + "" : 1 + "";
            items.get(5).name = (Math.round(calories * 10 / 100)) > 0 ? (Math.round(calories * 10 / 100)) + "" : 1 + "";
            items.get(6).name = (Math.round(calories * 100 / 100)) > 0 ? (Math.round(calories * 100 / 100)) + "" : 1 + "";
            items.get(7).name = (Math.round(calories * 12 / 100)) > 0 ? (Math.round(calories * 12 / 100)) + "" : 1 + "";
            items.get(8).name = (Math.round(calories * 20 / 100)) > 0 ? (Math.round(calories * 20 / 100)) + "" : 1 + "";
            items.get(9).name = (Math.round(calories * 12 / 100)) > 0 ? (Math.round(calories * 12 / 100)) + "" : 1 + "";
            items.get(10).name = (Math.round(calories * 13 / 100)) > 0 ? (Math.round(calories * 13 / 100)) + "" : 1 + "";
            items.get(11).name = (Math.round(calories * 15 / 100)) > 0 ? (Math.round(calories * 15 / 100)) + "" : 1 + "";
        }

        private class Item
        {
            String name;
            int drawableId;
            String type;

            Item(String name, int drawableId)
            {
                this.name = name;
                this.drawableId = drawableId;
                this.type = "minutes";
            }

            Item(String name, int drawableId, String type)
            {
                this.name = name;
                this.drawableId = drawableId;
                this.type = type;
            }

        }

    }

}