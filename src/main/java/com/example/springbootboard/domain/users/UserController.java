package com.example.springbootboard.domain.users;

import com.example.springbootboard.common.CommonResponse;
import com.example.springbootboard.domain.users.dto.UserRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@Controller
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public String signUpUser(@Validated UserRequestDTO userRequestDTO, Errors errors) {
        if (errors.hasErrors()) {
            return "redirect:/users/login?error";
        }
        userService.signUpUser(userRequestDTO);
        return "redirect:/users/login";
    }

    @PostMapping("/join/api")
    public CommonResponse<Object> signUpUserAPI(@RequestBody UserRequestDTO userRequest) {
        return CommonResponse.success(userService.signUpUser(userRequest));
    }
}
