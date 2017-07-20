package com.keiko.astrumqchat;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Petr on 16.07.2017.
 */

public class CustomListAdapter extends ArrayAdapter {
    private ArrayList<UserProfile> user;
    private static class ViewHolder {
        TextView titleField;
        TextView descField;
        TextView emailField;
        TextView date;


        ImageView info;
    }
    //to reference the Activity
    private Activity context;

    //to store the animal images
    //private final Integer[] imageIDarray;

    private final  ArrayList<HashMap<String, String>> profileArray;


    public CustomListAdapter(Activity context, ArrayList<HashMap<String, String>> profileArray, ArrayList<UserProfile> user) { // ArrayList<HashMap<String, String>> descriptionArray, ArrayList<HashMap<String, String>> profileArray) {
        super(context, R.layout.chat_row, profileArray);
        this.context = context;
        this.profileArray = profileArray;
        this.user = user;
    }

    public View getView(int position, View convertview, ViewGroup parent) {
        View rowView = convertview;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        rowView = inflater.inflate(R.layout.chat_row, parent, false);

        //this code gets references to objects in the listview_row.xml file
        /*Prijemnce*/
        TextView titleField = (TextView) rowView.findViewById(R.id.titleView);
        TextView descField = (TextView) rowView.findViewById(R.id.descView);
        TextView authorField = (TextView) rowView.findViewById(R.id.author);
        ImageView imageField = (ImageView) rowView.findViewById(R.id.imageViewID);
        LinearLayout reciverLayout = (LinearLayout) rowView.findViewById(R.id.ReciverLayout);

        //TextView profileField = (TextView) rowView.findViewById(R.id.Tv_profile);
        //ImageView imageView = (ImageView) rowView.findViewById(R.id.imageView);

        /*Odesilatel*/
        TextView titleSender = (TextView) rowView.findViewById(R.id.Tv_currentTitle);
        TextView descSender = (TextView) rowView.findViewById(R.id.Tv_currentDesc);
        TextView authorSender = (TextView) rowView.findViewById(R.id.Tv_currentEmail);
        ImageView imageSender = (ImageView) rowView.findViewById(R.id.VI_senderImage);
        LinearLayout senderLayout = (LinearLayout) rowView.findViewById(R.id.SenderLayout);

        if(profileArray.get(position).get("id").contentEquals(user.get(0).getID())){
            titleSender.setText((CharSequence) profileArray.get(position).get("title"));
            descSender.setText((CharSequence) profileArray.get(position).get("description"));

            if(profileArray.get(position).get("name")==null || profileArray.get(position).get("surname")==null){
                authorSender.setText("Admin ("+(CharSequence) profileArray.get(position).get("email")+")");
            }  else{
                authorSender.setText((CharSequence)profileArray.get(position).get("name") + " "+profileArray.get(position).get("surname")+"("+(CharSequence) profileArray.get(position).get("email")+")");
            }

            titleField.setVisibility(View.INVISIBLE);
            titleField.setGravity(Gravity.RIGHT);
            imageField.setVisibility(View.INVISIBLE);

            reciverLayout.setVisibility(View.INVISIBLE);

            descField.setVisibility(View.INVISIBLE);
            descField.setGravity( Gravity.RIGHT);
            authorField.setVisibility(View.INVISIBLE);
            authorField.setGravity( Gravity.RIGHT);



        }else{
            //this code sets the values of the objects to values from the arrays
            titleField.setText((CharSequence) profileArray.get(position).get("title"));
            descField.setText((CharSequence) profileArray.get(position).get("description"));


            if(profileArray.get(position).get("name")==null || profileArray.get(position).get("surname")==null){
                authorField.setText("Admin ("+(CharSequence) profileArray.get(position).get("email")+")");
            }  else{
                authorField.setText((CharSequence)profileArray.get(position).get("name") + " "+profileArray.get(position).get("surname")+"("+(CharSequence) profileArray.get(position).get("email")+")");
            }
            titleSender.setVisibility(View.INVISIBLE);
            titleSender.setGravity( Gravity.LEFT);
            authorSender.setVisibility(View.INVISIBLE);
            authorSender.setGravity( Gravity.LEFT);
            descSender.setVisibility(View.INVISIBLE);
            descSender.setGravity( Gravity.LEFT);
            imageSender.setVisibility(View.INVISIBLE);

            senderLayout.setVisibility(View.INVISIBLE);

        }
        //imageView.setImageResource(imageIDarray[position]);

        return rowView;

    }

}
