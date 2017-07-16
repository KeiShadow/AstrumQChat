package com.keiko.astrumqchat;

import android.app.Activity;
import android.content.Context;
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

/**
 * Created by Petr on 16.07.2017.
 */

public class CustomListAdapter extends ArrayAdapter {
    //to reference the Activity
    private final Activity context;

    //to store the animal images
    //private final Integer[] imageIDarray;

    //to store the list of countries
    private final  ArrayList<HashMap<String, String>> titleArray;

    //to store the list of countries
    private final  ArrayList<HashMap<String, String>> descriptionArray;

    private final  ArrayList<HashMap<String, String>> profileArray;

    public CustomListAdapter(Activity context, ArrayList<HashMap<String, String>> titleArray, ArrayList<HashMap<String, String>> descriptionArray, ArrayList<HashMap<String, String>> profileArray){
        super(context,R.layout.listviewrow,titleArray);

        this.context = context;
        this.titleArray = titleArray;
        this.descriptionArray = descriptionArray;
        this.profileArray = profileArray;

    }


    public View GetView(int position, View view, ViewGroup parent){
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.listviewrow, null,true);

        TextView titleField = (TextView) rowView.findViewById(R.id.Tv_title);
        TextView descField = (TextView) rowView.findViewById(R.id.Tv_description);
        TextView profileField = (TextView) rowView.findViewById(R.id.Tv_profile);

        //ImageView imageView = (ImageView) rowView.findViewById(R.id.imageView);

        titleField.setText((CharSequence) titleArray.get(position));
        descField.setText((CharSequence) descriptionArray.get(position));
        profileField.setText((CharSequence) profileArray.get(position));

        //imageView.setImageResource(imageIDarray[position]);

        return rowView;

    }

}
