package com.softwaresolution.ilearnclient.Note.NoteData;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
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

public class ListTeacherRem  extends ArrayAdapter<String> {
    Activity context;
    int listview;
    List<String> title;
    List<String> text;
    List<String> date;
    public ListTeacherRem(Activity context, int listview) {
        super(context, listview, AllData.dataTeacherRem.noteid);
        this.context = context;
        this.listview = listview;
        title = AllData.dataTeacherRem.title;
        text = AllData.dataTeacherRem.text;
        date = AllData.dataTeacherRem.date;
    }
    public ObjectAnimator scaleDown;
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View v = inflater.inflate(listview,null,true);
        TextView txt_title = (TextView) v.findViewById(R.id.title);
        TextView txt_text = (TextView) v.findViewById(R.id.text);
        txt_text.setMaxLines(1);
        TextView txt_date = (TextView) v.findViewById(R.id.date);
        txt_title.setText(title.get(position));
        txt_text.setText(text.get(position));
        txt_date.setText(date.get(position));
        return v;
    }
}
