package com.ramos.fredy.goschool.models.io;

import com.google.gson.annotations.SerializedName;
import com.ramos.fredy.goschool.models.User;

/**
 * Created by kenok on 30/04/2017.
 */

public class LoginResponse {

    private int status;

    @SerializedName("data")
    private Object user;

    private String error;
    private String message;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getUser() {
        return user;
    }

    public void setUser(Object user) {
        this.user = user;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
