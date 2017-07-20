package com.keiko.astrumqchat;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AuthorDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author_detail);


        Toolbar my_toolbar = (Toolbar) findViewById(R.id.AuthorTB);
        setSupportActionBar(my_toolbar);

        ActionBar ab =getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);


        TextView Tv_AuthorEamil = (TextView) findViewById(R.id.Tv_AuthorDetailEmail);
        TextView Tv_DescMessage = (TextView) findViewById(R.id.Tv_detailMessage);
        TextView Tv_CreatedMessage= (TextView) findViewById(R.id.Tv_createdMessage);
        TextView Tv_name = (TextView)findViewById(R.id.Tv_name);
        TextView Tv_surname = (TextView)findViewById(R.id.Tv_surname);

        String savedEmail = getIntent().getStringExtra("email");
        String savedTitle = getIntent().getStringExtra("title");
        String savedDesc = getIntent().getStringExtra("description");
        String DateMessage = getIntent().getStringExtra("dateCreated");
        String savedName = getIntent().getStringExtra("name");
        String savedSurname = getIntent().getStringExtra("surname");



            Tv_AuthorEamil.setText("Email: "+savedEmail);
            Tv_DescMessage.setText(savedDesc);
            Tv_CreatedMessage.setText(DateMessage);
            Tv_name.setText("Jméno: "+savedName);
            Tv_surname.setText("Příjmení: "+ savedSurname);


        ab.setTitle(savedTitle);


    }
}
