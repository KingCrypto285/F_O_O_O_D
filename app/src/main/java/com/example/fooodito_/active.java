package com.example.fooodito_;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.protobuf.Empty;

import java.util.ArrayList;
import java.util.List;

public class active extends AppCompatActivity
{
    RecyclerView ExerciseSet_RVW;
    TextView Empty_TXTVW;
    DatabaseReference reference;
    List<ExerciseGroup> EXEGROUP_List;
    ExerciseGroup_Adapter EG_Adapter;
    FirebaseAuth FA;
    FirebaseFirestore Fstore;
    ConstraintLayout SideMenu;
    private int hiddenValue = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active);

        intialize();
    }

    public void intialize()
    {
        SideMenu = findViewById(R.id.Side_Menu);
        ExerciseSet_RVW = findViewById(R.id.daily_exercises);
        Empty_TXTVW = findViewById(R.id.empty_message);
        Fstore = FirebaseFirestore.getInstance();
        Empty_TXTVW.setVisibility(View.INVISIBLE);
        SideMenu.setVisibility(View.INVISIBLE);

        EXEGROUP_List = new ArrayList<>();
        EG_Adapter = new ExerciseGroup_Adapter(this,EXEGROUP_List);
        ExerciseSet_RVW.setAdapter(EG_Adapter);
        ExerciseSet_RVW.setHasFixedSize(true);
        LinearLayoutManager Mlayout = new LinearLayoutManager(this);
        Mlayout.setReverseLayout(true);
        Mlayout.setStackFromEnd(true);
        ExerciseSet_RVW.setLayoutManager(Mlayout);

        ExerciseSet_RVW.scrollToPosition(EG_Adapter.getItemCount());

        reference = (DatabaseReference) FirebaseDatabase.getInstance().getReference("ExerciseGroup");

        load_recycler();

    }

    public void load_recycler()
    {
        Fstore.collection("ExerciseGroup").orderBy("order").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>()
        {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots)
            {
                if(!queryDocumentSnapshots.isEmpty())
                {
                    List<DocumentSnapshot> Dlist = queryDocumentSnapshots.getDocuments();
                    for(DocumentSnapshot d : Dlist) {
                        ExerciseGroup EXG = d.toObject(ExerciseGroup.class);
                        if(EXG.getUserID() == User_id())
                        {
                            EXEGROUP_List.add(EXG);
                        }

                    }
                    EG_Adapter.notifyDataSetChanged();
                    if(EXEGROUP_List.isEmpty())
                    {
                        Empty_TXTVW.setVisibility(View.VISIBLE);
                        Empty_TXTVW.setText("You have no exercise sets");

                    }
                }
                else
                {
                }
            }
        }
        );

    }

    public void to_sidemenu(View view)
    {
        if(hiddenValue == 0)
        {
            SideMenu.setVisibility(View.VISIBLE);
            hiddenValue = 1;
        }
        else
        {

            SideMenu.setVisibility(View.INVISIBLE);
            hiddenValue = 0;
        }
    }

    public void BACK(View view)
    {
        startActivity(new Intent(active.this,sidemenu.class));
    }

    public String User_id()
    {
        FA = FirebaseAuth.getInstance();
        String ID = FA.getCurrentUser().getUid();

        return ID;
    }

    public void EnterExercise(View view)
    {

    }
}