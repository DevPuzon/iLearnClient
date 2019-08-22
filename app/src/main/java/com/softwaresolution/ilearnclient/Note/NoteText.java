package com.softwaresolution.ilearnclient.Note;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.softwaresolution.ilearnclient.Auth.GetAuth;
import com.softwaresolution.ilearnclient.JsonData.AllData;
import com.softwaresolution.ilearnclient.LoginForm;
import com.softwaresolution.ilearnclient.MainBottom;
import com.softwaresolution.ilearnclient.R;
import com.softwaresolution.ilearnclient.SendMessage;

import java.util.UUID;

public class NoteText extends AppCompatActivity {
    TextView txt_title;
    TextView txt_text;
    int position = _Note.POSITION;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_text);
        txt_title = (TextView) findViewById(R.id.title);
        txt_text = (TextView) findViewById(R.id.text);
        if(!_Note.ISADD){
            txt_title.setText(AllData.dataNote.title.get(position));
            txt_text.setText(AllData.dataNote.text.get(position));
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }else if(id == R.id.action_save){
            if(!TextUtils.isEmpty(txt_title.getText().toString()) &&
               !TextUtils.isEmpty(txt_text.getText().toString())){
                if (_Note.ISADD){
                    _save("false/",
                            UUID.randomUUID().toString().substring(0,6)
                            ,txt_title.getText().toString()
                            ,txt_text.getText().toString(), GetAuth.Studentid);
                }else{
                    _save("true",AllData.dataNote.noteid.get(position)
                            ,txt_title.getText().toString()
                            ,txt_text.getText().toString(),"");
                }
            }
            return true;
        }else if(id == R.id.action_delete){
            if (!_Note.ISADD){
                _delete(AllData.dataNote.noteid.get(position),NoteText.this);
                onBackPressed();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.notemenu, menu);
        return true;
    }

    private void _save(String isadd,String noteid,String title,String text,String authid){
        SendMessage sendMessage = new SendMessage();
        sendMessage.SendMessage(NoteText.this,"savenote/"+isadd+
                 noteid+"/"+title+"/"+text+"/"+authid,null,"");
    }
    public static void _delete(String noteid, Context context){
        SendMessage sendMessage = new SendMessage();
        sendMessage.SendMessage(context,"deletenote/"+noteid,
                null,"");
    }
    private void _retrieveData(){
        SendMessage sendMessage = new SendMessage();
        sendMessage.fragment = new _Note();
        sendMessage.isFragment = true;
        sendMessage.SendMessage(MainBottom.context,"note/"+
                        GetAuth.Studentid,
                null
                ,"");
        _Note._retrieve();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        _retrieveData();
    }
}
