package com.softwaresolution.ilearnclient.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.softwaresolution.ilearnclient.Activity.MainActivityData.ListPreviewResult;
import com.softwaresolution.ilearnclient.R;

public class PreviewResult extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_result);
        ListView listpreview = (ListView) findViewById(R.id.listpreview);
        ListPreviewResult adpater = new ListPreviewResult(this,R.layout.list_main_preview );
        listpreview.setAdapter(adpater);
    }
}
