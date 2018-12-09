package com.eslamshawky.hp.makeupartist.ModelsServiceProvider;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SessionModel {
    private SharedPreferences preferences;
    public SessionModel (Context cntx){
        preferences = PreferenceManager.getDefaultSharedPreferences(cntx);
        }
        public void setUserName(String userName){
        preferences.edit().putString("username",userName).commit();
        }
        public String getUserName(){
        String userName = preferences.getString("username","");
        return userName;
        }
        public void setPassword(String password){
        preferences.edit().putString("password",password).commit();
        }
        public String getPassword(){
        String password = preferences.getString("password","");
        return password;
        }
}
