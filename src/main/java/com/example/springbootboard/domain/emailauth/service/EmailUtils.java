package com.example.springbootboard.domain.emailauth.service;

public interface EmailUtils {

    public void sendMail(String userEmail, String authCode) throws Exception;

    public String createAuthCode();
}
