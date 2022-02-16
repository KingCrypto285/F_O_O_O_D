package com.example.fooodito_;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SocialOptions extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_options);

        Bundle bundle = getIntent().getExtras();
        String PassedValue = bundle.getString("transfer");


        Intialize();

    }

    public void Intialize()
    {


    }

    public void BACK(View view)
    {
        startActivity(new Intent(SocialOptions.this,Socials.class));
    }
}