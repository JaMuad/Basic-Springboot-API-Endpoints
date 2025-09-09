package com.mm.apibasic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private ApiService apiService;

    @GetMapping("/info")
    public ResponseEntity<ApiResponse> getInfo() {
        ApiResponse response = apiService.getApiResponse();
        return ResponseEntity.ok(response);
    }
}