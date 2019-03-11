package com.softwaresolution.ilearnclient.Activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.softwaresolution.ilearnclient.Activity.MainActivityData.ListMainActivity;
import com.softwaresolution.ilearnclient.R;

import java.util.ArrayList;
import java.util.List;

public class _Activity extends Fragment {
    View v;
    List<String> list;
    GridView grid_activity;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.menu_activity,container,false);
        grid_activity = (GridView) v.findViewById(R.id.grid_activty);
        list = new ArrayList<String>();
        list.add("Dynamically mathematics");
        list.add("Mathematics humba sda");
        list.add("sathemsd aas as asdwe");
        ListMainActivity listMainActivity = new ListMainActivity(getActivity(), list);
        grid_activity.setAdapter(listMainActivity);
        return v;
    }
}
