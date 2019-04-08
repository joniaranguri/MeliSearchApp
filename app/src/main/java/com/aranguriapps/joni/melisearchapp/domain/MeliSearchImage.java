package com.aranguriapps.joni.melisearchapp.domain;

import android.os.Parcel;
import android.os.Parcelable;

import com.aranguriapps.joni.melisearchapp.io.api.Constants;
import com.google.gson.annotations.SerializedName;

public class MeliSearchImage implements Parcelable {
    public static final Parcelable.Creator<MeliSearchImage> CREATOR = new Parcelable.Creator<MeliSearchImage>() {
        public MeliSearchImage createFromParcel(Parcel in) {
            return new MeliSearchImage(in);
        }

        @Override
        public MeliSearchImage[] newArray(int i) {
            return new MeliSearchImage[i];
        }


    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @SerializedName("id")
    String id;


    @SerializedName(Constants.URL)
    String url;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }

    private MeliSearchImage(Parcel in) {
        id = in.readString();
        url = in.readString();
    }
}
