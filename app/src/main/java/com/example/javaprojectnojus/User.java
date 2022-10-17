package com.example.javaprojectnojus;

import android.content.Context;
import android.content.SharedPreferences;

public class User {
    private String usernameLogin, usernameRegister, password, email;
    private static final String PREFERENCES_FILE_NAME = "com.example.javaprojectnojus";
    private static final String USERNAME_KEY = "username";
    private static final String PASSWORD_KEY = "password";
    private static final String REMEMBER_ME_KEY = "rememberMe";
    private SharedPreferences sharedPrefs;

    public User(String usernameRegister, String password, String email){
        this.usernameRegister = usernameRegister;
        this.password = password;
        this.email = email;

    }

    //using in login screen
    public User(Context context){
        this.sharedPrefs = context.getSharedPreferences(User.PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
    }

    public String getUsernameForRegistration(){
        return this.usernameRegister;
    }

    public String getUsernameForLogin(){
        return this.sharedPrefs.getString(USERNAME_KEY, "");
    }

    public void setUsernameForRegistration(String usernameRegister){
        this.usernameRegister = usernameRegister;
    }

    public void setUsernameForLogin(String usernameLogin){
        this.sharedPrefs.edit().putString(USERNAME_KEY, usernameLogin).commit();
    }

    public String getPasswordForLogin(){
        return this.sharedPrefs.getString(PASSWORD_KEY, "");
    }

    public void  setPasswordForLogin(){
        this.sharedPrefs.edit().putString(PASSWORD_KEY, password).commit();
    }

    public String getPasswordForRegistration(){
        return this.password;
    }

    public void setPasswordForRegistration(String password){
        this.password = password;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public boolean isRemembered(){
        return this.sharedPrefs.getBoolean(REMEMBER_ME_KEY, false);
    }

    public void setRemembered(Boolean remembered){
        this.sharedPrefs.edit().putBoolean(REMEMBER_ME_KEY, remembered).commit();
    }
}
