package com.example.fooodito_;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.google.firebase.firestore.FirebaseFirestore;

public class add_exercise extends AppCompatActivity
{
    FirebaseFirestore Fstore;
    EditText exercise_input;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);
    }

    public void intialize()
    {


    }
}