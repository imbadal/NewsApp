package com.example.newsapp.dagger;

import dagger.Module;
import dagger.Provides;

@Module
public class ApiKey {

    @Provides
    public String provideApiKey() {
        return "b0163361f90e4d51b663eb66acd3ea3f";
    }

}
