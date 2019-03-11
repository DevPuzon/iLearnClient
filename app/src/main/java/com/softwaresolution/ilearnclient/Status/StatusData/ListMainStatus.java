package com.softwaresolution.ilearnclient.Status.StatusData;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.softwaresolution.ilearnclient.Activity.MainActivityData.ListMainActivity;
import com.softwaresolution.ilearnclient.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ListMainStatus extends  RecyclerView.Adapter<ListMainStatus.ViewHolder> {

    List<String> listtopic;
    private Context context;

    public ListMainStatus(Context context, List<String> listtopic) {
        this.listtopic = listtopic;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_main_status, viewGroup, false);
        TextView txt_list = (TextView) view.findViewById(R.id.txt_list);
//        txt_list.setText(listtopic.get(i));
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.txt_list.setText(listtopic.get(i));
    }

    @Override
    public int getItemCount() {
        return listtopic.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_list;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_list = itemView.findViewById(R.id.txt_list);
        }
    }
}
