package com.keiko.astrumqchat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.google.common.hash.Hashing;
public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        final Button But_login;
        final Button But_reg;


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // String hashedpass = Hashing.sha256().hashString(password.getText().toString(), StandardCharsets.UTF_8).toString();

        /*Prihlaseni*/
        But_login = (Button)findViewById(R.id.But_login);
        final EditText Et_email = (EditText)findViewById(R.id.Et_email);
        final EditText Et_password=(EditText)findViewById(R.id.Et_password);
        But_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login log = new Login(Et_email.getText().toString(),Et_password.getText().toString());
            }
        });

        /*Registrace*/
        But_reg = (Button) findViewById(R.id.But_Registration);

        But_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent=new Intent(LoginActivity.this,RegistrationActivity.class);
                LoginActivity.this.startActivity(registerIntent);



            }
        });


    }

}
