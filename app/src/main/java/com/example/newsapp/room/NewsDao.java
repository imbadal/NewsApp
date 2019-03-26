package com.example.newsapp.room;

import com.example.newsapp.model.ModelArticle;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import io.reactivex.Observable;

@Dao
public interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(List<ModelArticle> article);

    @Query("SELECT * FROM news_table")
    Observable<List<ModelArticle>> getArticles();


}
