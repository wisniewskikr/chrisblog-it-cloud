package com.example.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.dtos.HelloWorldDto;
import com.example.services.HelloWorldService;

@Controller
public class HelloWorldController {

    Logger logger = LoggerFactory.getLogger(HelloWorldController.class);

    private HelloWorldService helloWorldService;
    private Environment environment; 

    public HelloWorldController(HelloWorldService helloWorldService, Environment environment) {
        this.helloWorldService = helloWorldService;
        this.environment = environment;
    }

    @GetMapping
    String findById(Model model) {

        logger.info("Called FIRST method HelloWorldController.findById()");

        HelloWorldDto helloWorldDto = helloWorldService.findById(1L);
        model.addAttribute("message", helloWorldDto.text());        
        model.addAttribute("portSecond", helloWorldDto.portSecond());
        model.addAttribute("portFirst", environment.getProperty("local.server.port"));
        return "helloworld";

    }

}
