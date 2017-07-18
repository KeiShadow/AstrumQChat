package com.keiko.astrumqchat;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

public class RegistrationActivity extends AppCompatActivity {
    private String urlReg = "https://private-0d820c-aqhr.apiary-mock.com/api/user/registration";
    private String output,id,token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        final Button But_reg = (Button) findViewById(R.id.But_reg);
        final EditText Et_email = (EditText) findViewById(R.id.Et_email);
        final EditText Et_pass = (EditText) findViewById(R.id.Et_pass);
        final EditText Et_nick = (EditText) findViewById(R.id.Et_nick);
        final EditText Et_name = (EditText) findViewById(R.id.Et_name);
        final EditText Et_surname = (EditText) findViewById(R.id.Et_surname);

        But_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            try{
                Connection connection = new Connection();
                String input = "{\"nick\":\"" + Et_nick.getText().toString()
                        + "\",\"name\":\"" +  Et_name.getText().toString()
                        + "\",\"surname\":\"" + Et_surname.getText().toString()
                        + "\",\"email\":\"" +  Et_email.getText().toString()
                        + "\",\"password\":\"" +  Et_pass.getText().toString() + "\"}";

                output=connection.Post(urlReg,input,"application/json");
                Intent listMessagesActivity = new Intent(RegistrationActivity.this, ListOfMessagesActivity.class);
                JSONObject object = new JSONObject(output);
                id = object.getString("id");
                JSONObject obj = new JSONObject(output);
                token = obj.getString("token");
                listMessagesActivity.putExtra("token",token);

                RegistrationActivity.this.startActivity(listMessagesActivity);

            }catch (JSONException e){
                e.printStackTrace();
            }

            }
        });

    }
}
