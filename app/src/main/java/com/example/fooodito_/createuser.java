package com.example.fooodito_;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import io.grpc.Server;

public class createuser extends AppCompatActivity
{
    new_User NewUser;
    EditText email_input,password_input,confirmPassword_input,Username_input,DOB_input;
    FirebaseAuth FAuth;
    FirebaseFirestore FFstore;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createuser);

        intialize();
    }

    public void intialize()
    {
        FFstore = FirebaseFirestore.getInstance();
        FAuth = FirebaseAuth.getInstance();
        NewUser = new new_User();
        email_input = findViewById(R.id.newemailInput);
        password_input = findViewById(R.id.newpasswordinput);
        confirmPassword_input = findViewById(R.id.newconfirmpasswordinput);
        Username_input = findViewById(R.id.newusernameinput);
        DOB_input = findViewById(R.id.newdobinput);

    }

    public void BACK(View view)
    {
        startActivity(new Intent(createuser.this,MainActivity.class));
    }

    public void generate_newuser(View view)
    {
        if(TextUtils.isEmpty(email_input.getText().toString())|| TextUtils.isEmpty(DOB_input.getText().toString())
                || TextUtils.isEmpty(password_input.getText().toString()) || TextUtils.isEmpty(Username_input.getText().toString()) ||
            TextUtils.isEmpty(confirmPassword_input.getText().toString()))
        {
            Toast.makeText(createuser.this, "Please fill out all fields!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            try
            {

                Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(DOB_input.getText().toString());

                boolean check_result = NewUser.check_password(password_input.getText().toString().trim());
                boolean compare_passwords = NewUser.equal_passwords(password_input.getText().toString().trim(),confirmPassword_input.getText().toString().trim());


                //new_User NU = new new_User(Username_input.getText().toString().trim(), , confirmPassword_input.getText().toString().trim(),"NULL", date1, String.valueOf(FieldValue.serverTimestamp()));


                createuser_firebaseAuth(email_input.getText().toString().trim(),
                        password_input.getText().toString().trim());






            }
            catch (ParseException e)
            {
                e.printStackTrace();
                Toast.makeText(createuser.this, "Did not work", Toast.LENGTH_SHORT).show();
            }


        }

    }

    public void createuser_firebaseAuth(String EML, String PWD)
    {
        FAuth.createUserWithEmailAndPassword(EML,PWD).addOnCompleteListener(new OnCompleteListener<AuthResult>()
                                                                                                               {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if(task.isSuccessful())
                {
                    Date date1= null;
                    try
                    {
                        FirebaseUser Usr = FirebaseAuth.getInstance().getCurrentUser();
                        String ID = Usr.getUid();
                        date1 = new SimpleDateFormat("dd/MM/yyyy").parse(DOB_input.getText().toString());
                        Date Current = Calendar.getInstance().getTime();
                        new_User  newUsr =  new new_User(Username_input.getText().toString().trim(),date1,Current,ID);
                        FFstore.collection("USR").add(newUsr).addOnSuccessListener(new OnSuccessListener<DocumentReference>()
                        {
                            @Override
                            public void onSuccess(DocumentReference documentReference)
                            {
                                Log.d("","new user created: " + ID + " at:"+ String.valueOf(FieldValue.serverTimestamp()) );
                                startActivity(new Intent(createuser.this,new_User_image.class));
                            }
                        }
                        );
                    }
                    catch (ParseException e)
                    {
                        e.printStackTrace();
                    }




                }
            }
        }
        );
    }
}