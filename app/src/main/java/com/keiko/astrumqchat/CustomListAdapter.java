package com.keiko.astrumqchat;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Created by Petr on 16.07.2017.
 */

public class CustomListAdapter extends ArrayAdapter{
    //to reference the Activity
    private  Activity context;

    //to store the animal images
    //private final Integer[] imageIDarray;

    //to store the list of countries
    private  ArrayList<HashMap<String, String>> titleArray;

    //to store the list of countries
    private  ArrayList<HashMap<String, String>> descriptionArray;




    //private final  ArrayList<HashMap<String, String>> profileArray;


    public CustomListAdapter(Activity context, ArrayList<HashMap<String, String>> titleArray, ArrayList<HashMap<String, String>> descriptionArray){ // ArrayList<HashMap<String, String>> descriptionArray, ArrayList<HashMap<String, String>> profileArray) {
        super(context, R.layout.listview_row, titleArray);
        this.context = context;
        this.titleArray = titleArray;
        this.descriptionArray = descriptionArray;

        //this.profileArray = profileArray;
    }



    public View getView(int position, View convertview, ViewGroup parent){
        View rowView = convertview;

        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        rowView=inflater.inflate(R.layout.listview_row,parent,false);

        //this code gets references to objects in the listview_row.xml file
        TextView titleField = (TextView) rowView.findViewById(R.id.titleView);


        TextView descField = (TextView) rowView.findViewById(R.id.descView);
        //TextView profileField = (TextView) rowView.findViewById(R.id.Tv_profile);
        //ImageView imageView = (ImageView) rowView.findViewById(R.id.imageView);

        //this code sets the values of the objects to values from the arrays
        titleField.setText((CharSequence) titleArray.get(position).get("title"));
        descField.setText((CharSequence) descriptionArray.get(position).get("description"));

        //profileField.setText((CharSequence) profileArray.get(position).get("id"));

        //imageView.setImageResource(imageIDarray[position]);

        return rowView;

    }

}
