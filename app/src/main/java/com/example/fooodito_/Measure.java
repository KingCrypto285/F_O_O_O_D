package com.example.fooodito_;

public class Measure
{
    private String MeasureID;
    private String Measurement;

    public Measure()
    {
    }

    public Measure(String measureID, String measurement)
    {
        MeasureID = measureID;
        Measurement = measurement;
    }

    public String getMeasureID() {
        return MeasureID;
    }

    public void setMeasureID(String measureID) {
        MeasureID = measureID;
    }

    public String getMeasurement() {
        return Measurement;
    }

    public void setMeasurement(String measurement) {
        Measurement = measurement;
    }
}
