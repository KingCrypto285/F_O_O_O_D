package com.example.fooodito_;

public class Exercises
{
    private String ExerciseID;
    private String Exercise;

    public Exercises()
    {
    }

    public Exercises(String exerciseID, String exercise) {
        ExerciseID = exerciseID;
        Exercise = exercise;
    }

    public String getExerciseID() {
        return ExerciseID;
    }

    public void setExerciseID(String exerciseID) {
        ExerciseID = exerciseID;
    }

    public String getExercise() {
        return Exercise;
    }

    public void setExercise(String exercise) {
        Exercise = exercise;
    }
}
