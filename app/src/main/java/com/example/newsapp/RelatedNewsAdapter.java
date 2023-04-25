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

public class RelatedNewsAdapter extends RecyclerView.Adapter<RelatedNewsAdapter.MyViewHolder>{
    Context context;
    ArrayList<News> list;
    ItemClickListener clickListener;
    public RelatedNewsAdapter(Context context, ArrayList<News> list, ItemClickListener clickListener) {
        this.context = context;
        this.list = list;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public RelatedNewsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Making the layout appear
        View view = LayoutInflater.from(context).inflate(R.layout.relatednews_layout, parent, false);
        // Calling the constructor
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RelatedNewsAdapter.MyViewHolder holder, int position) {
        News news = list.get(position);
        // Set values to the UIs
        holder.title.setText(news.title);
        holder.desc.setText(news.desc.substring(0, 50)+ "...");
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
        TextView title;
        TextView desc;
        ShapeableImageView image;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textViewRelatedTitle);
            desc = itemView.findViewById(R.id.textViewRelatedDesc);
            image = itemView.findViewById(R.id.imageViewRelated);
        }
    }
    public interface ItemClickListener {
        public void onItemClick(News news);
    }
}
