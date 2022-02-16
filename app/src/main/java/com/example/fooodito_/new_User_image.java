package com.example.fooodito_;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class new_User_image extends AppCompatActivity
{
    ImageView profile_Image;
    EditText Gender_Input;

    Uri imageuri;
    StorageReference storageRef;
    FirebaseFirestore Fstore;
    FirebaseAuth Fauth;
    FirebaseStorage storage;

    private String default_imageURL = "gs://yolup-ea4fc.appspot.com/default.png";
    private static final int CAMERA_PERMISSION_CODE = 100;
    private static final int STORAGE_PERMISSION_CODE = 101;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_image);

        intialize();
        Log.d("",generate_unique_Id());
    }

    public void intialize()
    {
        profile_Image = findViewById(R.id.new_imageInput);

        Gender_Input = findViewById(R.id.newgenderinput);

        Fstore = FirebaseFirestore.getInstance();
        Fauth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void capture_image(View view)
    {
        checkPermission(Manifest.permission.CAMERA, CAMERA_PERMISSION_CODE);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void load_image(View view)
    {
        checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, STORAGE_PERMISSION_CODE);
    }


    public void skip_section(View view)
    {
        add_userGender(return_Gender());

        upload_image("Default");

        startActivity(new Intent(new_User_image.this,mainmenu.class));
    }



    public String return_Gender()
    {
        if(TextUtils.isEmpty(Gender_Input.getText().toString().trim()))
        {
            return "Default";
        }
        return Gender_Input.getText().toString().trim();
    }

    public void add_userGender(String gender)
    {
        GenderClass Gender = new GenderClass(User_ID(),gender);
        Fstore.collection("user_gender").add(Gender).addOnSuccessListener(new OnSuccessListener<DocumentReference>()
        {
            @Override
            public void onSuccess(DocumentReference documentReference)
            {

            }
        });
    }

    public String User_ID()
    {
        FirebaseUser Usr = FirebaseAuth.getInstance().getCurrentUser();
        String ID = Usr.getUid();
        return ID;
    }

    public void upload_image(String setup)
    {
        if(setup.equals("Default"))
        {
            String medid = generate_unique_Id();
            usermedia User_Media = new usermedia(User_ID(), medid);
            Media media = new Media(medid, default_imageURL, "0");

            //StorageReference imagesRef = storageRef.child("Image");

            Fstore.collection("User_media").add(User_Media).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                                                     @Override
                                                                                     public void onSuccess(DocumentReference documentReference) {
                                                                                         Fstore.collection("Media").add(media).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                                                                                                                        @Override
                                                                                                                                                        public void onSuccess(DocumentReference documentReference) {

                                                                                                                                                        }
                                                                                                                                                    }
                                                                                         );
                                                                                     }
                                                                                 }
            );
        }

    }

    public void send_firebase()
    {
        profile_Image.setDrawingCacheEnabled(true);
        profile_Image.buildDrawingCache();
        Bitmap bitmap = ((BitmapDrawable) profile_Image.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();



    }

    public String generate_unique_Id()
    {
        DatabaseReference ref=FirebaseDatabase.getInstance().getReference();
        String uniqueId = ref.push().getKey();
        //Need id for the return here.
        return uniqueId;
    }


    //request user permission
    public void checkPermission(String permission, int requestCode)
    {
        if (ContextCompat.checkSelfPermission(new_User_image.this, permission) == PackageManager.PERMISSION_DENIED)
        {
            // Requesting the permission
            ActivityCompat.requestPermissions(new_User_image.this, new String[] { permission }, requestCode);
        }
        else
            {

                if(requestCode == CAMERA_PERMISSION_CODE)
                {
                    Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, CAMERA_PERMISSION_CODE);
                }
                else
                {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Image from here..."), STORAGE_PERMISSION_CODE);
                }
                
            }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode,
                permissions,
                grantResults);

        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(new_User_image.this, "Camera Permission Granted", Toast.LENGTH_SHORT) .show();
            }
            else {
                Toast.makeText(new_User_image.this, "Camera Permission Denied", Toast.LENGTH_SHORT) .show();
            }
        }
        else if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(new_User_image.this, "Storage Permission Granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(new_User_image.this, "Storage Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        imageuri = data.getData();

        if (requestCode == CAMERA_PERMISSION_CODE )
        {
           try
            {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageuri);
                profile_Image.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        if (requestCode == STORAGE_PERMISSION_CODE )
        {
            try
            {
                Bitmap Photo = (Bitmap)data.getExtras().get("data");
                profile_Image.setImageBitmap(Photo);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}