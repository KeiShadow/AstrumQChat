package com.keiko.astrumqchat;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.common.hash.Hashing;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class RegistrationActivity extends AppCompatActivity {
    private String urlReg = "http://production.astrumq.com/entry_test/www/api/user/registration";//https://private-0d820c-aqhr.apiary-mock.com/api/user/registration
    private String output,id,token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Toolbar my_toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(my_toolbar);

        ActionBar ab =getSupportActionBar();

        ab.setDisplayHomeAsUpEnabled(true);


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
                try {
                    if (Et_email.getText().toString() != null && Et_pass.getText().length() > 5) {
                        String hashedpass = Hashing.sha256().hashString(Et_pass.getText().toString(), StandardCharsets.UTF_8).toString();

                        Connection connection = new Connection();
                        String input = "{\"nick\":\"" + Et_nick.getText().toString() + "\",\"name\":\"" + Et_name.getText().toString() + "\",\"surname\":\"" + Et_surname.getText().toString() + "\",\"email\":\"" + Et_email.getText().toString() + "\",\"password\":\"" + hashedpass + "\"}";

                        output = connection.Post(urlReg, input, "application/json");

                         if(output.contentEquals("400")){
                            Toast toast = Toast.makeText(getApplicationContext(),"Uživatel již existuje",Toast.LENGTH_SHORT);
                            toast.show();
                        }else if(output.contentEquals("401")){
                             Toast toast = Toast.makeText(getApplicationContext(),"Chyba registrace",Toast.LENGTH_SHORT);
                             toast.show();
                         }else if(output.contentEquals("422")){
                            Toast toast = Toast.makeText(getApplicationContext(),"Email neni validní",Toast.LENGTH_SHORT);
                            toast.show();
                        }

                        Intent listMessagesActivity = new Intent(RegistrationActivity.this, Chat.class);
                        JSONObject object = new JSONObject(output);
                        id = object.getString("id");
                        JSONObject obj = new JSONObject(output);
                        token = obj.getString("token");


                        ArrayList<UserProfile> user = new ArrayList<UserProfile>();
                        UserProfile userProfile = new UserProfile();
                        userProfile.setNick(Et_nick.getText().toString());
                        userProfile.setName(Et_name.getText().toString());
                        userProfile.setSurname(Et_surname.getText().toString());
                        userProfile.setEmail(Et_email.getText().toString());
                        userProfile.setPassWord(Et_pass.getText().toString());
                        userProfile.setToken(token);
                        userProfile.setID(id);

                        user.add(userProfile);

                        Bundle extra = new Bundle();

                        extra.putSerializable("User", user);

                        listMessagesActivity.putExtra("registrovany", true);

                        listMessagesActivity.putExtra("extra", extra);

                        RegistrationActivity.this.startActivity(listMessagesActivity);
                        }else{
                        Toast toast = Toast.makeText(getApplicationContext(),"Zadejte email nebo heslo, heslo musi mít více než 6 znaků",Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    }catch(JSONException e){
                        e.printStackTrace();
                    }


            }
        });

    }

}
