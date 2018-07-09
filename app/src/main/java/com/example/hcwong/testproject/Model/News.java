package com.example.hcwong.testproject.Model;

import java.util.List;

public class News {
    private String status;
    private int totalResults;
    List<Article> articles;

    public String getStatus() {
        return status;
    }
    public int getTotalResults() {
        return totalResults;
    }
    public List<Article> getArticles() {
        return articles;
    }


}
