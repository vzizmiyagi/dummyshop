package com.paupuri.dummyshop.model;

import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aziz on 2/1/17.
 */



@ParseClassName("Product")
public class Product extends ParseObject {

    public Product() {
    }

    public String getName() {
        return getString("name");
    }

    public void setName(String name) {
        put("name", name);
    }

    public String getDescription() {
        return getString("description");
    }

    public void setDescription(String description) {
        put("description", description);
    }

    public String getPrice() {
        return getString("price");
    }

    public void setPrice(String price) {
        put("price", price);
    }

    public String getCity() {
        return getString("city");
    }

    public void setCity(String city) {
        put("city", city);
    }


    public ParseUser getSeller() {
        return getParseUser("seller");
    }

    public void setSeller(ParseUser user) {
        put("seller", user);
    }

    public String getSellerUsername() {
        String username = "";
        try {
            username = getSeller().fetchIfNeeded().getUsername();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return username;
    }

    public List<ParseFile> getPhotos() {
        return getList("photos");
    }

    public void setPhotos(ArrayList<ParseFile> images) {
        addAll("photos", images);
    }

    public ParseFile getThumbnail() {
        return getPhotos().get(0);
    }
}