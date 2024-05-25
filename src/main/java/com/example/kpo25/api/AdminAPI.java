package com.example.kpo25.api;

import com.example.kpo25.api.dto.NewsAPIDTO;

import java.util.List;

public interface AdminAPI {

    Object getAllNews();

    NewsAPIDTO createNews(String name, String info);

    NewsAPIDTO editNews(String name, String info, Boolean deleted);
}
