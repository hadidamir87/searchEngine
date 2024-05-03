package com.example.sina;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

import java.io.IOException;

@SpringBootApplication
@EnableMongoAuditing
public class SinaApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(SinaApplication.class, args);


    }
}





