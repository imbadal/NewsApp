package com.example.newsapp.dagger;

import com.example.newsapp.activity.MainActivity;
import com.example.newsapp.room.NewsRepository;
import com.example.newsapp.room.NewsViewModel;

import dagger.Component;

@Component(modules = {NetworkModule.class, AppModule.class, DatabaseModule.class, ApiKey.class})
public interface AppComponent {
    void inject(NewsViewModel viewModel);

    void inject(NewsRepository newsRepository);
}
