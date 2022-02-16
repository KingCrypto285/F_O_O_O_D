package com.example.fooodito_;

import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class firebaseuser
{
    private String UserID;
    private String Useername;

    FirebaseAuth Fauth;

    public firebaseuser()
    {}

    public String getUseername() {
        return Useername;
    }

    public void setUseername(String useername) {
        Useername = useername;
    }

    public String getUserID() {
        FirebaseUser Fuse = FirebaseAuth.getInstance().getCurrentUser();
        setUserID(Fuse.getUid());

        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }
}
