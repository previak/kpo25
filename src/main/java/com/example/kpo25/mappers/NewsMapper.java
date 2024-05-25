package com.example.kpo25.mappers;

import com.example.kpo25.api.dto.NewsAPIDTO;
import com.example.kpo25.data.entities.NewsEntity;
import com.example.kpo25.services.dto.NewsServiceDTO;
import com.example.kpo25.services.interfaces.NewsService;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NewsMapper {

    NewsAPIDTO newsEntity2NewsAPIDTO(NewsEntity newsEntity);
    NewsServiceDTO newsEntity2NewsServiceDTO(NewsEntity newsEntity);
    NewsAPIDTO newsServiceDTO2NewsAPIDTO(NewsServiceDTO newsServiceDTO);
    NewsServiceDTO newsAPIDTO2NewsServiceDTO(NewsAPIDTO newsAPIDTO);
}
