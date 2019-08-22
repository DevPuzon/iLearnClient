package com.softwaresolution.ilearnclient.AccountSettings;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.softwaresolution.ilearnclient.Auth.GetAuth;
import com.softwaresolution.ilearnclient.JsonData.AllData;
import com.softwaresolution.ilearnclient.LoginForm;
import com.softwaresolution.ilearnclient.R;
import com.softwaresolution.ilearnclient.SendMessage;

public class _AccountSettings extends Fragment {
    View v;
    Button btn_send;
    TextView txt_autid,txt_stdname,txt_password,txt_ip,txt_port;
    EditText editxt_message;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.menu_account_settings,container,false);
        btn_send = (Button) v.findViewById(R.id.bttn_send);
        txt_autid = (TextView) v.findViewById(R.id.txt_authid);
        txt_stdname = (TextView) v.findViewById(R.id.txt_stdname);
        txt_password = (TextView) v.findViewById(R.id.txt_password);
        txt_ip = (TextView) v.findViewById(R.id.txt_ip);
        txt_port = (TextView) v.findViewById(R.id.txt_port);
        editxt_message = (EditText) v.findViewById(R.id.edittxt_message);
        txt_autid.setText(AllData.dataStudent.authid);
        txt_stdname.setText(AllData.dataStudent.stdname);
        txt_password.setText(AllData.dataStudent.password);
        txt_ip.setText(GetAuth.Ipaddress);
        txt_port.setText("200");
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendMessage sendMessage = new SendMessage();
                sendMessage.SendMessage(getContext(),"message/"+
                                GetAuth.Username+
                                "/"+editxt_message.getText().toString()
                                        ,null,"");
            }
        });
        return v;
    }
}
