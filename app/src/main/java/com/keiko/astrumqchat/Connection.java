package com.keiko.astrumqchat;

import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

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
    private ClientResponse response;
    private WebResource wR;
    private Client client;


   // final String urlMsg = "Https://private-0d820c-aqhr.apiary-mock.com/api/messages";
    //final String urlLogin = "https://private-0d820c-aqhr.apiary-mock.com/api/login";//"https://private-0d820c-aqhr.apiary-mock.com/api/login" http://production.astrumq.com/entry_test/www/api/

     /*Metoda pro prihlaseni na server output vraci ID a token*/
        public String Post(String url,String input,String type){
            try{
                client = Client.create();
                wR = client.resource(url);
                response =wR.type(type).post(ClientResponse.class,input);
                 if(response.getStatus() ==400){
                     return String.valueOf(response.getStatus());
                }else if(response.getStatus() ==401){
                     return String.valueOf(response.getStatus());
                 }
                else if(response.getStatus() ==422){
                    return String.valueOf(response.getStatus());
                }
                else if(response.getStatus()==201){
                    String output = response.getEntity(String.class);
                    return output;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return "";
        }
        public String PostMessage(String url, String input, String token, String type){
            try{
                client = Client.create();
                wR = client.resource(url);
                response =wR.type(type).header("Authorization",token).post(ClientResponse.class,input);
                if(response.getStatus() ==422) {
                    return String.valueOf(response.getStatus());
                }
                else if(response.getStatus()==201){
                    String output = response.getEntity(String.class);
                    return output;
                }
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
                response = wR.queryParam("offsetFilter","0").queryParam("limitFilter","1000").type(type).header("Authorization", token).get(ClientResponse.class);
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
