package com.keiko.astrumqchat;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.google.common.hash.Hashing;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        final Button But_login;
        final Button But_reg;
        final String urlLogin = "https://private-0d820c-aqhr.apiary-mock.com/api/login";//"https://private-0d820c-aqhr.apiary-mock.com/api/login" http://production.astrumq.com/entry_test/www/api/
        final String output;


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
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                String input = "{\"email\":\"" + Et_email.getText().toString() + "\",\"password\":\"" + Et_password.getText().toString() + "\"}";
                try {
                    Connection cnt = new Connection();
                    String output= cnt.Post(urlLogin,input,"application/json");

                    String token, id;

                    Intent listMessagesActivity = new Intent(LoginActivity.this,ListOfMessagesActivity.class);
                    JSONObject object = new JSONObject(output);
                    id = object.getString("id");
                    JSONObject obj = new JSONObject(output);
                    token = obj.getString("token");

                    ArrayList<UserProfile> user = new ArrayList<UserProfile>();
                    UserProfile userProfile = new UserProfile();
                    userProfile.setEmail(Et_email.getText().toString());
                    userProfile.setPassWord(Et_password.getText().toString());
                    userProfile.setToken(token);
                    userProfile.setID(id);

                    user.add(userProfile);

                    Bundle extra = new Bundle();

                    extra.putSerializable("User",user);

                    listMessagesActivity.putExtra("extra",extra);

                    LoginActivity.this.startActivity(listMessagesActivity);


                } catch (JSONException e) {
                    e.printStackTrace();
                }catch (Exception e){
                    e.printStackTrace();
                }

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
