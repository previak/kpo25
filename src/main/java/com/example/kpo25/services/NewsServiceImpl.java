package com.example.kpo25.services;

import com.example.kpo25.data.repositories.NewsRepository;
import com.example.kpo25.data.entities.NewsEntity;
import com.example.kpo25.mappers.NewsMapper;
import com.example.kpo25.services.dto.NewsServiceDTO;
import com.example.kpo25.services.interfaces.NewsService;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class NewsServiceImpl implements NewsService {

    NewsRepository newsRepository;
    NewsMapper newsMapper;

    @Override
    public List<NewsServiceDTO> getAllNews() {
        return newsRepository.findAll().stream()
                .map(newsMapper::newsEntity2NewsServiceDTO)
                .collect(Collectors.toList());
    }

    @Override
    public NewsServiceDTO createNews(String name, String info) {
        NewsEntity newsEntity = new NewsEntity();
        newsEntity.setName(name);
        newsEntity.setInfo(info);
        newsEntity = newsRepository.save(newsEntity);
        return newsMapper.newsEntity2NewsServiceDTO(newsEntity);
    }

    @Override
    public NewsServiceDTO editNews(String name, String info, Boolean deleted) {
        NewsEntity newsEntity = newsRepository.findByName(name);
        newsEntity.setInfo(info);
        newsEntity.setDeleted(deleted);
        newsEntity = newsRepository.save(newsEntity);
        return newsMapper.newsEntity2NewsServiceDTO(newsEntity);
    }

    @Override
    public List<NewsServiceDTO> getAllNotDeletedNews() {
        List<NewsEntity> newsList = newsRepository.findAll();
        List<NewsServiceDTO> notDeletedNews = newsList.stream()
                .filter(news -> news.getDeleted() != null && !news.getDeleted())
                .map(newsMapper::newsEntity2NewsServiceDTO)
                .collect(Collectors.toList());

        return notDeletedNews.isEmpty() ? new ArrayList<>() : notDeletedNews;
    }
}