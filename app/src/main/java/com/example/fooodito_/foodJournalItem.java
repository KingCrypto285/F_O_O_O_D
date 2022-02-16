package com.example.fooodito_;

import com.google.firebase.firestore.ServerTimestamp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class foodJournalItem
{
    private String user_id;
    @ServerTimestamp
    private Date time_at;
    private int QTY;
    private String Food_Item;

    public foodJournalItem()
    {
    }

    public String getUser_ID() {
        return user_id;
    }

    public void setUser_ID(String user_ID) {
        this.user_id = user_ID;
    }

    public Date getTime_at() {
        return time_at;
    }

    public void setTime_at(Date time_at) {
        this.time_at = time_at;
    }

    public int getQTY() {
        return QTY;
    }

    public void setQTY(int QTY) {
        this.QTY = QTY;
    }

    public String getFood_Item() {
        return Food_Item;
    }

    public void setFood_Item(String food_Item) {
        Food_Item = food_Item;
    }

    public foodJournalItem(String user_id, Date time_at, int QTY, String food_Item) {
        this.user_id = user_id;
        this.time_at = time_at;
        this.QTY = QTY;
        Food_Item = food_Item;
    }

    public String Day_date(Date day)
    {
        Date dt = new Date(String.valueOf(day   ));
        SimpleDateFormat sdf = new SimpleDateFormat("EEE d, MMM");
        String time1 = sdf.format(dt);

        return time1;
    }

    public String Return_time(Date Day)
    {

        Date dt = new Date(String.valueOf(Day));
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm aa");
        String time1 = sdf.format(dt);

        return time1;
    }
}
