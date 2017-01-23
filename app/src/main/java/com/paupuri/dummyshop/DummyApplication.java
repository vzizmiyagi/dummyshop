package com.paupuri.dummyshop;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;

/**
 * Created by aziz on 1/23/17.
 */

public class DummyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        //ParseUser.registerSubclass(User.class);
        //ParseObject.registerSubclass(Product.class);

        // Add your initialization code here
        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                .applicationId("d9102f39980cbb0860b3c0831f7f14ec5e1b0b23")
                .clientKey("8f2618a529b8e561f6c37316929a396f5c89346d")
                .server("http://ec2-54-92-154-243.compute-1.amazonaws.com:80/parse/")
                .build()
        );


        //ParseUser.enableAutomaticUser();

        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);
        defaultACL.setPublicWriteAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);

    }

}