package com.example.fooodito_;

import android.widget.Toast;

import java.util.Date;
import java.util.regex.Pattern;

public class new_User
{
    private String User_Name;
    private String user_ID;
    private Date Date_of_Birth;
    private Date created_log;

    public new_User()
    {}

    public new_User(String user_Name,Date date_of_Birth, Date created_log, String user_ID)
    {
        User_Name = user_Name;
        Date_of_Birth = date_of_Birth;
        this.created_log = created_log;
        this.user_ID = user_ID;
    }

    public String getUser_ID() {
        return user_ID;
    }

    public void setUser_ID(String user_ID) {
        this.user_ID = user_ID;
    }

    public Date getCreated_log() {
        return created_log;
    }

    public void setCreated_log(Date created_log) {
        this.created_log = created_log;
    }

    public String getUser_Name() {
        return User_Name;
    }

    public void setUser_Name(String user_Name) {
        User_Name = user_Name;
    }

    public Date getDate_of_Birth() {
        return Date_of_Birth;
    }

    public void setDate_of_Birth(Date date_of_Birth) {
        Date_of_Birth = date_of_Birth;
    }

    public boolean equal_passwords(String Password,String confirm_password)
    {
        boolean result = true;

        if(Password.equals(confirm_password))
        {
            return  result;
        }

        return false;

    }

    public boolean check_password(String Password)
    {
        Pattern uppercase = Pattern.compile("[A-Z]");
        Pattern lowercase = Pattern.compile("[a-z]");
        Pattern digit = Pattern.compile("[0-9]");



        if(Password.length() >= 8)
        {
            if(!uppercase.matcher(Password).find())
            {
                return  false;
            }
            else
            {
                if(!lowercase.matcher(Password).find())
                {
                    return false;
                }
                else
                {
                    if(digit.matcher(Password).find())
                    {
                        return false;
                    }
                    else
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
