package com.example.newsapp.dagger;

import android.app.Application;

import com.example.newsapp.room.NewsDao;
import com.example.newsapp.room.NewsDatabase;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import dagger.Module;
import dagger.Provides;

@Module(includes = {AppModule.class})
public class DatabaseModule {

    @Provides
    public NewsDatabase databaseRoom(Application application) {
        return Room.databaseBuilder(application, NewsDatabase.class, "news_database")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    public NewsDao provideNewsDao(NewsDatabase database) {
        return database.newsDao();
    }

}
