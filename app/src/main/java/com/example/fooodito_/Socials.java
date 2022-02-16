package com.example.fooodito_;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Socials extends AppCompatActivity
{
    TextView EVENT,FRIENDS,MESSAGES,FEEDS;
    ImageView Feed_imageVW;
    ConstraintLayout option_layout1,option_layout2,option_layout3,option_layout4,option_layout5;
    optionValues Optvals;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socials);

        intialize();

        option_layout2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                option_layout2.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.option_background_left));
                option_layout1.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.bottom_options));
            }
        });
    }

    public void intialize()
    {
        //option_layout1,option_layout2,option_layout3,option_layout4,option_layout5;
        /*MESSAGES = findViewById(R.id.message_oopt_txtview);
        EVENT = findViewById(R.id.Events_opts_txtvew);
        FRIENDS = findViewById(R.id.friends_opt_txtview);*/
        //Optvals = new optionValues("");

        option_layout1 = findViewById(R.id.layout_1);
        option_layout2 = findViewById(R.id.layout_2);
        option_layout3 = findViewById(R.id.layout_3);
        option_layout4 = findViewById(R.id.layout_4);
        option_layout5 = findViewById(R.id.layout5);

        Feed_imageVW = findViewById(R.id.Feed_imageview);
        FEEDS = findViewById(R.id.feeds_textview);

    }

    public void BACK(View view)
    {
        startActivity(new Intent(Socials.this,sidemenu.class));
    }


    public void goto_optionmenu(View view)
    {
    }

    public void select_option(View view)
    {
        //Feed_imageVW.setBackground(R.id.);
    }
}