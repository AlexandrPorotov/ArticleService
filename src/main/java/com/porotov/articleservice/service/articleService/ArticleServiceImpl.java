package com.porotov.articleservice.service.articleService;

import com.porotov.articleservice.dto.ArticleDTO;
import com.porotov.articleservice.exeption.ResourceAlreadyExistsException;
import com.porotov.articleservice.exeption.ResourceNotFoundException;
import com.porotov.articleservice.model.Article;
import com.porotov.articleservice.repository.ArticleRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


/**@author Alexandr Porotov
 * @version  pre-alpha
 * */
@Service
@AllArgsConstructor
public class ArticleServiceImpl implements ArticleService{

    private final ArticleRepository articleRepository;
    private static final Logger log = LoggerFactory.getLogger(ArticleServiceImpl.class.getName());


    @Override
    public List<Article> getAllArticles() {
    
        log.debug("Getting all articles ");
        List<Article> articles = articleRepository.findAll();
        log.debug("Found {} articles",articles.size());
        
        return articles;
        
    }

    @Override
    public Article getArticleById(Long id) {

        log.debug("Retrieving article by ID: {}", id);

        Optional<Article> optionalArticle = articleRepository.findById(id);

        if(optionalArticle.isEmpty()){

            log.warn("Could not find article with ID: {}", id);
            throw new ResourceNotFoundException("Could not find article with ID: " + id);

        }

        Article article = optionalArticle.get();
        log.debug("Retrieved article: {}", article);

        return article;

    }

    @Override
    public Article createArticle(ArticleDTO articleDTO) {

        log.debug("Creating article {}", articleDTO);

        if (articleRepository.findByUrl(articleDTO.getUrl()).isPresent()) {
            log.warn("Article with url {} already exist.", articleDTO.getUrl());
            throw new ResourceAlreadyExistsException("Article with url '" + articleDTO.getUrl() + "' already exists");
        }

        Article article = new Article();

        article.setTitle(articleDTO.getTitle());
        article.setUrl(articleDTO.getUrl());
        article.setDateChangeTime(LocalDateTime.now());


        articleRepository.save(article);

        log.debug("Article with ID: {} created", article.getId());

        return article;

    }

    @Override
    public Article updateArticle(Long id, ArticleDTO articleDTO) {

        log.debug("Updating article with ID: {}", id);

        Optional<Article> optionalArticle = articleRepository.findById(id);

        if(optionalArticle.isEmpty()){

            log.warn("Could not find article with ID: {}", id);
            throw new ResourceNotFoundException("Could not find article with ID: " + id);

        }

        if (articleRepository.findByUrl(articleDTO.getUrl()).isPresent()) {
            log.warn("Article with url {} already exist.", articleDTO.getUrl());
            throw new ResourceAlreadyExistsException("Article with url '" + articleDTO.getUrl() + "' already exists");
        }

        Article article = optionalArticle.get();

        article.setTitle(articleDTO.getTitle());
        article.setUrl(articleDTO.getUrl());
        article.setDateChangeTime(LocalDateTime.now());

        articleRepository.save(article);

        log.debug("Article with ID: {} updated", article.getId());

        return article;



    }

    @Override
    public void deleteArticle(Long id) {

        log.debug("Deleting article with ID: {}", id);

        Optional<Article> optionalArticle = articleRepository.findById(id);

        if(optionalArticle.isEmpty()){

            log.warn("Could not find article with ID: {}", id);
            throw new ResourceNotFoundException("Could not find article with ID: " + id);

        }

        articleRepository.delete(optionalArticle.get());

        log.debug("Article with ID: {} deleted", id);

    }
}
