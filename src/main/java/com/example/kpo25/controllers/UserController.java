package com.example.kpo25.controllers;

import com.example.kpo25.api.UserAPI;
import com.example.kpo25.api.dto.NewsAPIDTO;
import com.example.kpo25.mappers.NewsMapper;
import com.example.kpo25.services.interfaces.NewsService;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("/user")
public class UserController implements UserAPI {

    NewsService newsService;
    NewsMapper newsMapper;

    @Override
    @GetMapping("/news")
    public Object getAllNotDeletedNews() {
        List<NewsAPIDTO> newsList = newsService.getAllNotDeletedNews().stream()
                .map(newsMapper::newsServiceDTO2NewsAPIDTO)
                .collect(Collectors.toList());

        return Optional.ofNullable(newsList)
                .map(List::isEmpty)
                .map(isEmpty -> isEmpty ? "There are no news yet" : newsList)
                .orElse("There are no news yet");
    }
}
