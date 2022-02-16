package com.example.fooodito_;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class sidemenu extends AppCompatActivity
{
    ImageView circle_profileImage,temp;
    FirebaseFirestore Fstore;
    FirebaseStorage storage;
    StorageReference storageRef;
    FirebaseUser fuser;
    FirebaseAuth FAuth;
    firebasefunctionality firebasefunc;
    private String UserID;
    private String Username;
    private String URLs;
    TextView UserName_View;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sidemenu);

        Intialize();

    }


    public String getURLs() {
        return URLs;
    }

    public void setURLs(String URLs) {
        this.URLs = URLs;
    }

    public void Intialize()
    {
        FAuth = FirebaseAuth.getInstance();
        Fstore = FirebaseFirestore.getInstance();


        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();
        UserName_View = findViewById(R.id.Textview_username);
        circle_profileImage = findViewById(R.id.profile_image);
        temp = findViewById(R.id.ima1geView);

        User_Name();
        load_userImage(getUserID());
        return_url();
    }

    public void BACK(View view)
    {
        startActivity(new Intent(sidemenu.this,mainmenu.class));
    }

    public void return_url()
    {
        storageRef.child("/default.png").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>()
        {
            @Override
            public void onSuccess(Uri uri)
            {
                setURLs(String.valueOf(uri));
            }
        }
        ).addOnFailureListener(new OnFailureListener()
        {
            @Override
            public void onFailure(@NonNull Exception e)
            {
               //
            }
        });
    }

    public void  User_Name()
    {
        Fstore = FirebaseFirestore.getInstance();
        Log.d("",getUserID());
        Fstore.collection("USR").whereEqualTo("user_ID",getUserID()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
                                                                                                 {
                                                                                                     @Override
                                                                                                     public void onComplete(@NonNull Task<QuerySnapshot> task)
                                                                                                     {
                                                                                                         if(task.isSuccessful())
                                                                                                         {
                                                                                                             for(QueryDocumentSnapshot QDS : task.getResult())
                                                                                                             {
                                                                                                                 String Name = QDS.getString("user_Name");
                                                                                                                 Log.d("user_Name",Name);
                                                                                                                 UserName_View.setText(Name);
                                                                                                             }
                                                                                                         }
                                                                                                     }
                                                                                                 }
        );
    }

    public String getUserID()
    {
        FAuth = FirebaseAuth.getInstance();
        fuser = FAuth.getCurrentUser();
        Log.d("getuserid",fuser.getUid());
        setUserID(fuser.getUid());
        return UserID;
    }

    public void load_userImage(String ID)
    {
        Log.d("","user id: " + ID);
        Fstore.collection("User_media").whereEqualTo("user_ID",ID).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
        {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task)
            {
               if(task.isSuccessful())
               {
                   for(QueryDocumentSnapshot QDS : task.getResult())
                   {
                       String media_id = QDS.getString("media_ID");
                       Log.d("user","User media id: " + media_id);


                        get_media(media_id);
                   }
               }
               //Log.d("","Did not work ");
            }
        }
        );
    }

    public void get_media(String media_id)
    {
        Log.d("get media: ", media_id);
        Fstore.collection("Media").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
        {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task)
            {
                if(task.isSuccessful())
                {


                    for(QueryDocumentSnapshot Doc : task.getResult())
                    {
                       String ID = Doc.getString("media_ID");

                       if(ID.equals(media_id))
                       {
                           String URL = Doc.getString("url");
                           Glide.with(sidemenu.this).load(getURLs()).into(circle_profileImage);

                       }
                       else
                       {
                           Log.d("","Did not work");
                       }
                    }
                    Log.d("","Step 4");


                }
                else
                {
                }
            }
        });

    }


    public void setUserID(String userID)
    {
        UserID = userID;
    }

    public String getUsername()
    {
        return Username;
    }

    public void setUsername(String username)
    {
        Username = username;
    }

    public void LOGOUT(View view)
    {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(sidemenu.this,MainActivity.class));
    }

    public void load_settings(View view)
    {
        //startActivity(new Intent(sidemenu.this,new_User_image.class));
    }

    public void load_foods(View view)
    {
        startActivity(new Intent(sidemenu.this,Main_Page.class));

    }

    public void load_socials(View view)
    {
        startActivity(new Intent(sidemenu.this,Socials.class));

    }


    public void go_active(View view)
    {
        startActivity(new Intent(sidemenu.this,active.class));
    }
}