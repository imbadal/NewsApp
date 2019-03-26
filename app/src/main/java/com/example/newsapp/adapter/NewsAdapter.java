package com.example.newsapp.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.newsapp.R;
import com.example.newsapp.model.ModelArticle;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    List<ModelArticle> newsList = new ArrayList<>();
    private RecyclerViewClickListener listener;

    public NewsAdapter(RecyclerViewClickListener listener) {
        this.listener = listener;
    }

    public void setAtricles(List<ModelArticle> newsList) {
        this.newsList = newsList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.news_item, viewGroup, false);
        return new NewsViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder newsViewHolder, int i) {

        newsViewHolder.textViewTitle.setText(newsList.get(i).getTitle());
        newsViewHolder.textViewDesc.setText(newsList.get(i).getDescription());
        String date = getDate(newsList.get(i).getPublishedAt());
        newsViewHolder.textViewDate.setText(date);
//        newsViewHolder.textViewSource.setText(newsList.get(i).getSource().getName());
        Glide.with(newsViewHolder.itemView)
                .load(newsList.get(i).getUrlToImage())
                .placeholder(R.drawable.place_holder)
                .into(newsViewHolder.imageView);
    }

    private String getDate(String raw) {

        StringBuilder date = new StringBuilder();
        for (int i = 0; i < raw.length(); i++) {
            if (raw.charAt(i) == 'T') {
                date.append(", ");
            } else if (raw.charAt(i) == 'Z') {
                date.append("");
            } else {
                date.append(raw.charAt(i));
            }
        }
        return date.toString();
    }


    @Override
    public int getItemCount() {
        return newsList==null?0:newsList.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewTitle;
        TextView textViewDesc;
        TextView textViewDate;
        TextView textViewSource;
        ImageView imageView;
        RecyclerViewClickListener listener;

        public NewsViewHolder(@NonNull View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            this.listener = listener;
            textViewTitle = itemView.findViewById(R.id.title);
            textViewDesc = itemView.findViewById(R.id.desc);
            textViewDate = itemView.findViewById(R.id.date_time);
            textViewSource = itemView.findViewById(R.id.source);
            imageView = itemView.findViewById(R.id.image);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(v, getAdapterPosition(),newsList.get(getAdapterPosition()));
        }
    }

    public interface RecyclerViewClickListener {

        void onClick(View view, int position,ModelArticle article);
    }


}
