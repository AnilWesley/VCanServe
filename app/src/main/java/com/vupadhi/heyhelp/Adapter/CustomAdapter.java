package com.vupadhi.heyhelp.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.vupadhi.heyhelp.R;

import java.util.ArrayList;

public class CustomAdapter  extends BaseAdapter implements SpinnerAdapter {
 
    ArrayList<String> loclist;
    Context context;
    String[] colors = {"#13edea","#e20ecd","#15ea0d"};
    String[] colorsback = {"#FF000000","#FFF5F1EC","#ea950d"};
 
    public CustomAdapter(Context context, ArrayList<String> loclist) {
        this.loclist = loclist;
        this.context = context;
    }
 
    @Override
    public int getCount() {
        return loclist.size();
    }
 
    @Override
    public Object getItem(int position) {
        return loclist.get(position);
    }
 
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view =  View.inflate(context, R.layout.spinner_item, null);
        TextView textView = (TextView) view.findViewById(R.id.text1);
        textView.setText(loclist.get(position));
        return textView;
    }


    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
 
        View view;
        view =  View.inflate(context, R.layout.spinner_item, null);
        final TextView textView = (TextView) view.findViewById(R.id.text1);
        textView.setText(loclist.get(position));
 
//        textView.setTextColor(Color.parseColor(colors[position]));
//        textView.setBackgroundColor(Color.parseColor(colorsback[position]));
 
 
        return view;
    }
}