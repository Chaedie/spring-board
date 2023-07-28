package com.example.springbootboard.domain.users;

import com.example.springbootboard.domain.users.dto.UserRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public String signUpUser(@Validated UserRequestDTO userRequestDTO, Errors errors) {
        if (errors.hasErrors()) {
            return "redirect:login?error";
        }
        userService.signUpUser(userRequestDTO);
        return "redirect:login";
    }


}
