package com.example.springbootboard.service.Impl;

import com.example.springbootboard.data.dto.UserEmailRequestDTO;
import com.example.springbootboard.service.EmailService;
import com.example.springbootboard.utils.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.util.Random;

@Service
@Primary
@RequiredArgsConstructor
public class EmailServiceRedisImpl implements EmailService {

    private final JavaMailSender emailSender;
    private final RedisUtil redisUtil;

    @Value("${AdminMail.id}")
    private String id;
    @Value("${AdminMail.password}")
    private String password;

    @Override
    @Transactional
    public boolean isVerifiedCode(UserEmailRequestDTO userEmailRequestDTO) {
        String authCode = userEmailRequestDTO.getAuthCode();
        String storedAuthCode = redisUtil.getData(userEmailRequestDTO.getUserEmail());

        return authCode.equals(storedAuthCode);
    }

    @Override
    @Transactional
    public void sendSimpleMessage(UserEmailRequestDTO userEmailRequestDTO) throws Exception {
        String authCode = createKey();
        String userEmail = userEmailRequestDTO.getUserEmail();

        redisUtil.setDataExpire(userEmail, authCode, (long) (5 * 60) + 30);

        sendMail(userEmail, authCode);
    }

    private void sendMail(String userEmail, String authCode) throws Exception {

        String subject = "스프링 보드 가입 인증 메일";
        String text = "<div style='margin:20px;'>" +
                "<h1> 안녕하세요 Spring-Board입니다.</h1><br>" +
                "<p>아래 코드를 복사해 입력해주세요<p><br>" +
                "<p>감사합니다.<p><br>" +
                "<div align='center' style='border:1px solid black); font-family:verdana');>" +
                "<h3 style='color:blue);'>회원가입 인증 코드입니다.</h3>" +
                "<div style='font-size:130%'>CODE : <strong>" + authCode + "</strong><div><br/></div>";

        MimeMessage message = emailSender.createMimeMessage();
        message.addRecipients(RecipientType.TO, userEmail);// 보내는 대상
        message.setSubject(subject);// 제목
        message.setText(text, "utf-8", "html");// 내용
        message.setFrom(new InternetAddress("spring-boards.io", "Spring-Boards"));// 보내는 사람

        try {// 예외처리
            emailSender.send(message);
            System.out.println("생성된 AuthCode : " + authCode);
        } catch (MailException es) {
            es.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    public static String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 8; i++) { // 인증코드 8자리
            int index = rnd.nextInt(3); // 0~2 까지 랜덤

            switch (index) {
                case 0:
                    key.append((char) ((int) (rnd.nextInt(26)) + 97));
                    //  a~z  (ex. 1+97=98 => (char)98 = 'b')
                    break;
                case 1:
                    key.append((char) ((int) (rnd.nextInt(26)) + 65));
                    //  A~Z
                    break;
                case 2:
                    key.append((rnd.nextInt(10)));
                    // 0~9
                    break;
            }
        }
        return key.toString();
    }
}
