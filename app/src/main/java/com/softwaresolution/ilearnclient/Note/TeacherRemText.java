package com.softwaresolution.ilearnclient.Note;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.softwaresolution.ilearnclient.JsonData.AllData;
import com.softwaresolution.ilearnclient.R;

public class TeacherRemText extends AppCompatActivity {
    private TextView text,title,date;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_main_note);
        text = (TextView) findViewById(R.id.text);
        title = (TextView) findViewById(R.id.title);
        date = (TextView) findViewById(R.id.date);
        int position = _Note.POSITION;
        text.setText(AllData.dataTeacherRem.text.get(position));
        title.setText(AllData.dataTeacherRem.title.get(position));
        date.setText(AllData.dataTeacherRem.date.get(position));
    }
}
