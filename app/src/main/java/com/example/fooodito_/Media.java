package com.example.fooodito_;

public class Media
{
    private String Media_ID;
    private String URL;
    private String TypeID;

    public Media(String media_ID, String URL, String typeID)
    {
        Media_ID = media_ID;
        this.URL = URL;
        TypeID = typeID;
    }

    public String getMedia_ID() {
        return Media_ID;
    }

    public void setMedia_ID(String media_ID) {
        Media_ID = media_ID;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getTypeID() {
        return TypeID;
    }

    public void setTypeID(String typeID) {
        TypeID = typeID;
    }
}
