package com.ramos.fredy.goschool.models.io;

import com.google.gson.annotations.SerializedName;
import com.ramos.fredy.goschool.models.School;

import java.util.List;

/**
 * Created by boticas on 02/05/17.
 */

public class SchoolResponse {

    @SerializedName("data")
    private List<School> lstSchool;

    public List<School> getLstSchool() {
        return lstSchool;
    }

    public void setLstSchool(List<School> lstSchool) {
        this.lstSchool = lstSchool;
    }
}
