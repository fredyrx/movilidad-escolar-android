package com.ramos.fredy.goschool;

import android.app.Application;

import com.ramos.fredy.goschool.api.ApiManager;

/**
 * Created by fredy on 30/04/17.
 */

public class App extends Application {

    private static App instance;

    public App(){
        instance = this;
    }

    @Override
    public void onCreate(){
        super.onCreate();
    }

    public static App getInstance(){
        return instance;
    }

}
