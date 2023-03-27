package com.porotov.articleservice.service.articleService;

import com.porotov.articleservice.dto.ArticleDTO;
import com.porotov.articleservice.model.Article;

import java.util.List;

public interface ArticleService {

    List<Article> getAllArticles();
    Article getArticleById(Long id);
    Article createArticle(ArticleDTO articleDTO);
    Article updateArticle(Long id, ArticleDTO articleDTO);
    void deleteArticle(Long id);

}
