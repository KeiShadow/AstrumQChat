package com.keiko.astrumqchat;

import android.app.ProgressDialog;
import android.database.DataSetObserver;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ListOfMessagesActivity extends AppCompatActivity{
    ProgressDialog asyncDialog;

    private ArrayList<HashMap<String, String>> profilefield;
    private ArrayList<HashMap<String, String>> titleField;
    private ArrayList<HashMap<String, String>> descField;

    private ListView simpleList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_messages);

        profilefield = new ArrayList<>();
        titleField = new ArrayList<>();
        descField = new ArrayList<>();

        new Messages().execute();
    }

    public class Messages extends AsyncTask<Void,Void,Void> {
        //  ProgressDialog asyncDialog = new ProgressDialog();
        private String urlMsg = "Https://private-0d820c-aqhr.apiary-mock.com/api/messages";
        private ProgressDialog pDialog;
        private ListView simpleList;
        private UserProfile userProfile;

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
            String messages;

            try {
                Connection connection = new Connection();
                UserProfile userProfile = new UserProfile();
                messages =connection.Get(urlMsg,userProfile.getToken(userProfile),"application/json");
                Log.i("TOKEN",);

                //V output jsou json zpravy

                JSONObject jsonObject = new JSONObject(messages);
                JSONArray jsonArrayMsg = jsonObject.getJSONArray("messages");

                for (int i = 0; i < jsonArrayMsg.length(); i++) {
                    JSONObject cid = jsonArrayMsg.getJSONObject(i);
                    JSONObject ctitle = jsonArrayMsg.getJSONObject(i);
                    JSONObject cdesc = jsonArrayMsg.getJSONObject(i);

                    String id = cid.getString("id");
                    String tittle = ctitle.getString("title");
                    String description = cdesc.getString("description");

                    HashMap<String, String> profile = new HashMap<>();
                    HashMap<String, String> desc = new HashMap<>();
                    HashMap<String, String> title = new HashMap<>();

                    profile.put("id", id);
                    title.put("title", tittle);
                    desc.put("description", description);

                    profilefield.add(profile);
                    titleField.add(title);
                    descField.add(desc);

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

            //CustomListAdapter whatever = new CustomListAdapter(ListOfMessagesActivity.this, titleField,descField);
          ListAdapter whatever = new CustomListAdapter(ListOfMessagesActivity.this,titleField,descField);
            simpleList = (ListView) findViewById(R.id.listViewID);
            simpleList.setAdapter(whatever);

        }
    }

    }
