package com.aranguriapps.joni.melisearchapp.domain;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ItemSearch {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private  String id;
    private String thumbnail;
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title;
     private String price;

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public ArrayList<MeliSearchImage> getPictures() {
        return pictures;
    }

    public void setPictures(ArrayList<MeliSearchImage> pictures) {
        this.pictures = pictures;
    }

    private ArrayList<MeliSearchImage> pictures;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String description;

    public String getLinkMercadolibre() {
        return linkMercadolibre;
    }

    public void setLinkMercadolibre(String linkMercadolibre) {
        this.linkMercadolibre = linkMercadolibre;
    }

    @SerializedName("permalink")
    private String linkMercadolibre;
}
