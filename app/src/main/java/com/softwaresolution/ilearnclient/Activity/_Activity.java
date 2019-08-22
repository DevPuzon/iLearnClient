package com.softwaresolution.ilearnclient.Activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.softwaresolution.ilearnclient.Activity.MainActivityData.ListMainActivity;
import com.softwaresolution.ilearnclient.Auth.GetAuth;
import com.softwaresolution.ilearnclient.JsonData.AllData;
import com.softwaresolution.ilearnclient.LoginForm;
import com.softwaresolution.ilearnclient.MainBottom;
import com.softwaresolution.ilearnclient.R;
import com.softwaresolution.ilearnclient.SendMessage;

import java.util.ArrayList;
import java.util.List;

public class _Activity extends Fragment {
    View v;
    List<String> list;
    public static GridView grid_activity;
    ListMainActivity listMainActivity;
    AllData allData;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.menu_activity,container,false);
        grid_activity = (GridView) v.findViewById(R.id.grid_activty);
        list = new ArrayList<String>(allData.dataMainTopic.maintopic);
        listMainActivity = new ListMainActivity(getActivity(),
                                                list,
                                                R.layout.list_main_activity);
        grid_activity.setAdapter(listMainActivity);
        final SendMessage sendMessage = new SendMessage();
        sendMessage.isNextActivity = true;
        grid_activity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    sendMessage.SendMessage(getContext(),"subtopic/" + GetAuth.Subject
                            +"/"+allData.dataMainTopic.maintopic.get(position)
                    ,SubTopic.class, "");
                }catch (Exception ex){
                    Log.e("BugActivity",ex.getMessage());
                }
            }
        });
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
