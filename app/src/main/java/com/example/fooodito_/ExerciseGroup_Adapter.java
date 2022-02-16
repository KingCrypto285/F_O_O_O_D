package com.example.fooodito_;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class ExerciseGroup_Adapter extends RecyclerView.Adapter<ExerciseGroup_Adapter.ExerciseGroupViewHolder>
{
    private Context CTX;
    private List<ExerciseGroup> group_list;

    public ExerciseGroup_Adapter() {
    }

    @NonNull
    @Override
    public ExerciseGroup_Adapter.ExerciseGroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        return new ExerciseGroup_Adapter.ExerciseGroupViewHolder
                (
                    LayoutInflater.from(CTX).inflate(R.layout.exercise_set, parent, false)
                );
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseGroup_Adapter.ExerciseGroupViewHolder holder, int position)
    {
        final ExerciseGroup EXE_Group = group_list.get(position);

        holder.Text_Title.setText(EXE_Group.getTitle());
        holder.Text_order.setText(EXE_Group.getOrder());

        holder.Text_Title.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });
    }

    @Override
    public int getItemCount()
    {
        return group_list.size();
    }

    public ExerciseGroup_Adapter(Context CTX, List<ExerciseGroup> group_list)
    {
        this.CTX = CTX;
        this.group_list = group_list;
    }


    class ExerciseGroupViewHolder extends RecyclerView.ViewHolder
    {

        TextView Text_order, Text_Title;

        public ExerciseGroupViewHolder(View itemView)
        {
            super(itemView);

            Text_order = itemView.findViewById(R.id.OrderNumber);
            Text_Title = itemView.findViewById(R.id.ExerciseSet_Title);

        }

    }
}
