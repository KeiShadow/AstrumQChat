package com.keiko.astrumqchat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class MainActivity extends AppCompatActivity {
        private Button login, registration;
        private EditText email, password;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      login = (Button) findViewById(R.id.login);
      registration = (Button)findViewById(R.id.registration);

      email = (EditText) findViewById(R.id.email);
      password = (EditText)findViewById(R.id.password);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
         String hashedpass = Hashing.sha256().hashString(password.getText().toString(), StandardCharsets.UTF_8).toString();
            Log.i("heslo:",hashedpass);
                Login login = new Login(email.getText().toString(),hashedpass);

            /*if(email.toString() !=" " && (password.length()>=6 && password.toString()!="")){


                }
            */
            }
        });


        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.registration);
            }
        });



    }

}
