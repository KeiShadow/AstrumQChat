package com.keiko.astrumqchat;


import android.os.StrictMode;
import android.util.Log;
import android.widget.ArrayAdapter;


import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by Petr on 16.07.2017.
 */

public class Login {
    final private String urlMsg = "https://private-0d820c-aqhr.apiary-mock.com/api/messages";
    final private String urlLogin="https://private-0d820c-aqhr.apiary-mock.com/api/login";//"https://private-0d820c-aqhr.apiary-mock.com/api/login" http://production.astrumq.com/entry_test/www/api/
    private Connection connection;
    private  String output,token,id;

    private ArrayList<HashMap<String, String>> profilefield;
   public Login(String email, String password)  {
       StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


       String input = "{\"email\":\""+email+"\",\"password\":\""+password+"\"}";

       connection = new Connection();

       output= connection.Post(urlLogin,input,"application/json");
       try{
           token =getToken(output);
           id = getID(output);
           /*Musim potom registrovanemu uzivateli ulozit Token...*/
           UserProfile newUserProfile = new UserProfile();
           newUserProfile.addUser(email,password,token,id);

           /*tohel bych pouzil u registrace jelikoz vytvarim noveho uzivatele.... u prihlaseni bych pouzil jiny konstruktor*/
           //Metodu uz mam naimplementovanou

       }catch (Exception e){
           e.printStackTrace();
       }
   }

   public String getID(String output) throws JSONException {
       JSONObject object =new JSONObject(output);
       String id = object.getString("id");
       return id;
   }
   public String getToken(String output) throws JSONException {
       JSONObject object =new JSONObject(output);
       String Token = object.getString("token");
       return Token;
   }


}


