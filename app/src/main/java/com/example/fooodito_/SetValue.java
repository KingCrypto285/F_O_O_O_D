package com.example.fooodito_;

public class SetValue
{
    private String SetValueID;
    private Double SetValue;

    public SetValue()
    {
    }

    public SetValue(String setValueID, Double setValue) {
        SetValueID = setValueID;
        SetValue = setValue;
    }

    public String getSetValueID() {
        return SetValueID;
    }

    public void setSetValueID(String setValueID) {
        SetValueID = setValueID;
    }

    public Double getSetValue() {
        return SetValue;
    }

    public void setSetValue(Double setValue) {
        SetValue = setValue;
    }
}
