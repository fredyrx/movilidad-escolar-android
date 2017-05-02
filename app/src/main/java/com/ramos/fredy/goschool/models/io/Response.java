package com.ramos.fredy.goschool.models.io;

import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.google.gson.annotations.SerializedName;
import com.ramos.fredy.goschool.models.Client;

/**
 * Created by sistemas on 02/05/2017.
 */

public class Response {

    private long timestamp;
    private String exception;
    private String path;
    private int status;
    private String error;
    private String message;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    Response(){}

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
