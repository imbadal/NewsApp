package com.example.newsapp;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Named;

import androidx.room.Query;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class NewsApiKeyInterceptor implements Interceptor {

    private String apiKey;
    public NewsApiKeyInterceptor(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl url = request.url().newBuilder().addQueryParameter("apiKey", apiKey).build();
        Request newRequest = request.newBuilder().url(url).build();
        return chain.proceed(newRequest);
    }
}
