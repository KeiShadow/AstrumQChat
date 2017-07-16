package com.keiko.astrumqchat;


import android.os.StrictMode;
import android.util.Log;


import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by Petr on 16.07.2017.
 */

public class Login {
    final private String urlMsg = "https://private-0d820c-aqhr.apiary-mock.com/api/messages";
    final private String urlLogin="https://private-0d820c-aqhr.apiary-mock.com/api/login";//"https://private-0d820c-aqhr.apiary-mock.com/api/login" http://production.astrumq.com/entry_test/www/api/

   public Login(User user,String email, String password){
       StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
       StrictMode.setThreadPolicy(policy);

    try{
        ClientResponse response;
        WebResource wR;
        Client client = Client.create();

        wR = client.resource(urlLogin);
        String input = "{\"email\":\""+email+"\",\"password\":\""+password+"\"}";
        response = wR.type("application/json").post(ClientResponse.class,input);

        if(response.getStatus()!=201){
            throw new RuntimeException("Filed: HTTP error code: "+response.getStatus());
        }
        String output = response.getEntity(String.class);
        Log.wtf("Output from server.....\n",String.valueOf(output));

        //Token pro user
        user.setEmail(email);
        user.setPassWord(password);
        user.setID(getID(output));
        user.setToken(getToken(output));

    } catch(Exception e){
        e.printStackTrace();
    }

   }
   public int getID(String output) throws JSONException {
       JSONObject object =new JSONObject(output);
       String id = object.getString("token");
       int idi= Integer.parseInt(id);
       return idi;
   }
   public String getToken(String output) throws JSONException {
       JSONObject object =new JSONObject(output);
       String Token = object.getString("token");
       return Token;
   }


}


