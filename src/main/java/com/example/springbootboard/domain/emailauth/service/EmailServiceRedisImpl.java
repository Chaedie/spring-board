package com.example.springbootboard.domain.emailauth.service;

import com.example.springbootboard.domain.emailauth.dto.UserEmailRequestDTO;
import com.example.springbootboard.utils.RedisStringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Primary // 인터페이스에 여러 구현체가 있는경우 @Primary 어노테이션이 있는 빈을 주입한다.
@RequiredArgsConstructor
public class EmailServiceRedisImpl implements EmailService {

    private final RedisStringUtil redisUtil;
    private final EmailUtils emailUtils;

    @Override
    @Transactional
    public void sendSimpleMessage(UserEmailRequestDTO userEmailRequestDTO) throws Exception {
        String newAuthCode = emailUtils.createAuthCode();
        String userEmail = userEmailRequestDTO.getUserEmail();

        // Redis TTL 과 화면단 타이머가 5초 차이 발생 -> duration +5초 
        boolean hasSetNewAuthCode = redisUtil.setDataIfAbsentExpire(userEmail, newAuthCode, (long) (5 * 60) + 5);
        if (hasSetNewAuthCode) {
            emailUtils.sendMail(userEmail, newAuthCode);
            return;
        }
        System.out.println("이미 인증코드 발송이 된 메일입니다.");
    }

    @Override
    @Transactional
    public boolean isVerifiedCode(UserEmailRequestDTO userEmailRequestDTO) {
        String authCode = userEmailRequestDTO.getAuthCode();
        String storedAuthCode = redisUtil.getData(userEmailRequestDTO.getUserEmail());

        return authCode.equals(storedAuthCode);
    }
}
