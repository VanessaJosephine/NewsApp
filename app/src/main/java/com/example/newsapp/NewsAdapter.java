package com.example.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder>{
    Context context;
    ArrayList<News> list;
    ItemClickListener clickListener;
    public NewsAdapter(Context context, ArrayList<News> list, ItemClickListener clickListener) {
        this.context = context;
        this.list = list;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public NewsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Making the layout appear
        View view = LayoutInflater.from(context).inflate(R.layout.news_layout, parent, false);
        // Calling the constructor
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.MyViewHolder holder, int position) {
        News news = list.get(position);
        // Set values to the UIs
        holder.title.setText(news.title);
        holder.desc.setText(news.desc.substring(0, 30)+ "...");
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
            title = itemView.findViewById(R.id.textView4);
            desc = itemView.findViewById(R.id.textView5);
            image = itemView.findViewById(R.id.imageView2);
        }
    }
    public interface ItemClickListener {
        public void onItemClick(News news);
    }
}
