package com.example.anson_tu__100655482__mobile_final_assignment;

import android.app.Application;

public class User extends Application {
    public String currentUser;

    public String getCurrentUser() {
        return this.currentUser;
    }

    public void setUsername(String username) {
        this.currentUser = username;
    }
}
