package com.softwaresolution.ilearnclient.Note;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.softwaresolution.ilearnclient.Auth.GetAuth;
import com.softwaresolution.ilearnclient.JsonData.AllData;
import com.softwaresolution.ilearnclient.JsonData.DataTeacherRem;
import com.softwaresolution.ilearnclient.Note.NoteData.ListMainNote;
import com.softwaresolution.ilearnclient.Note.NoteData.ListTeacherRem;
import com.softwaresolution.ilearnclient.R;
import com.softwaresolution.ilearnclient.SendMessage;
import com.softwaresolution.ilearnclient.SocketSignal;

import java.util.ArrayList;
import java.util.List;

public class _Note extends Fragment {
    private static final String TAG = "_Note";
    View v;
    static List<String> title;
    static List<String> text;
    static List<String> date;
    FloatingActionButton fab;
    public static Activity context;
    static ListView listView;
    public static int POSITION;
    public static boolean ISADD;
    private Button btn_teacherRem;
    private boolean isTeacherRem = false;
    private ProgressDialog loading;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.menu_note,container,false);
        listView = (ListView) v.findViewById(R.id.listview);
        fab = (FloatingActionButton) v.findViewById(R.id.fab);
        SendMessage sendMessage = new SendMessage();
        btn_teacherRem = (Button) v.findViewById(R.id.btn_teacherRem);

        loading =  new ProgressDialog(getContext());
        loading.setTitle("Loading");
        loading.setMessage("Please wait");
        loading.setCancelable(false);
        btn_teacherRem.setTextColor(getResources().getColor(R.color.colorPrimary));
        btn_teacherRem.setBackground(null);

        btn_teacherRem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTeacherRem){
                    _retrieve();
                    btn_teacherRem.setTextColor(getResources().getColor(R.color.colorPrimary));
                    btn_teacherRem.setBackground(null);
                }else{
                    getTeacherRem();
                    btn_teacherRem.setTextColor(Color.WHITE);
                    btn_teacherRem.setBackgroundResource(R.drawable.bttn_round);
                }
                isTeacherRem = !isTeacherRem;
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ISADD =true;
                startActivity(new Intent(getContext(),NoteText.class));
            }
        });
        context = getActivity();
        _retrieve();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                POSITION = position;
                if (isTeacherRem){
                    startActivity(new Intent(getContext(),TeacherRemText.class));
                }else{
                    startActivity(new Intent(getContext(),NoteText.class));
                }
            }
        });
        return v;
    }

    private boolean threadbool =true;
    private Gson gson = new Gson();
    private void getTeacherRem(){
        Thread thread1 = new Thread(){
            @Override
            public void run() {
                while (threadbool){
                    try{
                        threadbool = false;
                        SocketSignal.sendMessage("teacherem/"+GetAuth.Username);
                        SocketSignal.getSignal = "";
                        Thread.sleep(1000);
                        AllData.dataTeacherRem =
                                gson.fromJson(SocketSignal.getSignal, DataTeacherRem.class);
                        if (AllData.dataTeacherRem == null){
                            threadbool = true;
                        }
                        Thread.sleep(1000);
                    }catch (Exception ex){
                        threadbool = true;
                    }
                }
//                Log.d(TAG,"Username "+GetAuth.Username);
//                boolean isRemove = true;
//                for (int i = 0 ; i < AllData.dataTeacherRem.title.size();i++){
//                  String[] tos = AllData.dataTeacherRem.to.get(i).split(",");
//                    isRemove = true;
//                    for (int ii = 0 ; ii < tos.length ; ii++){
//                        if (!isRemove){
//                            return;
//                        }
//                        if (tos[ii].equals(GetAuth.Username)){
//                            isRemove = false;
//                        }
//                        isRemove = true;
//                        Log.d(TAG,"tos "+String.valueOf(ii)+" : "+tos[ii]);
//                    }
//                    if (isRemove){
//                        AllData.dataTeacherRem.title.remove(i);
//                        AllData.dataTeacherRem.text.remove(i);
//                        AllData.dataTeacherRem.date.remove(i);
//                        AllData.dataTeacherRem.noteid.remove(i);
//                        AllData.dataTeacherRem.to.remove(i);
//                    }
//                    Log.d(TAG,AllData.dataTeacherRem.noteid.get(i));
//                }
                final ListTeacherRem adapter = new ListTeacherRem(getActivity()
                        ,R.layout.list_main_note);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        listView.setAdapter(adapter);
                    }
                });
                this.interrupt();
                loading.dismiss();
            }
        };
        loading.show();
        thread1.start();
    }
    public static void _retrieve(){
        ISADD = false;
        title = new ArrayList<String>(AllData.dataNote.title);
        text = new ArrayList<String>(AllData.dataNote.text);
        date = new ArrayList<String>(AllData.dataNote.date);
        ListMainNote listMainNote = new ListMainNote(context,
                title,text,date);
        listView.setAdapter(listMainNote);
    }
}
