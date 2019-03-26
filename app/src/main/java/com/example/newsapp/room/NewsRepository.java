package com.example.newsapp.room;

import android.annotation.SuppressLint;

import com.example.newsapp.NetworkState;
import com.example.newsapp.application.NewsApplication;
import com.example.newsapp.model.ModelArticle;
import com.example.newsapp.model.ModelNews;
import com.example.newsapp.retrofit.NewsOrg;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class NewsRepository {

    private NewsDao dao;
    private NewsOrg api;
    private NetworkState networkState;
    private long lastRequest;


    @Inject
    public NewsRepository(NewsDao newsDao, NewsOrg api, NetworkState networkState) {
        dao = newsDao;
        this.api = api;
        this.networkState = networkState;

    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @SuppressLint("CheckResult")
    public Observable<List<ModelArticle>> getAllNews() {
        if (networkState.isConnected() && shouldMakeRequest()) {
            lastRequest = new Date().getTime();
            getNetworkResponseAnUpdateDB().subscribe(modelNews -> {
            }, throwable -> {
            });
        }
        return dao.getArticles();
    }

    public Observable<ModelNews> getNetworkResponseAnUpdateDB() {
        return api.getNews("in")
                .doOnNext(modelNews -> dao.insert(modelNews.getArticles()))
                .subscribeOn(Schedulers.io());
    }

    private boolean shouldMakeRequest() {
        if (lastRequest == 0) return true;
        return new Date().getTime() - lastRequest > TimeUnit.MILLISECONDS.convert(10, TimeUnit.SECONDS);
    }
}
