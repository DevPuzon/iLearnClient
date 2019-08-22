package com.softwaresolution.ilearnclient;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.softwaresolution.ilearnclient.AccountSettings._AccountSettings;
import com.softwaresolution.ilearnclient.Activity._Activity;
import com.softwaresolution.ilearnclient.Auth.GetAuth;
import com.softwaresolution.ilearnclient.JsonData.AllData;
import com.softwaresolution.ilearnclient.Leaderboard._Leaderboard;
import com.softwaresolution.ilearnclient.Note._Note;
import com.softwaresolution.ilearnclient.Status._Status;

public class MainBottom extends AppCompatActivity {
    public static Context context;
    AllData allData;
    private static BottomNavigationView navigation;
    Gson gson = new Gson();
    private static SendMessage sendMessage =new SendMessage();
    enum getNavSelect{
        nav_activity,
        nav_status,
        nav_leaderboard,
        nav_note,
        nav_account,
    }
    private static getNavSelect navSelect = getNavSelect.nav_activity;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_activity:
                    sendMessage.fragment = new _Activity();
                    sendMessage.isFragment = true;
                    sendMessage.SendMessage(MainBottom.this,"maintopic/"+
                                    GetAuth.Subject,
                            null
                            ,"");
                    navSelect = getNavSelect.nav_activity;
                    return true;
                case R.id.nav_status:
                    sendMessage.fragment = new _Status();
                    sendMessage.isFragment = true;
                    sendMessage.SendMessage(MainBottom.this,"status/"+
                                    GetAuth.Subject
                                    +"/"+GetAuth.Studentid,
                            null
                            ,"");
                    navSelect = getNavSelect.nav_status;
                    return true;
                case R.id.nav_leaderboard:
                    sendMessage.fragment = new _Leaderboard();
                    sendMessage.isFragment = true;
                    sendMessage.SendMessage(MainBottom.this,"leaderboard/"+
                                    GetAuth.Subject,
                            null
                            ,"");
                    navSelect = getNavSelect.nav_leaderboard;
                    return true;
                case R.id.nav_note:
                    sendMessage.fragment = new _Note();
                    sendMessage.isFragment = true;
                    sendMessage.SendMessage(MainBottom.this,"note/"+
                                    GetAuth.Studentid,
                            null
                            ,"");
                    navSelect = getNavSelect.nav_note;
                    return true;
                case R.id.nav_account_settings:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new _AccountSettings()).commit();
                    navSelect = getNavSelect.nav_account;
                    return true;
            }
            return false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_bottom);
        context = MainBottom.this;
        GetAuth.Subject = allData.dataSubject.subject.get(0);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new _Activity()).commit();
            navigation.setSelectedItemId(R.id.nav_activity);
        }
        msnackbar = findViewById(R.id.snackbarPosition);
    }
    private static View msnackbar;
    public static Handler showMessage = new Handler(){
        @Override
        public boolean sendMessageAtTime(Message msg, long uptimeMillis) {
            final Snackbar snackbar = Snackbar.make(msnackbar, msg.obj.toString(), 8000);
            snackbar.setActionTextColor(Color.WHITE);
            View snackbarView = snackbar.getView();
            snackbarView.setBackgroundColor(Color.parseColor("#CC00CC"));
            TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setMaxLines(20);
            snackbar.show();
            snackbar.setAction("Reply", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((FragmentActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new _Activity()).commit();
                    navSelect = getNavSelect.nav_account;
                    navigation.setSelectedItemId(R.id.nav_account_settings);
                }
            });
            return super.sendMessageAtTime(msg, uptimeMillis);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.main2, menu);
        for (int i =0 ; i < allData.dataSubject.subject.size() ; i++){
            menu.add(allData.dataSubject.subject.get(i));
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        GetAuth.Subject = String.valueOf(item.getTitle());
        switch (navSelect){
            case nav_activity:
                sendMessage.isFragment = true;
                sendMessage.fragment = new _Activity();
                sendMessage.SendMessage(MainBottom.this,"maintopic/"+
                                GetAuth.Subject,
                        null
                        ,"");
                break;
            case nav_status:
                sendMessage.fragment = new _Status();
                sendMessage.isFragment = true;
                sendMessage.SendMessage(MainBottom.this,"status/"+
                                GetAuth.Subject
                                +"/"+GetAuth.Studentid,
                        null
                        ,"");
                break;
            case nav_leaderboard:
                sendMessage.fragment = new _Leaderboard();
                sendMessage.isFragment = true;
                sendMessage.SendMessage(MainBottom.this,"leaderboard/"+
                                GetAuth.Subject,
                        null
                        ,"");
                break;
            case nav_note:
                sendMessage.fragment = new _Note();
                sendMessage.isFragment = true;
                sendMessage.SendMessage(MainBottom.this,"note/"+
                                GetAuth.Studentid,
                        null
                        ,"");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
