package com.example.fooodito_;

public class UserImage
{
    private String user_ID;
    private String Image_ID;

    public UserImage(String user_ID, String image_ID)
    {
        this.user_ID = user_ID;
        Image_ID = image_ID;
    }

    public String getUser_ID() {
        return user_ID;
    }

    public void setUser_ID(String user_ID) {
        this.user_ID = user_ID;
    }

    public String getImage_ID() {
        return Image_ID;
    }

    public void setImage_ID(String image_ID) {
        Image_ID = image_ID;
    }
}
