package com.example.newsapp.room;

import android.annotation.SuppressLint;
import android.app.Application;

import com.example.newsapp.application.NewsApplication;
import com.example.newsapp.model.ModelArticle;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class NewsViewModel extends AndroidViewModel {

    @Inject
    NewsRepository newsRepository;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private MutableLiveData<List<ModelArticle>> state = new MutableLiveData<>();

    MutableLiveData<Boolean> loadingState = new MutableLiveData<>();

    public NewsViewModel(@NonNull Application application) {
        super(application);

        ((NewsApplication) application).getAppComponent().inject(this);

        loadArticles();

    }

    @SuppressLint("CheckResult")
    private void loadArticles() {
        //noinspection ResultOfMethodCallIgnored
        newsRepository.getAllNews()
                .doOnSubscribe(disposable -> compositeDisposable.add(disposable))
                .subscribe(articles -> state.postValue(articles), throwable -> {
                });
    }

    @SuppressLint("CheckResult")
    public void refreshArticle() {

        newsRepository.getNetworkResponseAnUpdateDB()
                .doOnSubscribe(disposable -> compositeDisposable.add(disposable))
                .doOnSubscribe(disposable -> loadingState.postValue(true))
                .subscribe(load -> loadingState.postValue(false),
                        throwable -> loadingState.postValue(false));
    }

    public MutableLiveData<List<ModelArticle>> getArticles() {
        return state;
    }

    public MutableLiveData<Boolean> getLoadingState() {
        return loadingState;
    }


    @Override
    protected void onCleared() {
        if (!compositeDisposable.isDisposed()) compositeDisposable.dispose();
        super.onCleared();
    }
}
