package com.example.fooodito_;

public class GenderClass
{
    private String US_ID;
    private String Gender;

    public GenderClass(String US_ID, String gender)
    {
        this.US_ID = US_ID;
        Gender = gender;
    }

    public String getUS_ID() {
        return US_ID;
    }

    public void setUS_ID(String US_ID) {
        this.US_ID = US_ID;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }
}
