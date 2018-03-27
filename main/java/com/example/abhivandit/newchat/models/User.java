package com.example.abhivandit.newchat.models;

/**
 * Created by delaroy on 4/13/17.
 */
public class User {
    public String uid;
    public String email;
    public String firebaseToken;
    public String count;

    public User(){

    }

    public User(String uid, String email, String firebaseToken){
        this.uid = uid;
        this.email = email;
        this.firebaseToken = firebaseToken;
        count="1";
    }
}
