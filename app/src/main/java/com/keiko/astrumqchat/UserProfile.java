package com.keiko.astrumqchat;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Petr on 16.07.2017.
 */

public class UserProfile implements Serializable {

    public String nick;
    public String name;
    public String surname;
    public String email;
    public String password;
    public String token;
    public String id;

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

    public UserProfile(UserProfile user){
        this.nick = user.getNick();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.token = user.getToken();
    }

    public UserProfile(){

    }
}
