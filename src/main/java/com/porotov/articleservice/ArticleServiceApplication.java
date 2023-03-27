package com.porotov.articleservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ArticleServiceApplication {

    private final static Logger log = LoggerFactory.getLogger(ArticleServiceApplication.class.getName());

    public static void main(String[] args) {
        log.info("Starting Article Service ...");
        SpringApplication.run(ArticleServiceApplication.class, args);
    }

}
