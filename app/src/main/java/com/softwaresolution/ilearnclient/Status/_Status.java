package com.softwaresolution.ilearnclient.Status;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

import com.softwaresolution.ilearnclient.Activity.MainActivityData.ListMainActivity;
import com.softwaresolution.ilearnclient.R;
import com.softwaresolution.ilearnclient.Status.StatusData.ListMainStatus;

import org.lucasr.twowayview.TwoWayView;

import java.util.ArrayList;
import java.util.List;

public class _Status extends Fragment {
    GridView grid_activity;
    View v;
    List<String> list;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.menu_status,container,false);
//        twoway = (GridView) v.findViewById(R.id.twoway);
        list = new ArrayList<String>();
        list.add("Main Topic 1");
        list.add("Main Topic 2");
        list.add("Main Topic 3");
        list.add("Main Topic 4");
        list.add("Main Topic 5");
        list.add("Main Topic 6");
        list.add("Main Topic 7");

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = v.findViewById(R.id.topList);
        recyclerView.setLayoutManager(layoutManager);
        ListMainStatus adapter = new ListMainStatus(getContext(), list);
        recyclerView.setAdapter(adapter);


        grid_activity = (GridView) v.findViewById(R.id.grid_activty);
        list = new ArrayList<String>();
        list.add("Dynamically mathematics");
        list.add("Mathematics humba sda");
        list.add("sathemsd aas as asdwe");
        list.add("Dynamically mathematics");
        list.add("Mathematics humba sda");
        list.add("sathemsd aas as asdwe");
        list.add("Dynamically mathematics");
        list.add("Mathematics humba sda");
        list.add("sathemsd aas as asdwe");
        list.add("Dynamically mathematics");
        list.add("Mathematics humba sda");
        list.add("sathemsd aas as asdwe");
        list.add("Dynamically mathematics");
        list.add("Mathematics humba sda");
        list.add("sathemsd aas as asdwe");
        ListMainActivity listMainActivity = new ListMainActivity(getActivity(), list);
        grid_activity.setAdapter(listMainActivity);
        return v;
    }
}
