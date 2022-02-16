package com.example.fooodito_;

public class Type
{
    private String TypeID;
    private String Type;

    public Type(String typeID, String type)
    {
        TypeID = typeID;
        Type = type;
    }

    public String getTypeID() {
        return TypeID;
    }

    public void setTypeID(String typeID) {
        TypeID = typeID;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}
