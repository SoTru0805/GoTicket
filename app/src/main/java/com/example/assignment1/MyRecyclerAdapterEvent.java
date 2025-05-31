package com.example.assignment1;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerAdapterEvent extends RecyclerView.Adapter<MyRecyclerAdapterEvent.ViewHolder> {

    private ArrayList<Event> data = new ArrayList<>();

    private Context context;

    public MyRecyclerAdapterEvent(Context context, List<Event> listEvent) {
        this.data.addAll(listEvent);
    }

    public void setData(ArrayList<Event> data) {
        this.data.clear();
        this.data = data;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout_event, parent, false); //CardView inflated as RecyclerView list item


        ViewHolder viewHolder = new ViewHolder(v);
        Log.d("Assignment-AK", "onCreateViewHolder");
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {{
        holder.textViewId.setText(data.get(position).getEventId());
        holder.textViewTickets.setText(String.valueOf(data.get(position).getTicketsAvailable()));
        holder.textViewName.setText(String.valueOf(data.get(position).getName()));
        holder.textViewCategoryId.setText(data.get(position).getCategoryId());
        holder.isActive.setText(data.get(position).isActive() ? "Active" : "InActive");
    }
        holder.EventView.setOnClickListener(v -> {
            String eventWebView = data.get(position).getEventName();

            Context context = holder.EventView.getContext();
            Intent intent = new Intent(context, EventWebView.class);
            intent.putExtra("selectedEventWeb", eventWebView);
            context.startActivity(intent);
        });

    }





    @Override
    public int getItemCount() {
        return data.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewId;
        public TextView textViewCategoryId;
        public TextView textViewTickets;
        public TextView textViewName;
        public TextView isActive;
        public View EventView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            EventView = itemView;
            textViewId = itemView.findViewById(R.id.card_event_id);
            textViewCategoryId = itemView.findViewById(R.id.card_category_id);
            textViewTickets = itemView.findViewById(R.id.card_event_tickets);
            textViewName = itemView.findViewById(R.id.card_event_name);
            isActive = itemView.findViewById(R.id.card_is_active);
        }
    }
}
