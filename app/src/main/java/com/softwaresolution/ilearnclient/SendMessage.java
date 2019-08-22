package com.softwaresolution.ilearnclient;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Debug;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shashank.sony.fancytoastlib.FancyToast;
import com.softwaresolution.ilearnclient.Activity.SubTopic;
import com.softwaresolution.ilearnclient.Auth.GetAuth;
import com.softwaresolution.ilearnclient.JsonData.AllData;
import com.softwaresolution.ilearnclient.JsonData.DataLeaderboard;
import com.softwaresolution.ilearnclient.JsonData.DataMainTopic;
import com.softwaresolution.ilearnclient.JsonData.DataNote;
import com.softwaresolution.ilearnclient.JsonData.DataPreviewResult;
import com.softwaresolution.ilearnclient.JsonData.DataQuiz;
import com.softwaresolution.ilearnclient.JsonData.DataStatus;
import com.softwaresolution.ilearnclient.JsonData.DataStudent;
import com.softwaresolution.ilearnclient.JsonData.DataSubject;
import com.softwaresolution.ilearnclient.JsonData.DataSubtopic;

public class SendMessage {
    ProgressDialog loading;
    String message1; 
    Gson gson = new GsonBuilder()
                .setLenient()
                .create();
    boolean threadbool = false;
    public boolean isNextActivity;
    public boolean isFragment;
    public Fragment fragment;
    public String  getMessage;
    public void SendMessage(final Context context, final String message, final Class clas, final String intentMessage){
        final String[] auth = message.split("/");
//        Gson a = new GsonBuilder()
//                .setLenient()
//                .create();
        loading =  new ProgressDialog(context);
        loading.setTitle("Loading");
        loading.setMessage("Please wait");
        loading.setCancelable(false);
        threadbool = true;
        final Handler handlerSignup = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Log.d("Puta11",msg.obj.toString());
                if(msg.obj.toString().equals("Success")){
                    FancyToast.makeText(context,"Success!"
                            ,FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show();
                }else{
                    FancyToast.makeText(context,"Please create a unique name"
                            , FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
                }
            }
        };
        final Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    while (threadbool){
                        threadbool = false;
                        SocketSignal.sendMessage(message);
                        Log.d("SmSplit",auth[0]);
                        SocketSignal.getSignal = "";
                        if (auth[0].equals("login_form")){
                            Thread.sleep(100);
                            getMessage = SocketSignal.getSignal;
                            AllData.dataStudent = gson.fromJson(SocketSignal.getSignal, DataStudent.class);
                            if (AllData.dataStudent != null){
                                GetAuth.Studentid = AllData.dataStudent.authid;
                            }
                        }
                        SocketSignal.getSignal = "";
                        if(auth[0].equals("subject")){
                            Thread.sleep(100);
                            AllData.dataSubject =gson.fromJson(SocketSignal.getSignal, DataSubject.class);
                            if (AllData.dataSubject==null){
                                threadbool = true;
                            }
                        }
                        SocketSignal.getSignal = "";
                        if(auth[0].equals("maintopic")){
                            Thread.sleep(100);
                            AllData.dataMainTopic = gson.fromJson(SocketSignal.getSignal, DataMainTopic.class);
                            if (AllData.dataMainTopic==null){
                                threadbool = true;
                            }
                        }
                        SocketSignal.getSignal = "";
                        if(auth[0].equals("subtopic")){
                            Thread.sleep(100);
                            AllData.dataSubtopic = gson.fromJson(SocketSignal.getSignal, DataSubtopic.class);
                            if (AllData.dataSubtopic==null){
                                threadbool = true;
                            }
                        }
                        SocketSignal.getSignal = "";
                        if (auth[0].equals("activityquiz")){
                            Thread.sleep(2000);
                            AllData.dataQuiz = gson.fromJson(SocketSignal.getSignal, DataQuiz.class);
                            if (AllData.dataQuiz==null){
                                threadbool = true;
                            }
                        }
                        SocketSignal.getSignal = "";
                        if(auth[0].equals("preview")){
                            Thread.sleep(100);
                            AllData.dataPreviewResult = gson.fromJson(SocketSignal.getSignal, DataPreviewResult.class);
                            if (AllData.dataPreviewResult == null){
                                threadbool = true;
                            }
                        }
                        if (auth[0].equals("status")){
                            Thread.sleep(1000);
                            AllData.dataStatus = gson.fromJson(SocketSignal.getSignal, DataStatus.class);
                            if (AllData.dataStatus==null){
                                threadbool = true;
                            }
                        }
                        if (auth[0].equals("leaderboard")){
                            Thread.sleep(100);
                            AllData.dataLeaderboard = gson.fromJson(SocketSignal.getSignal, DataLeaderboard.class);
                            if (AllData.dataLeaderboard==null){
                                threadbool = true;
                            }
                        }
                        if (auth[0].equals("note")){
                            Thread.sleep(100);
                            AllData.dataNote = gson.fromJson(SocketSignal.getSignal, DataNote.class);
                            if (AllData.dataNote==null){
                                threadbool = true;
                            }
                        }
                        if (auth[0].equals("signup")){
                            Thread.sleep(100);
                            getMessage = SocketSignal.getSignal;
                            if (SocketSignal.getSignal==null){
                                threadbool = true;
                            }else{
                                handlerSignup.obtainMessage(0, 0, -1,
                                        getMessage).sendToTarget();
                            }
                        }
                        Thread.sleep(1000);
                    }
                    Thread.sleep(1000);
                    this.interrupt();
                    loading.dismiss();
                    if(isNextActivity){
                        Intent intent = new Intent(context, clas);
                        if (intentMessage != ""){
                            intent.putExtra("my package",intentMessage);
                        }
                        context.startActivity(intent);
                    }
                    if (isFragment){
                        ((FragmentActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                fragment).commit();
                        isFragment = false;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        loading.show();
        thread.start();
    }
}
