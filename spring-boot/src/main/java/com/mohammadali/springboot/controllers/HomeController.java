package com.mohammadali.springboot.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
@RequestMapping("/")
public class HomeController {

    @ResponseBody
    @GetMapping
    public String home(){
        log.debug("project is starting");
        return "hello";
    }
}
