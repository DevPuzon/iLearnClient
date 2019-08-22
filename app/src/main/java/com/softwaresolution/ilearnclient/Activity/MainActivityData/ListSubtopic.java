package com.softwaresolution.ilearnclient.Activity.MainActivityData;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.softwaresolution.ilearnclient.JsonData.AllData;
import com.softwaresolution.ilearnclient.R;

import java.util.List;

public class ListSubtopic  extends ArrayAdapter<String> {
    Activity context;
    int listview;
    List<String> listSubtopic;
    List<String> listSecurity;
    List<String> listQuizno;
    public ListSubtopic(Activity context, int listview) {
        super(context, listview, AllData.dataSubtopic.subtopic);
        this.context = context;
        this.listview = listview;
        listSubtopic = AllData.dataSubtopic.subtopic;
        listSecurity = AllData.dataSubtopic.security;
        listQuizno = AllData.dataSubtopic.quizno;
    }
    public ObjectAnimator scaleDown;
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View gridviewitem = inflater.inflate(listview,null,true);

        scaleDown = ObjectAnimator.ofPropertyValuesHolder(
                gridviewitem,
                PropertyValuesHolder.ofFloat("scaleX", 0.9f),
                PropertyValuesHolder.ofFloat("scaleY", 0.9f));
        scaleDown.setDuration(310);
        scaleDown.setRepeatCount(ObjectAnimator.INFINITE);
        scaleDown.setRepeatMode(ObjectAnimator.REVERSE);

        TextView txt_head = (TextView) gridviewitem.findViewById(R.id.txt_head);
        TextView txt_subhead = (TextView) gridviewitem.findViewById(R.id.txt_subhead);
        TextView txt_quizno = (TextView) gridviewitem.findViewById(R.id.txt_quizno);

        txt_quizno.setText("Quiz no. "+listQuizno.get(position));
        txt_head.setText(listSubtopic.get(position).substring(0,1).toUpperCase());
        txt_subhead.setText(listSubtopic.get(position).substring(0,1).toUpperCase()
                + listSubtopic.get(position).substring(1));
        ImageView img_security = (ImageView) gridviewitem.findViewById(R.id.img_security);
        if (listSecurity.get(position).equals("Lock")){
            img_security.setImageResource(R.drawable.ic_lock);
        }else{
            img_security.setImageResource(R.drawable.ic_lock_open);
        }
        return gridviewitem;
    }
}
