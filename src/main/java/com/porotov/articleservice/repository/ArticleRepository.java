package com.porotov.articleservice.repository;

import com.porotov.articleservice.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**@author Alexandr Porotov
 * @version  pre-alpha
 * */
@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query("SELECT article FROM Article article WHERE article.url = ?1")
    Optional<Article> findByUrl(String url);

}
