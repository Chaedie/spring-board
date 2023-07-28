package com.example.springbootboard.domain.emailauth;

import com.example.springbootboard.common.ResponseDTO;
import com.example.springbootboard.domain.emailauth.service.EmailService;
import com.example.springbootboard.domain.users.dto.UserEmailRequestDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/mail")
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/send")
    @ApiOperation(value = "회원 가입시 이메일 인증", notes = "기존사용하고 있는 이메일을 통해 인증")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseDTO<String> sendEmail(@RequestBody UserEmailRequestDTO userEmailRequestDTO) throws Exception {
        emailService.sendSimpleMessage(userEmailRequestDTO);

        return ResponseDTO.of(200, "Send Email SUCCESS", "OK");
    }

    @PostMapping("/verifyEmail")
    @ApiOperation(value = "이메일 인증 확인 절차", notes = "이메일로 받은 인증 코드를 제출")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseDTO<String> verifyEmail(@RequestBody UserEmailRequestDTO userEmailDTO) {
        if (emailService.isVerifiedCode(userEmailDTO)) {
            return ResponseDTO.of(200, "Verification SUCCESS", "OK");
        }
        return ResponseDTO.of(400, "Verification Fail", "Not OK");
    }
}
