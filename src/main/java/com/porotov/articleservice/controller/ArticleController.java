package com.porotov.articleservice.controller;

import com.porotov.articleservice.dto.ArticleDTO;
import com.porotov.articleservice.model.Article;
import com.porotov.articleservice.service.articleService.ArticleService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

/**@author Alexandr Porotov
 * @version  pre-alpha
 * */
@RestController
@RequestMapping("/api")
public class ArticleController {

    private final ArticleService articleService;

    private static final Logger log = LoggerFactory.getLogger(ArticleController.class.getName());

    @Autowired
    public ArticleController(ArticleService articleService){
        this.articleService = articleService;
    }

    @GetMapping("/articles")
    public ResponseEntity<List<Article>> getAllArticles(HttpServletRequest request) {

        log.info("Getting all articles");
        List<Article> articles = articleService.getAllArticles();
        log.info("Found {} articles", articles.size());

        return new ResponseEntity<>(articles,HttpStatus.OK);

    }

    @GetMapping("/articles/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable Long id) {

        log.info("Getting article with ID: {}", id);
        Article articleFromDB = articleService.getArticleById(id);
        log.info("Found article: {}", articleFromDB);

        return new ResponseEntity<>(articleFromDB,HttpStatus.OK);

    }

    @PostMapping("/articles")
    public ResponseEntity<Article> createArticle(@RequestBody ArticleDTO articleDTO) {

        log.info("Creating article: {}", articleDTO);
        Article createdArticle = articleService.createArticle(articleDTO);
        log.info("Created article: {}", createdArticle);

        return new ResponseEntity<>(createdArticle, HttpStatus.CREATED);

    }

    @PutMapping("/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody ArticleDTO articleDTO) {

        log.info("Updating article with ID: {}, new data {}",id,articleDTO);
        Article updatedArticle = articleService.updateArticle(id,articleDTO);
        log.info("Updated article {}", updatedArticle);

        return new ResponseEntity<>(updatedArticle, HttpStatus.OK);

    }

    @DeleteMapping("/articles/{id}")
    public ResponseEntity<?> deleteArticle(@PathVariable Long id) {

        log.info("Deleting article with ID: {}", id);
        articleService.deleteArticle(id);
        log.info("Article deleted successfully");

        return new ResponseEntity<>(HttpStatus.OK);

    }

}
