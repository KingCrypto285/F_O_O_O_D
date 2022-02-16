package com.example.fooodito_;

import java.util.Date;

public class ImageClass
{

    private String ImageID;
    private String TypeID;
    private String Image_URL;
    private Date created_Log;

    public ImageClass(String imageID, String typeID, String image_URL, Date created_Log)
    {
        ImageID = imageID;
        TypeID = typeID;
        Image_URL = image_URL;
        this.created_Log = created_Log;
    }

    public String getImageID() {
        return ImageID;
    }

    public void setImageID(String imageID) {
        ImageID = imageID;
    }

    public String getTypeID() {
        return TypeID;
    }

    public void setTypeID(String typeID) {
        TypeID = typeID;
    }

    public String getImage_URL() {
        return Image_URL;
    }

    public void setImage_URL(String image_URL) {
        Image_URL = image_URL;
    }

    public Date getCreated_Log() {
        return created_Log;
    }

    public void setCreated_Log(Date created_Log) {
        this.created_Log = created_Log;
    }
}
