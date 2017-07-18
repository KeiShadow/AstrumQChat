package com.keiko.astrumqchat;

import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * Created by Petr on 16.07.2017.
 */

public class UserProfile {

    public String nick;
    public String name;
    public String surname;
    public String email;
    public String password;
    public String token;
    public String ID;

    public ArrayList<UserProfile> userProfiles = new ArrayList<UserProfile>();

    public void addUser (String email,String password, String token, String ID){
        UserProfile loginProfile = new UserProfile();
        loginProfile.email = email;
        loginProfile.password = password;
        loginProfile.token = token;
        loginProfile.ID = ID;
        userProfiles.add(loginProfile);
    }
    public void addUserReg(String nick, String name, String surname, String email, String password, String token, String ID){
        UserProfile newProfile = new UserProfile();
        newProfile.name=name;
        newProfile.nick=nick;
        newProfile.surname = surname;
        newProfile.email = email;
        newProfile.password=password;
        newProfile.token = token;
        newProfile.ID = ID;

        userProfiles.add(newProfile);
    }


    public String getToken(UserProfile user){
        for(int i=0;i<userProfiles.size();i++){
            return userProfiles.get(i).token;
        }
        return "";
    }

}
