package com.keiko.astrumqchat;

/**
 * Created by Petr on 13.07.2017.
 */
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;


public class Login {

    public Login(String email, String password){

            Client client = ClientBuilder.newClient();
            Entity payload = Entity.json("{  'login': 'email',  'password': 'password'}");
            Response response = client.target("http://production.astrumq.com/entry_test/www/sign/in").request(MediaType.APPLICATION_JSON_TYPE).post(payload);
            System.out.println("status: " + response.getStatus());
            System.out.println("headers: " + response.getHeaders());
            System.out.println("body:" + response.readEntity(String.class));
    };

    public Login(){
        Client client = ClientBuilder.newClient();
        Response response = client.target("https://private-0d820c-aqhr.apiary-mock.com/api/login/salt")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get();

        System.out.println("status: " + response.getStatus());
        System.out.println("headers: " + response.getHeaders());
        System.out.println("body:" + response.readEntity(String.class));



    }



}
