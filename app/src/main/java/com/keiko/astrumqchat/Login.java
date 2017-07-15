package com.keiko.astrumqchat;

import android.os.StrictMode;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.IOException;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;


import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import javax.xml.*;

/**
 * Created by Petr on 16.07.2017.
 */

public class Login {

   /* public Login(String email, String password) {
        HttpURLConnection client = null;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        try{
            URL url = new URL("https://private-0d820c-aqhr.apiary-mock.com/api/login?email="+email+"&password="+password);
            client= (HttpURLConnection) url.openConnection();
            client.setRequestMethod("POST");
            //client.setRequestProperty("Content-Type", "application/json");
            //client.setRequestProperty("password",password);
            client.setDoOutput(true);

            OutputStream stream = new BufferedOutputStream(client.getOutputStream());
            writeStream(stream);
            Log.i("Jsem tu bro","good");
            client.disconnect();

        } catch(SocketTimeoutException e) {
            //e.getMessage();
        } catch (IOException e){
            //e.printStackTrace();

        } catch (Exception e){
            e.printStackTrace();
        }finally {
            if(client != null) // Make sure the connection is not null.
                client.disconnect();
        }


    }*/
   public Login(String email, String password){
       StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

       StrictMode.setThreadPolicy(policy);
    try{

        Client client = ClientBuilder.newClient();

        Entity payload = Entity.json("{  'email': '"+email+"',  'password': '"+password+"'}");
        Response response = client.target("https://private-0d820c-aqhr.apiary-mock.com/api/login")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(payload);
        Log.i("Status",String.valueOf(response.getStatus()));

    }catch(ClientErrorException e){
        e.printStackTrace();
    }
    catch (Exception e){
        e.printStackTrace();
    }

   }

    private void writeStream(OutputStream stream) throws IOException {

        String output = "Hello world";

        stream.write(output.getBytes());
        stream.flush();
    }

}


