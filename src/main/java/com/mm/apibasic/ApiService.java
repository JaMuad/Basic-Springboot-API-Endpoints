package com.mm.apibasic;

import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class ApiService {

    public ApiResponse getApiResponse() {
        String email = "mm@hotmail.com";
        String currentDatetime = Instant.now().toString(); // ISO 8601 format
        String githubUrl = "https://github.com/JaMuad/apibasic";

        return new ApiResponse(email, currentDatetime, githubUrl);
    }
}