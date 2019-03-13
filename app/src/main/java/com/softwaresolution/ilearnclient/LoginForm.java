package com.softwaresolution.ilearnclient;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.softwaresolution.ilearnclient.Bin.Client;
import com.softwaresolution.ilearnclient.Bin.MainActivity;
import com.softwaresolution.ilearnclient.Bin.Server;

import java.util.ArrayList;
import java.util.List;

public class LoginForm extends AppCompatActivity {
    Button bttn_login,bttn_check;
    TextView txt_signup;
    EditText txt_ip,txt_name,txt_pass;
    SocketSignal socketSignal;

    int i = 0;
    ProgressDialog loading;
    boolean threadbool = true;
    Thread thread = new Thread(){
        @Override
        public void run() {
            try {
                while (threadbool){
                    for(int i = 0 ; i < 20; i++){
                        Thread.sleep(100);
                        socketSignal.sendMessage(String.valueOf(i));
                    }
                    threadbool = false;
                    loading.dismiss();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_form);
        txt_signup = (TextView) findViewById(R.id.txt_signup);
        txt_ip = (EditText) findViewById(R.id.txt_ip);
        txt_name = (EditText) findViewById(R.id.txt_name);
        txt_pass = (EditText) findViewById(R.id.txt_pass);
        bttn_login = (Button) findViewById(R.id.bttn_login);
        bttn_check = (Button) findViewById(R.id.bttn_check);

        loading =  new ProgressDialog(this);
        loading.setTitle("Loading");
        loading.setMessage("Please wait");
        loading.setCancelable(false);
        final List<String> list = new ArrayList<String>();
        bttn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                socketSignal = new SocketSignal("192.168.0.105"
                        , 100);
                socketSignal.execute();
                loading.show();
                thread.start();
            }
        });

        final Handler handler = new Handler();
        bttn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                socketSignal.addListener(socketSignal);
                if (socketSignal.listener != null)
                    socketSignal.sendMessage("login_form/name/pass");
                threadbool = true;
                Thread thread = new Thread(){
                    @Override
                    public void run() {
                        try {
                            while (threadbool){
                                for(int i = 0 ; i < 20; i++){
                                    Thread.sleep(100);
                                    Log.d("Auth1",socketSignal.getSignal);
                                }
                                threadbool = false;
                                loading.dismiss();
                                if(socketSignal.getSignal.equals("success")){
                                    Intent intent = new Intent(LoginForm.this,MainBottom.class);
                                    startActivity(intent);
                                }else {
                                    Toast.makeText(LoginForm.this,"Failed to login",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };
                loading.show();
                thread.start();
            }
        });
        txt_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginForm.this,SignUp.class);
                startActivity(intent);
            }
        });
    }


//    @Override
//    protected void onDestroy() {
//        // TODO Auto-generated method stub
//        try {
//            if (socketSignal.listener != null)
//                socketSignal.listener.listener(Server.getIpAddress());
//            socketSignal.socket.close();
//        } catch (Exception e) {
//            // TODO: handle exception
//        }
//        super.onDestroy();
//    }
//
//    @Override
//    protected void onStop() {
//        // TODO Auto-generated method stub
//        try {
//            if (socketSignal.listener != null)
//                socketSignal.listener.listener(Server.getIpAddress());
//            socketSignal.socket.close();
//        } catch (Exception e) {
//            // TODO: handle exception
//        }
//        super.onStop();
//    }

}
