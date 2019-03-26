package com.example.newsapp.model;

import java.util.List;

public class ModelNews {

    String status;
    int totalResults;
    List<ModelArticle> articles;

    public ModelNews() {
    }

    public ModelNews(String status, int totalResults, List<ModelArticle> articles) {
        this.status = status;
        this.totalResults = totalResults;
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<ModelArticle> getArticles() {
        return articles;
    }

    public void setArticles(List<ModelArticle> articles) {
        this.articles = articles;
    }
}
