package com.example.springbootboard.controller;

import com.example.springbootboard.data.dto.UserRequestDTO;
import com.example.springbootboard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/join")
    public String getUserJoinPage(Model model) {
        List<String> teamList = userService.getTeamNameList();
        model.addAttribute("teamList", teamList);
        
        return "users/join";
    }

    @GetMapping("/{userId}")
    public String getUserPageByUserId(@PathVariable Long userId) {
        return "myPage";
    }
}
