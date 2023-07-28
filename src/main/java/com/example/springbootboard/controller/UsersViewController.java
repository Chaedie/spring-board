package com.example.springbootboard.controller;

import com.example.springbootboard.domain.teams.dto.TeamResponseDTO;
import com.example.springbootboard.domain.teams.service.TeamService;
import com.example.springbootboard.domain.users.dto.UserRequestDTO;
import com.example.springbootboard.domain.users.dto.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@Controller("/users")
public class UsersViewController {

    private final TeamService teamService;
    private final HttpSession session;

    @GetMapping("/join")
    public String getUserJoinPage(UserRequestDTO userRequestDTO, Model model) {
        List<TeamResponseDTO> teamList = teamService.getTeamList();
        model.addAttribute("teamList", teamList);
        model.addAttribute("userRequestDTO", userRequestDTO);

        return "users/join";
    }

    @GetMapping("/login")
    public String login() {
        UserResponseDTO userResponseDTO = (UserResponseDTO) session.getAttribute("user");
        if (userResponseDTO != null) {
            return "redirect:/";
        }
        return "users/login";
    }

    @GetMapping("/{userId}")
    public String getUserPageByUserId(@PathVariable Long userId) {
        return "myPage";
    }


    // @GetMapping("/logout")
    // public String performLogout() {
    //     session.removeAttribute("user");
    //     return "redirect:/";
    // }

}
