// User.java
package com.library.models;

import com.library.interfaces.ProgressObserver;

public class User implements ProgressObserver{
    private String username;
    private String password;
    private boolean isAdmin;


    public User(String username, String password, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }


    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.isAdmin = false; // Default to non-admin
    }


    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
    @Override
    public void update(String message) {
        System.out.println(username + " received progress update: " + message);
    }

}
