package com.softwaresolution.ilearnclient.Note.NoteData;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.softwaresolution.ilearnclient.R;

import java.util.List;

public class ListMainNote extends ArrayAdapter<String> {
    Activity context;
    List<String> title;
    List<String> text;
    List<String> date;

    public ListMainNote(Activity context, List<String> title,List<String> text
                        ,List<String> date) {
        super(context, R.layout.list_main_note,title);
        this.context = context;
        this.title = title;
        this.text = text;
        this.date = date;
    }

    @NonNull
    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View gridview = inflater.inflate(R.layout.list_main_note,null,true);
        TextView txt_title = (TextView) gridview.findViewById(R.id.title);
        TextView txt_text = (TextView) gridview.findViewById(R.id.text);
        txt_text.setMaxLines(1);
        TextView txt_date = (TextView) gridview.findViewById(R.id.date);
        txt_title.setText(title.get(position));
        txt_text.setText(text.get(position));
        txt_date.setText(date.get(position));
        return gridview;
    }
}
