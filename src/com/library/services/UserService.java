package com.library.services;

import com.library.interfaces.UserServiceInterface;
import com.library.models.User;

import java.util.HashMap;
import java.util.Map;

public class UserService implements UserServiceInterface {
    private Map<String, User> users = new HashMap<>();

    @Override
    public void registerUser(String username, String password) {
        if (users.containsKey(username)) {
            System.out.println("Username already exists!");
        } else {
            users.put(username, new User(username, password, false));
            System.out.println("User registered successfully!");
        }
    }

    @Override
    public User loginUser(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            System.out.println("Login successful!");
            return user;
        } else {
            System.out.println("Invalid username or password!");
            return null;
        }
    }
}
