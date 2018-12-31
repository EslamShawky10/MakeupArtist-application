package com.eslamshawky.hp.makeupartist.ModelsServiceProvider;

import android.widget.EditText;

import com.google.gson.annotations.SerializedName;

public class EditProviderModel {
    @SerializedName("FULL_NAME_id")
    private String fullName;
    @SerializedName("EMAIL_id")
    private String email;
    @SerializedName("PASSWORD_id")
    private String password;
    @SerializedName("TRADE_NAME_id")
    private String tradeName;
    @SerializedName("ADDRESS_id")
    private String address;
    @SerializedName("ABOUT_id")
    private String about;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

}
