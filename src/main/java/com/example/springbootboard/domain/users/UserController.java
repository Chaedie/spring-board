package com.example.springbootboard.domain.users;

import com.example.springbootboard.common.aop.TimeClock;
import com.example.springbootboard.domain.teams.dto.TeamResponseDTO;
import com.example.springbootboard.domain.teams.service.TeamService;
import com.example.springbootboard.domain.users.dto.UserRequestDTO;
import com.example.springbootboard.domain.users.dto.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final TeamService teamService;
    private final HttpSession session;

    @TimeClock
    @GetMapping("/join")
    public String getUserJoinPage(UserRequestDTO userRequestDTO, Model model) {
        List<TeamResponseDTO> teamList = teamService.getTeamList();
        model.addAttribute("teamList", teamList);
        model.addAttribute("userRequestDTO", userRequestDTO);

        return "users/join";
    }

    @PostMapping("/join")
    public String signUpUser(@Validated UserRequestDTO userRequestDTO, Errors errors) {
        if (errors.hasErrors()) {
            return "redirect:login?error";
        }
        userService.signUpUser(userRequestDTO);
        return "redirect:login";
    }

    @GetMapping("/login")
    public String login() {
        UserResponseDTO userResponseDTO = (UserResponseDTO) session.getAttribute("user");
        if (userResponseDTO != null) {
            return "redirect:/";
        }
        return "users/login";
    }

    @GetMapping("/logout")
    public String performLogout() {
        session.removeAttribute("user");
        return "redirect:/";
    }

    @GetMapping("/{userId}")
    public String getUserPageByUserId(@PathVariable Long userId) {
        return "myPage";
    }
}
