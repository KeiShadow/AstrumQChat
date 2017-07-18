package com.keiko.astrumqchat;

import android.util.Log;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Petr on 18.07.2017.
 */

public class Connection {
    private   ClientResponse response;
    private   WebResource wR;
    private   Client client;
    private String url;

    /*Metoda pro prihlaseni na server output vraci ID a token*/
    public String Post(String url,String input,String type){

        try{
            client = Client.create();
            wR = client.resource(url);
            response =wR.type(type).post(ClientResponse.class,input);
            if(response.getStatus()!=201){
                throw new RuntimeException("Filed: HTTP error code: "+response.getStatus());
            }
            String output = response.getEntity(String.class);
            Log.wtf("Server output",output);
            return output;
        }catch (Exception e){
            e.printStackTrace();
        }
          return "";
    }
    /*Metoda vraci zpravy a profil uzivatele*/
    public  String Get(String url, String token,String type){
        try {
            client = Client.create();
            wR = client.resource(url);
            response = wR.queryParam("offsetFilter","0").queryParam("limitFilter","10").type(type).header("Authorization", token).get(ClientResponse.class);
            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }
            String output = response.getEntity(String.class);
            return  output;

        }catch (Exception e){

        }
        return "";
    }

}
