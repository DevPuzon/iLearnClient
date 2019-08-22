package com.softwaresolution.ilearnclient.Activity.MainActivityData;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.softwaresolution.ilearnclient.R;

import java.util.List;

public class ListMainActivity extends ArrayAdapter<String> {
    Activity context;
    List<String> list;
    int listview;
    public ListMainActivity(Activity context,List<String> list,int listview) {
        super(context, listview,list);
        this.context = context;
        this.list = list;
        this.listview = listview;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View gridviewitem = inflater.inflate(listview,null,true);
        TextView txt_head = (TextView) gridviewitem.findViewById(R.id.txt_head);
        TextView txt_subhead = (TextView) gridviewitem.findViewById(R.id.txt_subhead);
        txt_head.setText(list.get(position).substring(0,1).toUpperCase());
        txt_subhead.setText(list.get(position).substring(0,1).toUpperCase()
                + list.get(position).substring(1));
        return gridviewitem;
    }
}
