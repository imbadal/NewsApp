package com.example.newsapp.dagger;

import android.app.Application;

import com.example.newsapp.NetworkState;
import com.example.newsapp.NewsApiKeyInterceptor;
import com.example.newsapp.retrofit.NewsOrg;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = {AppModule.class})
public class NetworkModule {
    private String baseUrl;
    private String apiKey;

    public NetworkModule(String baseUrl, String apiKey) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
    }

    @Provides
    public NewsOrg provideNewsOrg(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit.create(NewsOrg.class);
    }

    @Provides
    public NewsApiKeyInterceptor providerApiKey() {
        return new NewsApiKeyInterceptor(apiKey);
    }

    @Provides
    public NetworkState provideNetwork(Application application) {

        return new NetworkState(application);
    }

    @Provides
    public OkHttpClient provideOkHttp(NewsApiKeyInterceptor apiKeyInterceptor) {
        return new OkHttpClient.Builder().addInterceptor(apiKeyInterceptor).build();
    }
}
