package com.keiko.astrumqchat;

import android.os.Message;
import android.os.StrictMode;
import android.util.Log;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;



/**
 * Created by Petr on 16.07.2017.
 */

public class Login {

   public Login(String email, String password){
       StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
       StrictMode.setThreadPolicy(policy);

    try{
    Client client = Client.create();
        WebResource wR = client.resource("https://private-0d820c-aqhr.apiary-mock.com/api/login");//"https://private-0d820c-aqhr.apiary-mock.com/api/login" http://production.astrumq.com/entry_test/www/api/
        String input = "{\"email\":\""+email+"\",\"password\":\""+password+"\"}";
        ClientResponse response = wR.type("application/json").post(ClientResponse.class,input);

        if(response.getStatus()!=201){
         throw new RuntimeException("Filed: HTTP error code: "+response.getStatus());
        }

     Log.wtf("Output from server.....\n",String.valueOf(response.getEntity(String.class)));
    }catch(Exception e){
        e.printStackTrace();
    }

   }


}


