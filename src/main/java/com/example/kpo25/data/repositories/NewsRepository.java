package com.example.kpo25.data.repositories;

import com.example.kpo25.data.entities.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<NewsEntity, Long> {
    NewsEntity findByName(String name);
}
