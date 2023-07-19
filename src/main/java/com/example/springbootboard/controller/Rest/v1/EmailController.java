package com.example.springbootboard.controller.Rest.v1;

import com.example.springbootboard.data.dto.UserEmailRequestDTO;
import com.example.springbootboard.service.EmailService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/v1/mail")
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
    public ResponseEntity<Object> send(@RequestBody UserEmailRequestDTO userEmailRequestDTO) throws Exception {
        System.out.println("userEmail = " + userEmailRequestDTO.getUserEmail());

        emailService.sendSimpleMessage(userEmailRequestDTO);

        return ResponseEntity.ok("OK");
    }

    @PostMapping("/verifyEmail")
    @ApiOperation(value = "이메일 인증 확인 절차", notes = "이메일로 받은 인증 코드를 제출")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<Object> verifyEmail(@RequestBody UserEmailRequestDTO userEmailDTO) {
        if (emailService.isVerifiedCode(userEmailDTO)) {
            return ResponseEntity.ok("OK");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fail to Verification");
    }
}
