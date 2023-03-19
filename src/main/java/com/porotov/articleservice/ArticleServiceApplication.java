package com.porotov.articleservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ArticleServiceApplication {

    private static final Logger logger = LoggerFactory.getLogger(ArticleServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ArticleServiceApplication.class, args);
        logger.info("Application started successfully");
    }

}
