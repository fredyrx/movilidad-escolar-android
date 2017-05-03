package com.ramos.fredy.goschool.models.io;

import com.google.gson.annotations.SerializedName;
import com.ramos.fredy.goschool.models.Dependent;

/**
 * Created by sistemas on 02/05/2017.
 */

public class DependentResponse extends Response {

    @SerializedName("data")
    private Dependent dependent;

    DependentResponse(){
        super();
    }

    public Dependent getDependent() {
        return dependent;
    }

    public void setDependent(Dependent dependent) {
        this.dependent = dependent;
    }
}
