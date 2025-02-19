package com.example.myapplication.Entity;

import android.widget.Button;
import android.widget.EditText;

public class Users {
    public int id;
    public String username;
    public String email;
    public String password;
    public String dob;

    public Users(String username, String email, String password, String dob) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.dob = dob;
    }

    public Users(int id, String username, String email, String password, String dob) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.dob = dob;
    }
}
