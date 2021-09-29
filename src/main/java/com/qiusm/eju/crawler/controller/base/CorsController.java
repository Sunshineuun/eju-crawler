package com.qiusm.eju.crawler.controller.base;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cors")
public class CorsController {
    @GetMapping("test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("cors test");
    }
}
