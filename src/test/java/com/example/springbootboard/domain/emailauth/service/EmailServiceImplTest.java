package com.example.springbootboard.domain.emailauth.service;

import com.example.springbootboard.domain.emailauth.EmailAuthRepository;
import com.example.springbootboard.domain.emailauth.dto.UserEmailRequestDTO;
import com.example.springbootboard.utils.RedisStringUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class EmailServiceImplTest {

    @Autowired
    private RedisStringUtil redisStringUtil;

    @Autowired
    EmailUtils emailUtils;

    @Autowired
    EmailAuthRepository emailAuthRepository;

    @Autowired
    EmailServiceRedisImpl emailService;

    @Transactional
    @Test
    public void 인증메일_발송후_sendMail_메서드가_실행되면_패스() throws Exception {
        // given
        /**
         * 1) Testing 시 실제 Email 전송은 되지 않았으면 좋겠다고 생각했다. 
         * 이를 위해 Mockito 를 활용해 Mock 객체로 메일 전송한 것 처럼 Test 진행이 가능했다.
         * 2) mockEmailUtils 내부에 createAuthCode() 메서드를 활용해 authCode를 만드는 부분이 있었다.
         * 해당 return 값을 redis 에 set 해주는 코드가 있었는데,
         * redis 에 null 값을 set 하려니 IllegalArgumentException 이 발생하며 TEST 진행이 안되었다.
         * 이에 when() 메서드를 활용해 createAuthCode()의 return 값을 stubbing 해주었고, 
         * 해당 방법을 통해 원하는 Test 를 진행할 수 있었다.
         */
        EmailUtils mockEmailUtils = mock(EmailUtils.class);
        when(mockEmailUtils.createAuthCode()).thenReturn("testAuthCode");

        String authCode = mockEmailUtils.createAuthCode();
        String userEmail = "test@test.com";
        EmailServiceRedisImpl emailServiceRedis = new EmailServiceRedisImpl(redisStringUtil, mockEmailUtils);
        UserEmailRequestDTO userEmailRequest = UserEmailRequestDTO.builder()
                .authCode(authCode)
                .userEmail(userEmail)
                .build();

        // when
        emailServiceRedis.sendSimpleMessage(userEmailRequest);

        // then
        verify(mockEmailUtils).sendMail(userEmail, authCode);
    }

    @Transactional
    @Test
    public void 인증코드가_같으면_isVerified_true() throws Exception {
        // given
        EmailUtils mockEmailUtils = mock(EmailUtils.class);
        when(mockEmailUtils.createAuthCode()).thenReturn("testAuthCode");

        EmailServiceRedisImpl emailServiceRedis = new EmailServiceRedisImpl(redisStringUtil, mockEmailUtils);
        String authCode = mockEmailUtils.createAuthCode();
        String userEmail = "test@test.com";
        UserEmailRequestDTO userEmailRequest = UserEmailRequestDTO.builder()
                .userEmail(userEmail)
                .authCode(authCode)
                .build();
        emailServiceRedis.sendSimpleMessage(userEmailRequest);

        // when
        boolean isExists = emailService.isVerifiedCode(userEmailRequest);

        // then
        assertThat(isExists).isTrue();
    }
}
