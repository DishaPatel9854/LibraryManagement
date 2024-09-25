package com.library.interfaces;

import com.library.models.User;

public interface UserServiceInterface {
    void registerUser(String username, String password);
    User loginUser(String username, String password);
}
