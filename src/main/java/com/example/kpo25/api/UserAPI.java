package com.example.kpo25.api;

import com.example.kpo25.api.dto.NewsAPIDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

public interface UserAPI {

    Object getAllNotDeletedNews();
}
