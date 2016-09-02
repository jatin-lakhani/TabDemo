package com.tabdemo.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by harry on 9/2/16.
 */
public class ContactSResp implements Serializable {


    @SerializedName("getcontactsResult")
    public GetContactResult getcontactsResult;

    public class GetContactResult implements Serializable {
        @SerializedName("TotalContacts")
        public int TotalContacts;

        @SerializedName("data")
        public List<ContactInfo> listContactInfo;

        @SerializedName("message")
        public String message;

        @SerializedName("status")
        public int status;

    }
}
