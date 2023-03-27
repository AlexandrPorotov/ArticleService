package com.porotov.articleservice.service.articleService;

import com.porotov.articleservice.dto.ArticleDTO;
import com.porotov.articleservice.exeption.apiExeptions.exeptions.ResourceAlreadyExistsException;
import com.porotov.articleservice.exeption.apiExeptions.exeptions.ResourceNotFoundException;
import com.porotov.articleservice.model.Article;
import com.porotov.articleservice.repository.ArticleRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**@author Alexandr Porotov
 * @version  pre-alpha
 * */
@Service
@AllArgsConstructor
public class ArticleServiceImpl implements ArticleService{

    private final ArticleRepository articleRepository;

    @Override
    public List<Article> getAllArticles() {

        return articleRepository.findAll();

    }

    @Override
    public Article getArticleById(Long id) {
        return articleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Article with id '" + id + "' not found"));
    }

    @Override
    public Article createArticle(ArticleDTO articleDTO) {

        if (articleRepository.findByUrl(articleDTO.getUrl()).isPresent()) {
            throw new ResourceAlreadyExistsException("Article with url '" + articleDTO.getUrl() + "' already exists");
        }
        Article article = new Article();
        article.setTitle(articleDTO.getTitle());
        article.setUrl(articleDTO.getUrl());
        article.setDateChangeTime(LocalDateTime.now());

        return articleRepository.save(article);
    }

    @Override
    public Article updateArticle(Long id, ArticleDTO articleDTO) {

        Article existingArticle = articleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Article with id '" + id + "' not found"));
        if (articleRepository.findByUrl(articleDTO.getUrl()).isPresent()) {
            throw new ResourceAlreadyExistsException("Article with url '" + articleDTO.getUrl() + "' already exists");
        }
        existingArticle.setTitle(articleDTO.getTitle());
        existingArticle.setUrl(articleDTO.getUrl());
        existingArticle.setDateChangeTime(LocalDateTime.now());

        return articleRepository.save(existingArticle);

    }

    @Override
    public void deleteArticle(Long id) {

        Article existingArticle = articleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Article with id '" + id + "' not found"));
        articleRepository.delete(existingArticle);

    }
}
