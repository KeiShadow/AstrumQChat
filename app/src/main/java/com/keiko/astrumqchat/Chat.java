package com.keiko.astrumqchat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

public class Chat extends AppCompatActivity{
    ProgressDialog asyncDialog;

    private ArrayList<HashMap<String, String>> msgandprofile;
    private ArrayList<HashMap<String, String>> sendedmessage;
    private ArrayList<UserProfile> user;


    private ListView chatList;
    private EditText Et_EditMessage;
    private ImageButton ButtonSendMessage;

    private String messages;
    private String urlMsg = "http://production.astrumq.com/entry_test/www/api/messages";//"Https://private-0d820c-aqhr.apiary-mock.com/api/messages"
    private String type = "application/json";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_layout);
        this.setTitle("Seznam zpráv");

        Toolbar my_toolbar = (Toolbar) findViewById(R.id.listToolbar);
        setSupportActionBar(my_toolbar);

        ActionBar ab =getSupportActionBar();

        msgandprofile = new ArrayList<>();
        sendedmessage = new ArrayList<>();

        Et_EditMessage = (EditText)findViewById(R.id.messageEditText);

        /*Vytvoreni pripojeni*/
        Connection cnt = new Connection();

        /*Nacteni uzivatele z prihlaseni kvuli tokenu*/
        Bundle extra = getIntent().getBundleExtra("extra");
        user = (ArrayList<UserProfile>) extra.getSerializable("User");

        /*Nacteni JSON zprav i autora do messages*/
        messages =cnt.Get(urlMsg,user.get(0).getToken(),type);
        /*Parsovani json zpravy*/
        parse(msgandprofile,user,messages);

        ButtonSendMessage = (ImageButton) findViewById(R.id.sendMessageButton);
        final CustomListAdapter whatever = new CustomListAdapter(Chat.this,msgandprofile);
        chatList = (ListView) findViewById(R.id.listViewID);

        ButtonSendMessage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String message = Et_EditMessage.getText().toString();
                if(!message.equalsIgnoreCase("")){
                    Connection cnt = new Connection();
                    String input = "{\"title\":\"Message\",\"description\":\"" + message + "\"}";
                    String output=cnt.PostMessage(urlMsg,input,user.get(0).getToken(),type);
                    parseMsg(sendedmessage,user,output);
                    whatever.add(sendedmessage.get(0));
                    whatever.notifyDataSetChanged();
                }

            }
        });



        // Updating parsed JSON data into ListView
        chatList.setAdapter(whatever);
        chatList.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        chatList.setStackFromBottom(true);

        chatList.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
                Intent Chat = new Intent(Chat.this,AuthorDetail.class);

                    setProfile(position,Chat);
                    startActivity(Chat);

                }
        });



    }


    public void parse( ArrayList<HashMap<String, String>> user,ArrayList<UserProfile> usertoken, String messages){
        try{
            JSONObject jsonObject = new JSONObject(messages);
            JSONArray jsonArrayMsg = jsonObject.getJSONArray("messages");

            for (int i = 0; i<jsonArrayMsg.length(); i++) {

                HashMap<String, String> profile = new HashMap<>();

                JSONObject message = jsonArrayMsg.getJSONObject(i);

                JSONObject author = message.getJSONObject("author");

                String idauth= author.getString("id");


                if(idauth.contentEquals(usertoken.get(0).getID())){
                    String email = usertoken.get(0).getEmail();

                    profile.put("email",email);
                    profile.put("id",idauth);

                    String title = message.getString("title");
                    String description = message.getString("description");
                    String Datecreated = message.getString("dateCreated");

                    profile.put("title",title);
                    profile.put("description",description);

                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                    profile.put("dateCreated", String.valueOf(format.parse(Datecreated)));


                }else{
                    String name = author.getString("name");
                    String surname = author.getString("surname");
                    String email = author.getString("email");

                    profile.put("id",idauth);
                    profile.put("name",name);
                    profile.put("surname",surname);
                    profile.put("email",email);

                    String title = message.getString("title");
                    String description = message.getString("description");
                    String Datecreated = message.getString("dateCreated");

                    profile.put("title",title);
                    profile.put("description",description);

                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                    profile.put("dateCreated", String.valueOf(format.parse(Datecreated)));

                }

                msgandprofile.add(profile);

            }



        }catch (JSONException e){
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public void parseMsg( ArrayList<HashMap<String, String>> user,ArrayList<UserProfile> usertoken, String output){
        try{
            HashMap<String, String> send = new HashMap<>();


            JSONObject jsonObject = new JSONObject(output);


                JSONObject mess = jsonObject.getJSONObject("message");

                String title = mess.getString("title");
                String description = mess.getString("description");

                Date today = new Date();
                String Datecreated = today.toString();
                JSONObject profile = jsonObject.getJSONObject("message").getJSONObject("author");
                String id = profile.getString("id");
                 String email = profile.getString("email");




                send.put("title", title);
                send.put("description", description);
                send.put("dateCreated", Datecreated);
                send.put("email",email);
                send.put("id",id);


                sendedmessage.add(send);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setProfile(int position,Intent Chat){

        String email = msgandprofile.get(position).get("email");
        String desc= msgandprofile.get(position).get("description");
        String title = msgandprofile.get(position).get("title");
        String DateMesg = msgandprofile.get(position).get("dateCreated");
        String name = msgandprofile.get(position).get("name");
        String surname = msgandprofile.get(position).get("surname");

        if(title.contentEquals("")){

            Chat.putExtra("name",name);
            Chat.putExtra("surname",surname);
            Chat.putExtra("email",email);
            Chat.putExtra("description",desc);
            Chat.putExtra("title","Bez názvu");
            Chat.putExtra("dateCreated",DateMesg);


            startActivity(Chat);
        }else{
            Chat.putExtra("email",email);
            Chat.putExtra("description",desc);
            Chat.putExtra("title",title);
            Chat.putExtra("dateCreated",DateMesg);
            Chat.putExtra("name",name);
            Chat.putExtra("surname",surname);

        }





    }

    public void sendMessage(ArrayList<HashMap<String, String>> user,ArrayList<UserProfile> userProfile){
        String message = Et_EditMessage.getText().toString();
        if(!message.equalsIgnoreCase("")){
            Connection cnt = new Connection();
            String input = "{\"title\":\"New Message\",\"description\":\"" + message + "\"}";
            String output=cnt.PostMessage(urlMsg,input,userProfile.get(0).getToken(),type);
        }

    }
}

    /*public class Messages  {
        //  ProgressDialog asyncDialog = new ProgressDialog();
        private String urlMsg = "http://production.astrumq.com/entry_test/www/api/messages";//Https://private-0d820c-aqhr.apiary-mock.com/api/messages
        private ProgressDialog pDialog;
        private ListView simpleList;
        private UserProfile userProfile;
*/
























   /*     @Override
   protected void onPreExecute() {
        super.onPreExecute();
        // Showing progress dialog
        pDialog = new ProgressDialog(Chat.this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();
    }

        @Override
        protected Void doInBackground(Void... voids) {
            String messages;

            try {
                Connection connection = new Connection();
                Bundle extra = getIntent().getBundleExtra("extra");
                ArrayList<UserProfile> user = (ArrayList<UserProfile>) extra.getSerializable("User");


                messages =connection.Get(urlMsg, user.get(0).token,"application/json");

                //V output jsou json zpravy

                JSONObject jsonObject = new JSONObject(messages);
                JSONArray jsonArrayMsg = jsonObject.getJSONArray("messages");

                for (int i = 0; i < jsonArrayMsg.length(); i++) {

                    HashMap<String, String> profile = new HashMap<>();

                    JSONObject message = jsonArrayMsg.getJSONObject(i);

                    JSONObject author = message.getJSONObject("author");

                    String idauth= author.getString("id");
                    String idmess = author.getString("id");

                    if(idauth.contentEquals(user.get(0).getID())){
                        String email = user.get(0).getEmail();

                        profile.put("email",email);

                        String title = message.getString("title");
                        String description = message.getString("description");
                        String Datecreated = message.getString("dateCreated");

                        profile.put("title",title);
                        profile.put("description",description);

                        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
                        profile.put("dateCreated", String.valueOf(format.parse(Datecreated)));


                    }else{
                        String name = author.getString("name");
                        String surname = author.getString("surname");
                        String email = author.getString("email");


                        profile.put("name",name);
                        profile.put("surname",surname);
                        profile.put("email",email);

                        String title = message.getString("title");
                        String description = message.getString("description");
                        String Datecreated = message.getString("dateCreated");

                        profile.put("title",title);
                        profile.put("description",description);

                        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
                        profile.put("dateCreated", String.valueOf(format.parse(Datecreated)));

                    }

                    profilefield.add(profile);

                }


            } catch (JSONException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }


            return null;
        }

        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();

              Updating parsed JSON data into ListView


          ListAdapter whatever = new CustomListAdapter(Chat.this,profilefield);
            simpleList = (ListView) findViewById(R.id.listViewID);
            simpleList.setAdapter(whatever);

            simpleList.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
                            Intent Chat = new Intent(Chat.this,AuthorDetail.class);

                                String email = profilefield.get(position).get("email");
                                String desc= profilefield.get(position).get("description");
                                String title = profilefield.get(position).get("title");
                                String DateMesg = profilefield.get(position).get("dateCreated");
                                String name = profilefield.get(position).get("name");
                                String surname = profilefield.get(position).get("surname");

                                if(title.contentEquals("")){

                                    Chat.putExtra("name",name);
                                    Chat.putExtra("surname",surname);
                                    Chat.putExtra("email",email);
                                    Chat.putExtra("description",desc);
                                    Chat.putExtra("title","Bez názvu");
                                    Chat.putExtra("dateCreated",DateMesg);


                                    startActivity(Chat);
                                }else{
                                    Chat.putExtra("email",email);
                                    Chat.putExtra("description",desc);
                                    Chat.putExtra("title",title);
                                    Chat.putExtra("dateCreated",DateMesg);
                                    Chat.putExtra("name",name);
                                    Chat.putExtra("surname",surname);

                                    startActivity(Chat);

                                }

                    }
            });






        }
      }*/
