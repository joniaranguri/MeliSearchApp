package com.aranguriapps.joni.melisearchapp.io.model;

import com.aranguriapps.joni.melisearchapp.io.api.Constants;
import com.google.gson.annotations.SerializedName;

public class ItemDescription {
    @SerializedName(Constants.PLAIN_TEXT)
    private String descripcion;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
