package com.example.fooodito_;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Foods extends AppCompatActivity
{
    List<foodJournalItem> FJ_List;
    Calendar myCalendar;
    foods_tabAdapter Food_Adapter;
    TextView Todays_Date;
    EditText QTY_text, foodItem_text;
    ConstraintLayout SIDEMENU,Item_Box;
    RecyclerView Journal_RecyclerView;
    FirebaseFirestore Fstore;
    FirebaseAuth FA;
    DatePickerDialog.OnDateSetListener Mpicker;
    private int hiddenValue = 0;
    private int hidden_itmBox_Value = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foods);

        intialize();
        int Year = myCalendar.get(Calendar.YEAR);
        int month = myCalendar.get(Calendar.MONTH + 1);
        int day = myCalendar.get(Calendar.DAY_OF_MONTH);

        Todays_Date.setOnClickListener(new View.OnClickListener()
                                       {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v)
            {


                DatePickerDialog dia = new DatePickerDialog(Foods.this, new DatePickerDialog.OnDateSetListener()
                {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
                    {
                        month = month +1;
                        Date D = new Date(month+"/"+dayOfMonth+"/"+year);
                        SimpleDateFormat sdf = new SimpleDateFormat("EEE d, MMM");
                        String time1 = sdf.format(D);
                        Todays_Date.setText(time1);
                    }
                },Year,month,day);
                dia.show();
                load_recycler(Todays_Date.getText().toString().trim());

            }
        }
        );




    }

    public void BACK(View view)
    {
        startActivity(new Intent(Foods.this,sidemenu.class));
    }

    public void intialize()
    {
        String date = new SimpleDateFormat("EEE d, MMM", Locale.getDefault()).format(new Date());



        QTY_text = findViewById(R.id.qty_input);
        foodItem_text = findViewById(R.id.food_itm_input);
        SIDEMENU = findViewById(R.id.FoodJournal_sidemenu);
        Item_Box = findViewById(R.id.Food_itemBox);
        Item_Box.setVisibility(View.INVISIBLE);
        SIDEMENU.setVisibility(View.INVISIBLE);

        Journal_RecyclerView = findViewById(R.id.today_foodjournal);
        Todays_Date = findViewById(R.id.today_Date);

        myCalendar = Calendar.getInstance();
        Fstore = FirebaseFirestore.getInstance();

        Todays_Date.setText(date);

        FJ_List = new ArrayList<>();
        Food_Adapter = new foods_tabAdapter(this,FJ_List);

        Journal_RecyclerView.setAdapter(Food_Adapter);
        Journal_RecyclerView.setHasFixedSize(true);

        LinearLayoutManager Mlayout = new LinearLayoutManager(this);

        Mlayout.setReverseLayout(true);
        Mlayout.setStackFromEnd(true);
        Journal_RecyclerView.setLayoutManager(Mlayout);

        Journal_RecyclerView.scrollToPosition(Food_Adapter.getItemCount());
        load_recycler(date);

    }

    public void goto_optionmenu(View view)
    {
        if(hiddenValue == 0)
        {
            SIDEMENU.setVisibility(View.VISIBLE);
            hiddenValue = 1;
        }
        else
        {

            SIDEMENU.setVisibility(View.INVISIBLE);
            hiddenValue = 0;
        }
    }

    public void load_recycler(String today)
    {
        FJ_List.clear();
        Food_Adapter.notifyDataSetChanged();

        Fstore.collection("foodJournalItem").orderBy("time_at", Query.Direction.ASCENDING).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>()
        {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots)
            {
                if (!queryDocumentSnapshots.isEmpty())
                {
                    List<DocumentSnapshot> Dlist = queryDocumentSnapshots.getDocuments();
                    for (DocumentSnapshot d : Dlist)
                    {

                        foodJournalItem FJ = d.toObject(foodJournalItem.class);
                        String ID = FJ.getUser_ID();
                        String UID = User_id();
                        Date Fdate = FJ.getTime_at();
                        String D = FJ.Day_date(Fdate);


                        if(ID.equals(UID))
                        {
                            if(D.equals(today))
                            {
                                FJ_List.add(FJ);
                            }

                        }


                    }
                    Food_Adapter.notifyDataSetChanged();

                }
                else
                {
                }
            }
        }
        ).addOnFailureListener(new OnFailureListener()
        {
            @Override
            public void onFailure(@NonNull Exception e)
            {
                Toast.makeText(getApplicationContext(), "NOTHING", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public String User_id()
    {
        FA = FirebaseAuth.getInstance();
        String ID = FA.getCurrentUser().getUid();

        return ID;
    }

    public void add_item(View view)
    {
        if(hidden_itmBox_Value == 1)
        {
            Item_Box.setVisibility(View.VISIBLE);
            hidden_itmBox_Value = 0;
        }
        else
        {

            Item_Box.setVisibility(View.INVISIBLE);
            hidden_itmBox_Value = 1;
        }
    }

    public void remove_box(View view)
    {
        if(hidden_itmBox_Value == 0)
        {
            Item_Box.setVisibility(View.VISIBLE);
            hidden_itmBox_Value = 1;
        }
        else
        {

            Item_Box.setVisibility(View.INVISIBLE);
            hidden_itmBox_Value = 0;
        }
    }

    public void add_foodItem(View view)
    {
        if((TextUtils.isEmpty(QTY_text.getText().toString())) || (TextUtils.isEmpty(foodItem_text.getText().toString())) )
        {
            Toast.makeText(getApplicationContext(), "Please add Quantity and food item", Toast.LENGTH_SHORT).show();
        }
        else
        {

            int QTY = Integer.parseInt(QTY_text.getText().toString());
            String FOOD = foodItem_text.getText().toString();

            Map<String, Object> map = new HashMap<>();
            map.put("user_id", User_id());
            map.put("QTY",QTY);
            map.put("time_at", FieldValue.serverTimestamp());
            map.put("Food_Item", FOOD);


            Fstore.collection("foodJournalItem").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>()
                                                                               {
                @Override
                public void onSuccess(DocumentReference documentReference)
                {
                    startActivity(new Intent(Foods.this, sidemenu.class));
                }
            }
            );
        }



        Item_Box.setVisibility(View.INVISIBLE);
        hidden_itmBox_Value = 1;


    }


}