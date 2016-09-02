package com.tabdemo.models;

public class ContactInfo
{
    private String local_number;

    private String international_calling_code;

    private String valid;

    private String location;

    private String gmt_zone;

    private String caption;

    private String hasOurApp;

    private String country_code;

    private String imagebase64;

    private String flagImgBase64;

    private String isLineShared;

    private String international_number;

    private String ID;

    private String is_mobile;

    private String isCallFree;

    public String getLocal_number ()
    {
        return local_number;
    }

    public void setLocal_number (String local_number)
    {
        this.local_number = local_number;
    }

    public String getInternational_calling_code ()
    {
        return international_calling_code;
    }

    public void setInternational_calling_code (String international_calling_code)
    {
        this.international_calling_code = international_calling_code;
    }

    public String getValid ()
    {
        return valid;
    }

    public void setValid (String valid)
    {
        this.valid = valid;
    }

    public String getLocation ()
    {
        return location;
    }

    public void setLocation (String location)
    {
        this.location = location;
    }

    public String getGmt_zone ()
    {
        return gmt_zone;
    }

    public void setGmt_zone (String gmt_zone)
    {
        this.gmt_zone = gmt_zone;
    }

    public String getCaption ()
    {
        return caption;
    }

    public void setCaption (String caption)
    {
        this.caption = caption;
    }

    public String getHasOurApp ()
    {
        return hasOurApp;
    }

    public void setHasOurApp (String hasOurApp)
    {
        this.hasOurApp = hasOurApp;
    }

    public String getCountry_code ()
    {
        return country_code;
    }

    public void setCountry_code (String country_code)
    {
        this.country_code = country_code;
    }

    public String getImagebase64 ()
    {
        return imagebase64;
    }

    public void setImagebase64 (String imagebase64)
    {
        this.imagebase64 = imagebase64;
    }

    public String getFlagImgBase64 ()
    {
        return flagImgBase64;
    }

    public void setFlagImgBase64 (String flagImgBase64)
    {
        this.flagImgBase64 = flagImgBase64;
    }

    public String getIsLineShared ()
    {
        return isLineShared;
    }

    public void setIsLineShared (String isLineShared)
    {
        this.isLineShared = isLineShared;
    }

    public String getInternational_number ()
    {
        return international_number;
    }

    public void setInternational_number (String international_number)
    {
        this.international_number = international_number;
    }

    public String getID ()
    {
        return ID;
    }

    public void setID (String ID)
    {
        this.ID = ID;
    }

    public String getIs_mobile ()
    {
        return is_mobile;
    }

    public void setIs_mobile (String is_mobile)
    {
        this.is_mobile = is_mobile;
    }

    public String getIsCallFree ()
    {
        return isCallFree;
    }

    public void setIsCallFree (String isCallFree)
    {
        this.isCallFree = isCallFree;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [local_number = "+local_number+", international_calling_code = "+international_calling_code+", valid = "+valid+", location = "+location+", gmt_zone = "+gmt_zone+", caption = "+caption+", hasOurApp = "+hasOurApp+", country_code = "+country_code+", imagebase64 = "+imagebase64+", flagImgBase64 = "+flagImgBase64+", isLineShared = "+isLineShared+", international_number = "+international_number+", ID = "+ID+", is_mobile = "+is_mobile+", isCallFree = "+isCallFree+"]";
    }
}