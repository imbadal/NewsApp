package com.example.newsapp.application;

import android.app.Application;

import com.example.newsapp.BuildConfig;
import com.example.newsapp.dagger.AppComponent;
import com.example.newsapp.dagger.AppModule;
import com.example.newsapp.dagger.DaggerAppComponent;
import com.example.newsapp.dagger.NetworkModule;

public class NewsApplication extends Application {
    AppComponent appComponent;
    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule("https://newsapi.org/", BuildConfig.API_KEY))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
