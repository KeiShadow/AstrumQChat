package com.keiko.astrumqchat;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import org.glassfish.jersey.internal.inject.Custom;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ListOfMessagesActivity extends AppCompatActivity{

    private ArrayList<HashMap<String, String>> profilefield;
    private ArrayList<HashMap<String, String>> titlefield;
    private ArrayList<HashMap<String, String>> descfield;

    private ListView lv;
    private String urlMsg ="Https://private-0d820c-aqhr.apiary-mock.com/api/messages";
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_messages);

        profilefield = new ArrayList<>();
        titlefield = new ArrayList<>();
        descfield = new ArrayList<>();



         User user = new User();

        new Messages().execute();


    }


    public class Messages extends AsyncTask<Void,Void,Void>
    {
        private String urlMsg ="https://private-0d820c-aqhr.apiary-mock.com/api/messages";
        private  User user;
        private ProgressDialog pDialog;

       /* public ClientResponse Connect(String url, User user){
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
        }*/

       /* public String getJsonMessages(){
            String output;
            //V output jsou json zpravy
            output = Connect(urlMsg,user).getEntity(String.class);

           // Log.wtf("Output from Server .... \n",output);
            return output;
        }*/


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(ListOfMessagesActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                ClientResponse response;
                WebResource wR;
                Client client = Client.create();
                wR = client.resource("https://private-0d820c-aqhr.apiary-mock.com/api/messages");
                response = wR.accept("application/json").header("Authorization","aXiousbndekasldnnlasrlnb").get(ClientResponse.class);
                if (response.getStatus() != 200) {
                    throw new RuntimeException("Failed : HTTP error code : "
                            + response.getStatus());

                }
                String output;
                //V output jsou json zpravy
                output = response.getEntity(String.class);
                JSONObject jsonObject = new JSONObject(output);
                JSONArray jsonArray = jsonObject.getJSONArray("messages");

                for(int i=0;i<jsonArray.length();i++){
                    JSONObject cid = jsonArray.getJSONObject(i);
                    JSONObject ctitle = jsonArray.getJSONObject(i);
                    JSONObject cdesc = jsonArray.getJSONObject(i);

                    String id = cid.getString("id");
                    String tittle = ctitle.getString("title");
                    String description = cdesc.getString("description");

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
        }
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            CustomListAdapter whatever = new CustomListAdapter(ListOfMessagesActivity.this, titlefield, descfield, profilefield);
            lv = (ListView) findViewById(R.id.listViewID);
            lv.setAdapter(whatever);
        }
    }
}
