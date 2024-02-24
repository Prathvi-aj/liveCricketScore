package com.cricket.livescore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin("*")
public class LiveScoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(LiveScoreApplication.class, args);
    }

}
