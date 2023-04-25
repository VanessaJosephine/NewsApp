package com.example.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class TopStoriesAdapter extends RecyclerView.Adapter<TopStoriesAdapter.MyViewHolder>{
    Context context;
    ArrayList<News> list;
    ItemClickListener clickListener;
    public TopStoriesAdapter(Context context, ArrayList<News> list, ItemClickListener clickListener) {
        this.context = context;
        this.list = list;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public TopStoriesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Making the layout appear
        View view = LayoutInflater.from(context).inflate(R.layout.topstories_layout, parent, false);
        // Calling the constructor
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopStoriesAdapter.MyViewHolder holder, int position) {
        News news = list.get(position);
        // Set values to the UIs
        holder.image.setImageResource(news.image);
        // OnClickListener
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onItemClick(news);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ShapeableImageView image;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imageView);
        }
    }
    public interface ItemClickListener {
        public void onItemClick(News news);
    }
}
