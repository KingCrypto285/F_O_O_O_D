package com.example.fooodito_;

public class usermedia
{
    private String User_ID;
    private String Media_ID;

    public usermedia(String user_ID, String media_ID)
    {
        User_ID = user_ID;
        Media_ID = media_ID;
    }

    public String getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(String user_ID) {
        User_ID = user_ID;
    }

    public String getMedia_ID() {
        return Media_ID;
    }

    public void setMedia_ID(String media_ID) {
        Media_ID = media_ID;
    }
}
