package com.graynaud.eu4savedisplayerbo.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/test")
public class TestController {
    @GetMapping
    public ResponseEntity test() {
        return new ResponseEntity(HttpStatus.OK);
    }
}
