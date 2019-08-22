package com.softwaresolution.ilearnclient.Status;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.softwaresolution.ilearnclient.JsonData.AllData;
import com.softwaresolution.ilearnclient.R;
import com.softwaresolution.ilearnclient.Status.StatusData.ListMainStatus;

import java.util.ArrayList;
import java.util.List;

public class _Status extends Fragment {
    View v;
    ListView listview;
    List<String> maintopic;
    List<String> subtopic;
    List<String> score;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.main_status, container, false);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        maintopic= new ArrayList<String>(AllData.dataStatus.maintopic);
        subtopic= new ArrayList<String>(AllData.dataStatus.subtopic);
        score= new ArrayList<String>(AllData.dataStatus.score);
        listview = (ListView) v.findViewById(R.id.listview);
        ListMainStatus listMainStatus = new ListMainStatus(getActivity(),maintopic,
                                                             subtopic,score);
        listview.setAdapter(listMainStatus);
        return v;
    }
}
