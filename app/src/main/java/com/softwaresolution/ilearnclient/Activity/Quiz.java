package com.softwaresolution.ilearnclient.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.softwaresolution.ilearnclient.Auth.GetAuth;
import com.softwaresolution.ilearnclient.JsonData.AllData;
import com.softwaresolution.ilearnclient.JsonData.DataQuiz;
import com.softwaresolution.ilearnclient.LoginForm;
import com.softwaresolution.ilearnclient.MainBottom;
import com.softwaresolution.ilearnclient.R;
import com.softwaresolution.ilearnclient.SendMessage;
import com.softwaresolution.ilearnclient.SocketSignal;


public class Quiz extends AppCompatActivity implements View.OnClickListener {
    Button bttn_a,bttn_b,bttn_c,bttn_d,bttn_ready,bttn_finish;
    TextView txt_question,txt_time,txt_took,txt_score,txt_score1;
    public int score = 0;
    int noQuestion =0;
    RelativeLayout layout,layout2,layout3;
    AllData allData;
    DataQuiz dataQuiz;
    boolean isExam;
    SendMessage sendMessage;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Intent intent = getIntent();
        sendMessage = new SendMessage();
        isExam =Boolean.valueOf(intent.getStringExtra("my package"));
        txt_question = (TextView) findViewById(R.id.txt_question);
        txt_time = (TextView) findViewById(R.id.txt_timer);
        txt_score = (TextView) findViewById(R.id.txt_score);
        txt_score1 = (TextView) findViewById(R.id.txt_score1);
        txt_took = (TextView) findViewById(R.id.txt_took);
        layout = (RelativeLayout) findViewById(R.id.layout);
        layout2 = (RelativeLayout) findViewById(R.id.layout2);
        layout3 = (RelativeLayout) findViewById(R.id.layout3);
        layout.setVisibility(View.VISIBLE);
        layout2.setVisibility(View.GONE);
        layout3.setVisibility(View.GONE);
        bttn_a = (Button) findViewById(R.id.bttn_a);
        bttn_b = (Button) findViewById(R.id.bttn_b);
        bttn_c = (Button) findViewById(R.id.bttn_c);
        bttn_d = (Button) findViewById(R.id.bttn_d);
        bttn_ready = (Button) findViewById(R.id.bttn_ready);
        bttn_finish = (Button) findViewById(R.id.bttn_finish);
        bttn_ready.setOnClickListener(this);
        bttn_finish.setOnClickListener(this);
        bttn_a.setOnClickListener(this);
        bttn_b.setOnClickListener(this);
        bttn_c.setOnClickListener(this);
        bttn_d.setOnClickListener(this);
        dataQuiz = new DataQuiz();
        dataQuiz =allData.dataQuiz;
        if (dataQuiz.istake.equals("true")){
            txt_took.setVisibility(View.VISIBLE);
            bttn_ready.setVisibility(View.GONE);
        }else{
            txt_took.setVisibility(View.GONE);
            bttn_ready.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == bttn_a){
            sendAnswer("a");
        }if (v == bttn_b){
            sendAnswer("b");
        }if (v == bttn_c){
            sendAnswer("c");
        }if (v == bttn_d){
            sendAnswer("d");
        }if (v == bttn_ready){
            _bttn_ready_click();
        }if (v == bttn_finish){
            _bttn_finish_click();
        }
    }

    private void _bttn_finish_click() {
        Quiz.this.finish();
    }
    CountDownTimer cd1;
    private void _bttn_ready_click() {
        //set Istake
        SocketSignal.sendMessage( "insertistake/"
                +dataQuiz.quizid+"/"
                + GetAuth.Studentid +"/"+GetAuth.Username+"/"+ GetAuth.Subject);
        //set Istake

        layout.setVisibility(View.GONE);
        layout3.setVisibility(View.VISIBLE);
        final String time = dataQuiz.timer + "000";
        cd1 = new CountDownTimer(Integer.parseInt(time), 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int min = (int) (millisUntilFinished / 60000);
                int sec = (int) (millisUntilFinished % 60000 / 1000);
                String timeleft;
                timeleft = ""+ min;
                timeleft += ":";
                if(sec < 10) timeleft +="0";
                timeleft += sec;
                txt_time.setText(timeleft);
            }

            @Override
            public void onFinish() {
                layout3.setVisibility(View.GONE);
                layout2.setVisibility(View.VISIBLE);
                txt_score1.setText(String.valueOf(score));
            }
        }.start();
        changeTxt();
    }

    private void changeTxt(){
        txt_question.setText(dataQuiz.question.get(noQuestion));
        bttn_a.setText(dataQuiz.a.get(noQuestion));
        bttn_b.setText(dataQuiz.b.get(noQuestion));
        bttn_c.setText(dataQuiz.c.get(noQuestion));
        bttn_d.setText(dataQuiz.d.get(noQuestion));
    }
    private void sendAnswer(String answer){

        String correct = dataQuiz.answer.get(noQuestion);
        if (correct.equals(answer)){
            score = score + 1;
        }
        txt_score.setText(String.valueOf(score));
        SocketSignal.sendMessage("insertscore/"
                +dataQuiz.quizid
                +"/"+String.valueOf(score)+"/"+answer);
        if (!isExam){
            cd1.start();
        }

        noQuestion++;
        if(dataQuiz.question.size() == noQuestion){
            cd1.cancel();
            layout3.setVisibility(View.GONE);
            layout2.setVisibility(View.VISIBLE);
            txt_score1.setText(String.valueOf(score));
            return;
        }
        changeTxt();
    }
}
