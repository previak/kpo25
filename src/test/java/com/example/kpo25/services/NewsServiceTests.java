package com.example.kpo25.services;

import com.example.kpo25.data.entities.NewsEntity;
import com.example.kpo25.data.repositories.NewsRepository;
import com.example.kpo25.mappers.NewsMapper;
import com.example.kpo25.services.dto.NewsServiceDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class NewsServiceTests {

    @Mock
    private NewsRepository newsRepository;

    @Mock
    private NewsMapper newsMapper;

    @InjectMocks
    private NewsServiceImpl newsService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllNews() {
        NewsEntity newsEntity1 = new NewsEntity();
        NewsEntity newsEntity2 = new NewsEntity();
        when(newsRepository.findAll()).thenReturn(Arrays.asList(newsEntity1, newsEntity2));
        when(newsMapper.newsEntity2NewsServiceDTO(newsEntity1)).thenReturn(new NewsServiceDTO());
        when(newsMapper.newsEntity2NewsServiceDTO(newsEntity2)).thenReturn(new NewsServiceDTO());

        List<NewsServiceDTO> newsList = newsService.getAllNews();

        assertEquals(2, newsList.size());
        verify(newsRepository, times(1)).findAll();
        verify(newsMapper, times(2)).newsEntity2NewsServiceDTO(any(NewsEntity.class));
    }

    @Test
    public void testCreateNews() {
        String name = "Test News";
        String info = "Test Info";
        NewsEntity newsEntity = new NewsEntity();
        when(newsMapper.newsEntity2NewsServiceDTO(any(NewsEntity.class))).thenReturn(new NewsServiceDTO());
        when(newsRepository.save(any(NewsEntity.class))).thenReturn(newsEntity);

        NewsServiceDTO createdNews = newsService.createNews(name, info);

        verify(newsRepository, times(1)).save(any(NewsEntity.class));
        verify(newsMapper, times(1)).newsEntity2NewsServiceDTO(any(NewsEntity.class));
    }

    @Test
    public void testEditNews() {
        String name = "Test News";
        String info = "Updated Info";
        Boolean deleted = false;
        NewsEntity newsEntity = new NewsEntity();
        when(newsRepository.findByName(name)).thenReturn(newsEntity);
        when(newsMapper.newsEntity2NewsServiceDTO(any(NewsEntity.class))).thenReturn(new NewsServiceDTO());
        when(newsRepository.save(any(NewsEntity.class))).thenReturn(newsEntity);

        NewsServiceDTO editedNews = newsService.editNews(name, info, deleted);

        verify(newsRepository, times(1)).findByName(name);
        verify(newsRepository, times(1)).save(any(NewsEntity.class));
        verify(newsMapper, times(1)).newsEntity2NewsServiceDTO(any(NewsEntity.class));
    }

    @Test
    public void testGetAllNotDeletedNews() {
        NewsEntity newsEntity1 = new NewsEntity();
        newsEntity1.setDeleted(false);
        NewsEntity newsEntity2 = new NewsEntity();
        newsEntity2.setDeleted(true);
        when(newsRepository.findAll()).thenReturn(Arrays.asList(newsEntity1, newsEntity2));
        when(newsMapper.newsEntity2NewsServiceDTO(newsEntity1)).thenReturn(new NewsServiceDTO());

        List<NewsServiceDTO> notDeletedNews = newsService.getAllNotDeletedNews();

        assertEquals(1, notDeletedNews.size());
        assertTrue(notDeletedNews.get(0) instanceof NewsServiceDTO);
        verify(newsRepository, times(1)).findAll();
        verify(newsMapper, times(1)).newsEntity2NewsServiceDTO(any(NewsEntity.class));
    }
}
