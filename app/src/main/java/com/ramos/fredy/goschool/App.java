package com.ramos.fredy.goschool;

import android.app.Application;

import com.ramos.fredy.goschool.models.Client;
import com.ramos.fredy.goschool.models.Driver;

/**
 * Created by fredy on 30/04/17.
 */

public class App extends Application {

    private static App instance;

    private Client clientUser;
    private Driver  driverUser;

    public Client getClientUser() {
        return clientUser;
    }

    public void setClientUser(Client clientUser) {
        this.clientUser = clientUser;
    }

    public Driver getDriverUser() {
        return driverUser;
    }

    public void setDriverUser(Driver driverUser) {
        this.driverUser = driverUser;
    }

    public Object getUser(){
        if (clientUser != null){
            return clientUser;
        }else{
            return driverUser;
        }
    }

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
