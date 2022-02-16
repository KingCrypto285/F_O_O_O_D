package com.example.fooodito_;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class foods_tabAdapter extends RecyclerView.Adapter<foods_tabAdapter.foodsViewHolder>
{

    FirebaseAuth FA;
    private Context CTX;
    private List<foodJournalItem> journal_list;

    public foods_tabAdapter()
    {
    }

    public foods_tabAdapter(Context CTX, List<foodJournalItem> journal_list)
    {
        this.CTX = CTX;
        this.journal_list = journal_list;
    }

    @NonNull
    @Override
    public foods_tabAdapter.foodsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        return new foods_tabAdapter.foodsViewHolder
                (
                        LayoutInflater.from(CTX).inflate(R.layout.food_journal_entry, parent, false)
                );
    }

    @Override
    public void onBindViewHolder(@NonNull foods_tabAdapter.foodsViewHolder holder, int position)
    {
        final foodJournalItem FJ_item = journal_list.get(position);



            //date1 = new SimpleDateFormat("hh:mm").parse(String.valueOf(FJ_item.getTime_at()));


            holder.Text_foodItem.setText(FJ_item.getFood_Item());
            holder.Text_QTY.setText("QTY: " + String.valueOf(FJ_item.getQTY()));
            holder.Text_Time.setText(FJ_item.Return_time(FJ_item.getTime_at()));




    }



    @Override
    public int getItemCount()
    {
        return journal_list.size();
    }

    class foodsViewHolder extends RecyclerView.ViewHolder
    {

        TextView Text_QTY, Text_foodItem,Text_Time;

        public foodsViewHolder(View itemView)
        {
            super(itemView);
            Text_QTY = itemView.findViewById(R.id.quantity_amount);
            Text_foodItem = itemView.findViewById(R.id.food_item);
            Text_Time = itemView.findViewById(R.id.time);


        }

    }
}
