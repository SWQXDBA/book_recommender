package com.example.book_recommender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;



@EnableWebSecurity
@SpringBootApplication
public class BookRecommenderApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookRecommenderApplication.class, args);
    }

}
