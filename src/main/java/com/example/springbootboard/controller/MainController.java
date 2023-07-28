package com.example.springbootboard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/")
@Controller
public class MainController {

    @GetMapping()
    public String getIndexPage(Model model) {
        return "index";
    }

    @GetMapping("test")
    public String getIndexPage222(Model model) {
        return "boards/writePostPageRefactor";
    }


}
