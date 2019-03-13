package com.softwaresolution.ilearnclient;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.softwaresolution.ilearnclient.AccountSettings._AccountSettings;
import com.softwaresolution.ilearnclient.Activity._Activity;
import com.softwaresolution.ilearnclient.Leaderboard._Leaderboard;
import com.softwaresolution.ilearnclient.Note._Note;
import com.softwaresolution.ilearnclient.Status._Status;

public class MainBottom extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_activity:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new _Activity()).commit();
                    return true;
                case R.id.nav_status:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new _Status()).commit();
                    return true;
                case R.id.nav_leaderboard:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new _Leaderboard()).commit();
                    return true;
                case R.id.nav_note:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new _Note()).commit();
                    return true;
                case R.id.nav_account_settings:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new _AccountSettings()).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_bottom);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new _Activity()).commit();
            navigation.setSelectedItemId(R.id.nav_activity);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main2, menu);
        SocketSignal.sendMessage("subject");
        menu.add("mema");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(getApplicationContext(),item.getTitle(),Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }
}
