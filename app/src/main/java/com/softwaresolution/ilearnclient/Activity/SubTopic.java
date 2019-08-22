package com.softwaresolution.ilearnclient.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.shashank.sony.fancytoastlib.FancyToast;
import com.softwaresolution.ilearnclient.Activity.MainActivityData.ListSubtopic;
import com.softwaresolution.ilearnclient.Auth.GetAuth;
import com.softwaresolution.ilearnclient.JsonData.AllData;
import com.softwaresolution.ilearnclient.R;
import com.softwaresolution.ilearnclient.SendMessage;
public class SubTopic extends AppCompatActivity {
    private ListView listView;
    private ListSubtopic subtopicAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subtopic);
//        context = SubTopic.this;
        listView = (ListView) findViewById(R.id.listsubtopic);
        Intent intent = getIntent();
//        final String message = intent.getStringExtra("my package");
        final SendMessage sendMessage = new SendMessage();
        sendMessage.isNextActivity = true;
//        sendMessage.SendMessage(this,"subtopic/"+message);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (isPulStart){
                    InitAdapter();
                    sendMessage.SendMessage(SubTopic.this,"preview/"
                                    +AllData.dataSubtopic.quizid.get(position)
                            ,PreviewResult.class,"");
                    return;
                }
                if (!AllData.dataSubtopic.security.get(position).equals("Lock")){
                    InitAdapter();
                    sendMessage.SendMessage(SubTopic.this,"activityquiz/"
                                    +AllData.dataSubtopic.quizid.get(position)
                                    +"/"+ GetAuth.Studentid
                            ,Quiz.class,String.valueOf(isExam(position)));
                }else {
                    FancyToast.makeText(getApplicationContext(),
                            "This activity is currenlty locked",
                            FancyToast.LENGTH_LONG,FancyToast.INFO,false).show();
                }
            }
        });
        InitAdapter();
    }
    private void InitAdapter(){
        subtopicAdapter = new ListSubtopic(
                SubTopic.this,
                R.layout.list_activity_subtopic);
        listView.setAdapter(subtopicAdapter);
        isPulStart = false;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        if (AllData.dataSubtopic.quizid.size() > 0){
            inflater.inflate(R.menu.student_view, menu);
        }
        return true;
    }

    private boolean isPulStart = false;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_view){
            if (isPulStart){
                InitAdapter();
            }else{
                subtopicAdapter.scaleDown.start();
                FancyToast.makeText(getApplicationContext(),
                        "Select item to show quiz result.",
                        FancyToast.LENGTH_LONG,FancyToast.INFO,false).show();
            }
            isPulStart = !isPulStart;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean isExam(int i){
        boolean isexam = false;
        if (AllData.dataSubtopic.timeoption.get(i).equals("Per exam")){
            isexam = true;
        }else{
            isexam = false;
        }
        return isexam;
    }
}
