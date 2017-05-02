package com.ramos.fredy.goschool.models.io;

import com.google.gson.annotations.SerializedName;
import com.ramos.fredy.goschool.models.Client;


/**
 * Created by sistemas on 02/05/2017.
 */

public class ClientResponse extends Response {

    public ClientResponse(){
        super();
    }

    @SerializedName("data")
    Client client;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
