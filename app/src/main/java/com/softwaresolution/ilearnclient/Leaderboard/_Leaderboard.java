package com.softwaresolution.ilearnclient.Leaderboard;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.softwaresolution.ilearnclient.JsonData.AllData;
import com.softwaresolution.ilearnclient.Leaderboard.LeaderBoardData.ListMainLeaderboard;
import com.softwaresolution.ilearnclient.R;

import java.util.ArrayList;
import java.util.List;

public class _Leaderboard extends Fragment {
    View v;
    ListView listview;
    List<String> name ;
    List<String> score ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.menu_leaderboard,container,false);
        listview = (ListView) v.findViewById(R.id.listview);
        name = new ArrayList<String>(AllData.dataLeaderboard.name);
        score = new ArrayList<String>(AllData.dataLeaderboard.score);
        ListMainLeaderboard listMainLeaderboard =
                new ListMainLeaderboard(getActivity(),name,score);
        listview.setAdapter(listMainLeaderboard);
        return v;
    }
}
