package com.example.springbootboard.service.Impl;

import com.example.springbootboard.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Random;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    JavaMailSender emailSender;

    public static final String ePw = createKey();

    @Value("${AdminMail.id}")
    private String id;
    @Value("${AdminMail.password}")
    private String password;

    private MimeMessage createMessage(String to) throws Exception {
        System.out.println("보내는 대상 : " + to);
        System.out.println("인증 번호 : " + ePw);
        MimeMessage message = emailSender.createMimeMessage();

        message.addRecipients(RecipientType.TO, to);// 보내는 대상
        message.setSubject("스프링 보드 가입 인증 메일");// 제목

        StringBuilder sb = new StringBuilder();
        sb.append("<div style='margin:20px;'>");
        sb.append("<h1> 안녕하세요 Spring-Board입니다.. </h1><br>");
        sb.append("<p>아래 코드를 복사해 입력해주세요<p><br>");
        sb.append("<p>감사합니다.<p><br>");
        sb.append("<div align='center' style='border:1px solid black); font-family:verdana');>");
        sb.append("<h3 style='color:blue);'>회원가입 인증 코드입니다.</h3>");
        sb.append("<div style='font-size:130%'>");
        sb.append("CODE : <strong>");
        sb.append(ePw);
        sb.append("</strong><div><br/></div>");
        message.setText(sb.toString(), "utf-8", "html");// 내용
        message.setFrom(new InternetAddress(id, "Spring-Boards"));// 보내는 사람

        return message;
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

    @Override
    public String sendSimpleMessage(String to) throws Exception {
        MimeMessage message = createMessage(to);
        try {// 예외처리
            emailSender.send(message);
        } catch (MailException es) {
            es.printStackTrace();
            throw new IllegalArgumentException();
        }
        return ePw;
    }
}
