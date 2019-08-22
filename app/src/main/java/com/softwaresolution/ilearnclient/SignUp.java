package com.softwaresolution.ilearnclient;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUp extends AppCompatActivity {
    EditText editxt_username,editxt_password,editxt_repassword;
    Button bttn_create;
    static Context context;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_form);
        context = SignUp.this;
        editxt_username = (EditText) findViewById(R.id.editxt_username);
        editxt_password = (EditText) findViewById(R.id.editxt_password);
        editxt_repassword = (EditText) findViewById(R.id.editxt_repassword);
        bttn_create = (Button) findViewById(R.id.bttn_create);
        bttn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editxt_username.getText().toString();
                String pass = editxt_password.getText().toString();
                String repass = editxt_repassword.getText().toString();
                if (repass.equals(pass)){
                    if (!TextUtils.isEmpty(username) ||!TextUtils.isEmpty(pass) ){
                        SendMessage sendMessage = new SendMessage();
                        sendMessage.SendMessage(SignUp.this,"signup/"
                                        +username+"/"+pass
                                ,null,"");
                    }else {
                        FancyToast.makeText(SignUp.this,"Please fill all the information"
                                ,FancyToast.LENGTH_LONG,FancyToast.INFO,false).show();
                    }
                }else {
                    FancyToast.makeText(SignUp.this,"You must enter the same password twice in order to confirm it."
                            ,FancyToast.LENGTH_LONG,FancyToast.INFO,false).show();
                }
            }
        });
    }
}
