package com.softwaresolution.ilearnclient;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.shashank.sony.fancytoastlib.FancyToast;
import com.softwaresolution.ilearnclient.JsonData.AllData;
import com.softwaresolution.ilearnclient.JsonData.DataMainTopic;
import com.softwaresolution.ilearnclient.JsonData.DataStudent;
import com.softwaresolution.ilearnclient.JsonData.DataSubject;
import com.softwaresolution.ilearnclient.Auth.GetAuth;

public class LoginForm extends AppCompatActivity {
    Button bttn_login,bttn_check;
    TextView txt_signup;
    EditText txt_ip,txt_name,txt_pass;
    SocketSignal socketSignal;
    private  boolean isCheck;
    private static final int  PERMISSION_REQUEST_CODE = 1;
    int i = 0;
    ProgressDialog loading;
    boolean threadbool = true;
    Gson gson = new Gson();
    AllData alldata;
    Thread thread = new Thread(){
        @Override
        public void run() {
            try {
                while (threadbool){
                    for(int i = 0 ; i < 20; i++){
                        Thread.sleep(100);
                        SocketSignal.sendMessage(String.valueOf(i));
                    }
                    threadbool = false;
                    this.interrupt();
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
        if (checkPermission()) {
            Log.e("permission", "Permission already granted.");
        } else {
            requestPermission();
        }
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

        isCheck = false;
        bttn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                IpAddress = txt_ip.getText().toString();
                GetAuth.Ipaddress ="192.168.0.106";
                socketSignal = new SocketSignal(GetAuth.Ipaddress
                        , 200);
                socketSignal.execute();
                loading.show();
                thread.start();
                isCheck = true;
            }
        });
        bttn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isCheck){
                    FancyToast.makeText(LoginForm.this,
                            "Please verify the Ip address first."
                            ,FancyToast.LENGTH_LONG,FancyToast.INFO,false).show();
                    return;
                }
                final String username = txt_name.getText().toString();
                GetAuth.Username = username;
                GetAuth.Username = "q";
                final String pass = txt_pass.getText().toString();
//                if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(pass)){
                if (true){
                    SocketSignal.addListener(socketSignal);
                    threadbool = true;
//                                    SocketSignal.sendMessage("login_form/"+ STUDENTNAME +"/pass");
//                    SocketSignal.sendMessage("login_form/"+ username +"/"+pass);
//                    SendMessage sendMessage = new SendMessage();
//                    sendMessage.SendMessage(LoginForm.this,"login_form/"+ "name" +"/"+"pass"
//                    ,null,"");

                    Thread thread1 = new Thread(){
                        @Override
                        public void run() {
                            try {
                                while (threadbool){
                                    SocketSignal.sendMessage("login_form/"+ "q" +"/"+"q");
                                    Thread.sleep(1000);

                                    if(!SocketSignal.getSignal.equals("failed")){
                                        try{
                                            alldata.dataStudent = gson.fromJson(SocketSignal.getSignal, DataStudent.class);
                                            if (alldata.dataStudent != null){
                                                GetAuth.Studentid = alldata.dataStudent.authid;
                                            }
                                            threadbool = false;
                                            SocketSignal.sendMessage("subject");
                                            SocketSignal.getSignal = "";
                                            Thread.sleep(1000);
                                            alldata.dataSubject =gson.fromJson(SocketSignal.getSignal,DataSubject.class);
                                            SocketSignal.sendMessage("maintopic/"+alldata.dataSubject.subject.get(0));
                                            SocketSignal.getSignal = "";
                                            Thread.sleep(1000);
                                            alldata.dataMainTopic = gson.fromJson(SocketSignal.getSignal,DataMainTopic.class);
                                            if (alldata.dataMainTopic == null){
                                                threadbool = true;
                                            }if (alldata.dataSubject == null){
                                                threadbool = true;
                                            }Thread.sleep(1000);
                                        }catch (Exception ex){
                                            threadbool = true;
                                        }
                                    }else {
                                        FancyToast.makeText(LoginForm.this,"Failed to login"
                                                , FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
                                    }
                                }
                                this.interrupt();
                                loading.dismiss();
                                Intent intent = new Intent(LoginForm.this,MainBottom.class);
                                startActivity(intent);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    loading.show();
                    thread1.start();
                }else{
                    FancyToast.makeText(LoginForm.this,"Please fill all the information"
                        ,FancyToast.LENGTH_LONG,FancyToast.INFO,false).show();
                }
            }
        });
        txt_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isCheck){
                    FancyToast.makeText(LoginForm.this,
                            "Please verify the Ip address first."
                            ,FancyToast.LENGTH_LONG,FancyToast.INFO,false).show();
                    return;
                }
                Intent intent = new Intent(LoginForm.this,SignUp.class);
                startActivity(intent);
            }
        });
    }
    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(LoginForm.this, Manifest.permission.INTERNET);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, PERMISSION_REQUEST_CODE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(LoginForm.this,
                            "Permission accepted", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(LoginForm.this,
                            "Permission denied", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}
