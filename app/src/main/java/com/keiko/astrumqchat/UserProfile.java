package com.keiko.astrumqchat;
import android.util.Log;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


/**
 * Created by Petr on 16.07.2017.
 */

public class UserProfile implements Serializable {

    private String nick;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String token;
    private String id;
    private String date;
    private String idM;

    public void setNick(String nick){
        this.nick = nick;

    }
    public  String getNick(){
        return  nick;

    }
    public void setName(String name){
        this.name = name;

    }
    public  String getName(){
        return  name;

    }
    public void setSurname(String surname){
        this.surname = surname;
    }
    public String getSurname(){
        return surname;
    }

    public void setEmail(String email){
        this.email = email ;
    }

    public String getEmail(){
        return email ;
    }
    public void setPassWord(String password){
        this.password = password ;
    }
    public String getPassword(){
        return password ;
    }
    public void setToken(String token){
        this.token = token;
    }
    public String getToken(){
        return token;
    }

    public void setID(String id){this.id = id;}
    public String getID(){return id;}

    public void setIDM(String idM){this.idM = idM;}
    public String getIdM(){return idM;}

    public void setDate(String Date) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.GERMAN);
        Date datum = format.parse(Date);
        Log.i("Datum",datum.toString());
        this.date = datum.toString();
    }
    public String getDate(){
        return date;
    }

    public UserProfile(UserProfile user){
        this.nick = user.getNick();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.token = user.getToken();
        this.id=user.getID();
    }

    public UserProfile(){

    }
}
