package com.example.newsapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.model.ModelArticle;
import com.example.newsapp.adapter.NewsAdapter;
import com.example.newsapp.retrofit.NewsOrg;
import com.example.newsapp.R;
import com.example.newsapp.room.NewsViewModel;

import java.util.List;

import javax.inject.Inject;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    RecyclerView recyclerView;
    NewsAdapter adapter;
    SwipeRefreshLayout refreshLayout;
    NewsAdapter.RecyclerViewClickListener listener;
    NewsViewModel viewModel;

    @Inject
    NewsOrg newsOrg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
        setContentView(R.layout.activity_main);
        refreshLayout = findViewById(R.id.refresh);
        recyclerView = findViewById(R.id.recyclerview_news);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listener = (view, position, article) -> {
            Intent intent = new Intent(getApplicationContext(), WebViewActivity.class);
            intent.putExtra("url", article.getUrl());
            startActivity(intent);
        };
        adapter = new NewsAdapter(listener);
        recyclerView.setAdapter(adapter);

        refreshLayout.setOnRefreshListener(() -> {
            viewModel.refreshArticle();
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        render();
    }

    public void render() {
        viewModel.getArticles().observe(this, this::updateUI);
        viewModel.getLoadingState().observe(this, this::hideLoading);
    }

    private void hideLoading(Boolean isLoading) {
        refreshLayout.setRefreshing(isLoading);
    }


    private void updateUI(List<ModelArticle> newsState) {
        adapter.setAtricles(newsState);
    }
}