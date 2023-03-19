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

    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    public ArticleController(ArticleService articleService){
        this.articleService = articleService;
    }

    @GetMapping("/articles")
    public List<Article> getAllArticles(HttpServletRequest request) {
        logger.info("GET request; Path: '/articles' from " + request.getRemoteAddr());
        return articleService.getAllArticles();
    }

    @GetMapping("/articles/{id}")
    public Article getArticleById(@PathVariable Long id) {
        return articleService.getArticleById(id);
    }

    @PostMapping("/articles")
    public Article createArticle(@RequestBody ArticleDTO articleDTO) {
        return articleService.createArticle(articleDTO);
    }

    @PutMapping("/articles/{id}")
    public Article updateArticle(@PathVariable Long id, @RequestBody ArticleDTO articleDTO) {
        return articleService.updateArticle(id, articleDTO);
    }

    @DeleteMapping("/articles/{id}")
    public void deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
    }

}
