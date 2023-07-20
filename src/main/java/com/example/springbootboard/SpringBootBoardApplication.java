package com.example.springbootboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SpringBootBoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootBoardApplication.class, args);
    }

}
