package com.example.kpo25.services.interfaces;

import com.example.kpo25.api.dto.NewsAPIDTO;
import com.example.kpo25.services.dto.NewsServiceDTO;

import java.util.List;

public interface NewsService {

    List<NewsServiceDTO> getAllNews();

    NewsServiceDTO createNews(String name, String info);

    NewsServiceDTO editNews(String name, String info, Boolean deleted);

    List<NewsServiceDTO> getAllNotDeletedNews();
}
