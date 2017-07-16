package com.keiko.astrumqchat;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

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

public class Messages_1 extends AsyncTask<Void,Void,Void>
{
    private String urlMsg ="Https://private-0d820c-aqhr.apiary-mock.com/api/messages";
    private  User user;
    private ProgressDialog pDialog;

    @Override
    protected Void doInBackground(Void... voids) {
        return null;
    }
/*
    ArrayList<HashMap<String, String>> profilefield;
    ArrayList<HashMap<String, String>> titlefield;
    ArrayList<HashMap<String, String>> descfield;

   public ClientResponse Connect(String url, User user){
      ClientResponse response;
      WebResource wR;
      Client client = Client.create();
          wR = client.resource(url);
          response = wR.accept("application/json").header("Authorization",user.token).get(ClientResponse.class);
          if (response.getStatus() != 200) {
              throw new RuntimeException("Failed : HTTP error code : "
                      + response.getStatus());

          }
      return  response;
  }

  public String getJsonMessages(){
       String output;
        //V output jsou json zpravy
          output = Connect(urlMsg,user).getEntity(String.class);

          Log.wtf("Output from Server .... \n",output);
          return output;
  }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // Showing progress dialog
      //  pDialog = new ProgressDialog(ListOfMessagesActivity.this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();

    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            JSONObject jsonObject = new JSONObject(getJsonMessages());
            JSONArray jsonArray = new JSONArray("messages");

            for(int i=0;i<jsonArray.length();i++){
                JSONObject c = jsonArray.getJSONObject(i);

                String id = c.getString("id");
                String tittle = c.getString("title");
                String description = c.getString("description");

                HashMap<String, String> profile = new HashMap<>();
                HashMap<String, String> desc = new HashMap<>();
                HashMap<String, String> title = new HashMap<>();

                profile.put("id",id);
                title.put("title",tittle);
                desc.put("description",description);

                profilefield.add(profile);
                titlefield.add(title);
                descfield.add(desc);

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }*/
}
