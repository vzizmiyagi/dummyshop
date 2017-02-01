package com.paupuri.dummyshop;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.paupuri.dummyshop.model.Product;
import com.paupuri.dummyshop.model.User;

/**
 * Created by aziz on 1/23/17.
 */

public class DummyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        ParseUser.registerSubclass(User.class);
        ParseObject.registerSubclass(Product.class);

        // Add your initialization code here
        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                .applicationId("API_KEY")
                .clientKey("CLIENT_KEY")
                .server("SERVER_ADDRESS")
                .build()
        );


        //ParseUser.enableAutomaticUser();

        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);
        defaultACL.setPublicWriteAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);

    }

}
