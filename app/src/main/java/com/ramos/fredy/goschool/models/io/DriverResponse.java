package com.ramos.fredy.goschool.models.io;

import com.google.gson.annotations.SerializedName;
import com.ramos.fredy.goschool.models.Driver;

/**
 * Created by sistemas on 02/05/2017.
 */

public class DriverResponse extends Response {

    @SerializedName("data")
    private Driver driver;

    public DriverResponse() {
        super();
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}
