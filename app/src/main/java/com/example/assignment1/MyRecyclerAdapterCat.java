package com.example.assignment1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerAdapterCat extends RecyclerView.Adapter<MyRecyclerAdapterCat.ViewHolder> {

    private ArrayList<Category> data = new ArrayList<>();

    private final int HEADER_TYPE = 0;
    private final int ITEM_TYPE = 1;

    public MyRecyclerAdapterCat(Context context, List<Category> listCategory){
        this.data.addAll(listCategory);
    }
    public void setData(ArrayList<Category> data) {
        this.data.clear();
        this.data = data;

        notifyDataSetChanged();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView CategoryIDcv;
        public TextView CategoryNameCV;
        public TextView EventCountCategory;
        public TextView CategoryIsActive;
        public View CategoryView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            CategoryView = itemView;
            CategoryIDcv = itemView.findViewById(R.id.card_category_id);
            CategoryNameCV = itemView.findViewById(R.id.card_name);
            EventCountCategory = itemView.findViewById(R.id.card_event_count);
            CategoryIsActive = itemView.findViewById(R.id.card_is_active);

        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        if(viewType==HEADER_TYPE){
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout_header, parent, false); //CardView inflated as RecyclerView list item
        }
        else{
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false); //CardView inflated as RecyclerView list item

        }
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        if(position==0){
           holder.CategoryIDcv.setText("Id");
           holder.CategoryNameCV.setText("Name");
           holder.EventCountCategory.setText("Event Count");
        }else {
            holder.EventCountCategory.setText(String.valueOf(data.get(position-1).getEventCount()));
            holder.CategoryNameCV.setText(String.valueOf(data.get(position-1).getName()));
            holder.CategoryIDcv.setText(data.get(position-1).getCategoryId());
            holder.CategoryIsActive.setText(data.get(position-1).isActive() ? "Yes" : "No");

        }

        holder.CategoryView.setOnClickListener(v -> {
            String selectedCountry = data.get(position -1).getCategoryLocation();

            Context context = holder.CategoryView.getContext();
            Intent intent = new Intent(context, CategoryMapsActivity.class);
            intent.putExtra("SelectedCounty", selectedCountry);
            context.startActivity(intent);
        });
    }


    @Override
    public int getItemCount() {
        return data.size()+1;
    }
}