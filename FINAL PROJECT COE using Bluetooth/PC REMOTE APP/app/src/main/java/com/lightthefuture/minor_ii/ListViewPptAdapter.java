package com.lightthefuture.minor_ii;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Admin on 13-05-2016.
 */
public class ListViewPptAdapter extends BaseAdapter {
    private Activity activity;
    private ArrayList<String> strings = new ArrayList<String>();
    private LayoutInflater inflater;

    public ListViewPptAdapter(Activity activity, ArrayList<String> arrayList){
        this.strings = arrayList;
        this.activity = activity;
    }


    @Override
    public int getCount() {
        return strings.size();
    }

    @Override
    public Object getItem(int position) {
        return strings.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater .inflate(R.layout.layout_list_view_adapter, null);
        String s = strings.get(position);
        TextView textView = (TextView) convertView.findViewById(R.id.textView13);
        textView.setText(s);
        return convertView;
    }
}
