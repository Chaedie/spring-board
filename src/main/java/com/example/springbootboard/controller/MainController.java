package com.example.springbootboard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller("/")
public class MainController {

    @GetMapping()
    public String getIndexPage(Model model) {
        return "index";
    }


}
