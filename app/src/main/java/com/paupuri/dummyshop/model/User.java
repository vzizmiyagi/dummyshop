package com.paupuri.dummyshop.model;

import com.parse.ParseClassName;
import com.parse.ParseUser;

/**
 * Created by aziz on 1/23/17.
 */

@ParseClassName("User")
public class User extends ParseUser {

    public User() {
        // A default constructor is required.
    }

    public String getName() {
        return getString("name");
    }

    public void setName(String name) {
        put("name", name);
    }

    public String getPassword() {
        return getString("password");
    }

    public void setPassword(String password) {
        put("password", password);
    }

    public String getUsername() {
        return getString("username");
    }

    public void setUsername(String username) {
        put("username", username);
    }

    public String getCity() {
        return getString("city");
    }

    public void setCity(String city) {
        put("city", city);
    }

}