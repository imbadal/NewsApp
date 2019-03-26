package com.example.newsapp.room;

import com.example.newsapp.model.ModelArticle;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = ModelArticle.class, version = 1)
public abstract class NewsDatabase extends RoomDatabase {
    public abstract NewsDao newsDao();
}
