package com.softwaresolution.ilearnclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginForm extends AppCompatActivity {
    Button bttn_login;
    TextView txt_signup;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_form);
        txt_signup = (TextView) findViewById(R.id.txt_signup);
        bttn_login = (Button) findViewById(R.id.bttn_login);
        bttn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginForm.this,MainBottom.class);
                startActivity(intent);
            }
        });
        txt_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginForm.this,SignUp.class);
                startActivity(intent);
            }
        });
    }
}
