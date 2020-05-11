package com.example.covid19.models.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Timestamp;

public class State implements Parcelable {
    /*{"OBJECTID":294,
    "Province_State":"Colorado",
    "Country_Region":"US",
    "Last_Update":1588984355000,
    "Lat":39.059811,"Long_":-105.311104,
    "Confirmed":18827,
    "Recovered":0,
    "Deaths":960,
    "Active":17867,
    "Admin2":null,
    "FIPS":"08",
    "Combined_Key":"Colorado, US"}}*/

    int OBJECTID;
    String Province_State;
    String Country_Region;
    Long Last_Update;
    String Lat;
    String Long_;
    int Confirmed;
    int Recovered;
    int Deaths;
    int Active;
    String Admin2;
    String FIPS;
    String Combined_Key;

    protected State(Parcel in) {
        OBJECTID = in.readInt();
        Province_State = in.readString();
        Country_Region = in.readString();
        Last_Update = in.readLong();
        Lat = in.readString();
        Long_ = in.readString();
        Confirmed = in.readInt();
        Recovered = in.readInt();
        Deaths= in.readInt();
        Active = in.readInt();
        Admin2 = in.readString();
        FIPS = in.readString();
        Combined_Key = in.readString();
    }

    public static final Creator<State> CREATOR = new Creator<State>() {
        @Override
        public State createFromParcel(Parcel in) {
            return new State(in);
        }

        @Override
        public State[] newArray(int size) {
            return new State[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(OBJECTID);
        parcel.writeString(Province_State);
        parcel.writeString(Country_Region);
        parcel.writeLong(Last_Update);
        parcel.writeString(Lat);
        parcel.writeString(Long_);
        parcel.writeInt(Confirmed);
        parcel.writeInt(Recovered);
        parcel.writeInt(Deaths);
        parcel.writeInt(Active);
        parcel.writeString(Admin2);
        parcel.writeString(FIPS);
        parcel.writeString(Combined_Key);
    }

    public int getOBJECTID() {
        return OBJECTID;
    }

    public void setOBJECTID(int OBJECTID) {
        this.OBJECTID = OBJECTID;
    }

    public String getProvince_State() {
        return Province_State;
    }

    public void setProvince_State(String province_State) {
        Province_State = province_State;
    }

    public String getCountry_Region() {
        return Country_Region;
    }

    public void setCountry_Region(String country_Region) {
        Country_Region = country_Region;
    }

    public Long getLast_Update() {
        return Last_Update;
    }

    public void setLast_Update(Long last_Update) {
        Last_Update = last_Update;
    }

    public String getLat() {
        return Lat;
    }

    public void setLat(String lat) {
        Lat = lat;
    }

    public String getLong_() {
        return Long_;
    }

    public void setLong_(String long_) {
        Long_ = long_;
    }

    public int getConfirmed() {
        return Confirmed;
    }

    public void setConfirmed(int confirmed) {
        Confirmed = confirmed;
    }

    public int getRecovered() {
        return Recovered;
    }

    public void setRecovered(int recovered) {
        Recovered = recovered;
    }

    public int getDeaths() {
        return Deaths;
    }

    public void setDeaths(int deaths) {
        Deaths = deaths;
    }

    public int getActive() {
        return Active;
    }

    public void setActive(int active) {
        Active = active;
    }

    public String getAdmin2() {
        return Admin2;
    }

    public void setAdmin2(String admin2) {
        Admin2 = admin2;
    }

    public String getFIPS() {
        return FIPS;
    }

    public void setFIPS(String FIPS) {
        this.FIPS = FIPS;
    }

    public String getCombined_Key() {
        return Combined_Key;
    }

    public void setCombined_Key(String combined_Key) {
        Combined_Key = combined_Key;
    }


}
