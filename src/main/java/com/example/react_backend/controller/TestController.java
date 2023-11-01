package com.example.react_backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("/test")
    public void test(){
        System.out.println("TestController=====================");
    }
}
