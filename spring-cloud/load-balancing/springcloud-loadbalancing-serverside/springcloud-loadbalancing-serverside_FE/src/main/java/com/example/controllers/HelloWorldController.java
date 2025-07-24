package com.example.controllers;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.dtos.HelloWorldDto;
import com.example.services.HelloWorldService;

@Controller
@RequestMapping("fe")
public class HelloWorldController {

    private HelloWorldService helloWorldService;
    private Environment environment; 

    public HelloWorldController(HelloWorldService helloWorldService, Environment environment) {
        this.helloWorldService = helloWorldService;
        this.environment = environment;
    }

    @GetMapping
    String findById(Model model) {

        HelloWorldDto helloWorldDto = helloWorldService.findById(1L);
        model.addAttribute("message", helloWorldDto.text());        
        model.addAttribute("portBe", helloWorldDto.portBe());
        model.addAttribute("portFe", environment.getProperty("local.server.port"));
        return "helloworld";

    }

}
