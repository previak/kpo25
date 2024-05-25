package com.example.kpo25.controllers;

import com.example.kpo25.api.AdminAPI;
import com.example.kpo25.api.dto.NewsAPIDTO;
import com.example.kpo25.mappers.NewsMapper;
import com.example.kpo25.services.interfaces.NewsService;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("/admin")
public class AdminController implements AdminAPI {

    NewsService newsService;
    NewsMapper newsMapper;

    @Override
    @GetMapping("/news")
    public Object getAllNews() {
        List<NewsAPIDTO> newsList = newsService.getAllNews().stream()
                .map(newsMapper::newsServiceDTO2NewsAPIDTO)
                .collect(Collectors.toList());

        return Optional.ofNullable(newsList)
                .map(List::isEmpty)
                .map(isEmpty -> isEmpty ? "There are no news yet" : newsList)
                .orElse("There are no news yet");
    }

    @Override
    @PostMapping("/news")
    public NewsAPIDTO createNews(@RequestParam String name, @RequestParam String info) {
        return newsMapper.newsServiceDTO2NewsAPIDTO(newsService.createNews(name, info));
    }

    @Override
    @PutMapping("/news")
    public NewsAPIDTO editNews(@RequestParam String name, @RequestParam String info, @RequestParam Boolean deleted) {
        return newsMapper.newsServiceDTO2NewsAPIDTO(newsService.editNews(name, info, deleted));
    }
}