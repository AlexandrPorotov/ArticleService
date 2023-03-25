package com.porotov.articleservice.controller;

import com.porotov.articleservice.dto.ArticleDTO;
import com.porotov.articleservice.model.Article;
import com.porotov.articleservice.service.articleService.ArticleService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
        log.debug("Loading Article Controller");
        this.articleService = articleService;
    }

    @GetMapping("/articles")
    public List<Article> getAllArticles(HttpServletRequest request) {

        log.info("Getting all articles");
        List<Article> articles = articleService.getAllArticles();
        log.info("Found {} articles", articles.size());

        return articles;

    }

    @GetMapping("/articles/{id}")
    public Article getArticleById(@PathVariable Long id) {

        log.info("Getting article with ID: {}", id);
        Article articleFromDB = articleService.getArticleById(id);
        log.info("Found article: {}", articleFromDB);

        return articleFromDB;

    }

    @PostMapping("/articles")
    public Article createArticle(@RequestBody ArticleDTO articleDTO) {

        log.info("Creating article: {}", articleDTO);
        Article createdArticle = articleService.createArticle(articleDTO);
        log.info("Created article: {}", createdArticle);

        return createdArticle;

    }

    @PutMapping("/articles/{id}")
    public Article updateArticle(@PathVariable Long id, @RequestBody ArticleDTO articleDTO) {

        log.info("Updating article with ID: {}, new data {}",id,articleDTO);
        Article updatedArticle = articleService.updateArticle(id,articleDTO);
        log.info("Updated article {}", updatedArticle);

        return updatedArticle;

    }

    @DeleteMapping("/articles/{id}")
    public void deleteArticle(@PathVariable Long id) {

        log.info("Deleting article with ID: {}", id);
        articleService.deleteArticle(id);
        log.info("Article deleted successfully");

    }

}
