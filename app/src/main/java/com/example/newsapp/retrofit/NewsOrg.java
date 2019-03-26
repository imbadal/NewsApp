package com.example.newsapp.retrofit;

import com.example.newsapp.model.ModelNews;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsOrg {

    @GET("v2/top-headlines")
    Observable<ModelNews> getNews(@Query("country") String country);

}
