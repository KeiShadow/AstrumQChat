package com.keiko.astrumqchat;


import android.os.StrictMode;
import android.util.Log;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Petr on 16.07.2017.
 */


public class Register {
    private String urlReg = "https://private-0d820c-aqhr.apiary-mock.com/api/user/registration";
    private String output,token;

    public Register(String nick, String name, String surname,String email, String password) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        Connection connection = new Connection();
        String input = "{\"nick\":\"" + nick + "\",\"name\":\"" + name + "\",\"surname\":\"" + surname + "\",\"email\":\"" + email + "\",\"password\":\"" + password + "\"}";
        output = connection.Post(urlReg, input, "application/json");
    try{
        token = getToken(output);
        /*Musim potom registrovanemu uzivateli ulozit Token...*/
    }catch (Exception e){
        e.printStackTrace();
    }

    }


    public String getToken(String output) throws JSONException {
        JSONObject object =new JSONObject(output);
        String Token = object.getString("token");
        return Token;
    }
}
