package com.keiko.astrumqchat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        final Button But_reg = (Button) findViewById(R.id.But_reg);
        final EditText Et_email = (EditText)findViewById(R.id.Et_email);
        final EditText Et_pass = (EditText) findViewById(R.id.Et_pass);
        final EditText Et_nick = (EditText)findViewById(R.id.Et_nick);
        final EditText Et_name = (EditText)findViewById(R.id.Et_name);
        final EditText Et_surname = (EditText)findViewById(R.id.Et_surname);

        But_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             Register reg = new Register(Et_nick.getText().toString(),
                        Et_name.getText().toString(),
                        Et_surname.getText().toString(),
                        Et_email.getText().toString(),
                        Et_pass.getText().toString());
                
                Intent listMessagesActivity = new Intent(RegistrationActivity.this,ListOfMessagesActivity.class);
                RegistrationActivity.this.startActivity(listMessagesActivity);
            }
        });

    }
}
