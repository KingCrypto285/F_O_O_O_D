package com.example.fooodito_;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity
{
    EditText Email_Input, Password_Input;
    FirebaseAuth FireAuth;
    private String Email,Password;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intialize();


    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = FireAuth.getCurrentUser();

        if(currentUser != null)
        {
            startActivity(new Intent(MainActivity.this,mainmenu.class));
        }
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void Intialize()
    {
        Email_Input = findViewById(R.id.user_InputEmail);
        Password_Input = findViewById(R.id.user_passwordInput);

        FireAuth = FirebaseAuth.getInstance();

    }

    public boolean validate_login(String Eml,String Pwd)
    {

        if(Eml.isEmpty() || Pwd.isEmpty())
        {
            Toast.makeText(MainActivity.this, "Please enter your email and password", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;

    }

    public void LOGIN(View view)
    {

        setEmail(Email_Input.getText().toString().trim());
        setPassword(Password_Input.getText().toString().trim());
        Boolean result = validate_login(getEmail(),getPassword());

        if(result == true)
        {
            FireAuth.signInWithEmailAndPassword(getEmail(),getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>()
            {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task)
                {
                    if(task.isSuccessful())
                    {
                        //Start Main menu
                        startActivity(new Intent(MainActivity.this,mainmenu.class));
                    }

                }
            }
            );

        }


    }

    public void createUser(View view)
    {
        //new user
        startActivity(new Intent(MainActivity.this,createuser.class));
    }
}