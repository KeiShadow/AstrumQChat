package com.keiko.astrumqchat;

/**
 * Created by Petr on 13.07.2017.
 */
import android.util.Log;

import java.io.Console;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;


public class Login {

    public Login(String email, String password){

            Client client = ClientBuilder.newClient();
            Entity payload = Entity.json("{  'email': "+email+",  'password': "+password+"}");
            Response response = client.target("http://production.astrumq.com/entry_test/www/api/").request(MediaType.TEXT_HTML_TYPE).post(payload);

            System.out.println("status: " + response.getStatus());
            System.out.println("headers: " + response.getHeaders());
            System.out.println("body:" + response.readEntity(String.class));
    };
}
