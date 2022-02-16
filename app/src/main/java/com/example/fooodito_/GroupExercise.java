package com.example.fooodito_;

public class GroupExercise
{
    private String ExerciseID;
    private String GroupID;
    private String SetValueID;
    private String MeasureID;


    public GroupExercise()
    {
    }

    public GroupExercise(String exerciseID, String groupID, String setValueID, String measureID) {
        ExerciseID = exerciseID;
        GroupID = groupID;
        SetValueID = setValueID;
        MeasureID = measureID;
    }

    public String getExerciseID() {
        return ExerciseID;
    }

    public void setExerciseID(String exerciseID) {
        ExerciseID = exerciseID;
    }

    public String getGroupID() {
        return GroupID;
    }

    public void setGroupID(String groupID) {
        GroupID = groupID;
    }

    public String getSetValueID() {
        return SetValueID;
    }

    public void setSetValueID(String setValueID) {
        SetValueID = setValueID;
    }

    public String getMeasureID() {
        return MeasureID;
    }

    public void setMeasureID(String measureID) {
        MeasureID = measureID;
    }
}
