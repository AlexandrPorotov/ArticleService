package com.porotov.articleservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;
import java.util.Objects;

/**@author Alexandr Porotov
 * @version  pre-alpha
 * */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity(name = "Article")
@Table(name = "articles", schema = "public")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;

    @Column(name = "article_title")
    private String title;

    @Column(name = "article_url")
    private String url;

    @Column(name = "article_creation_date")
    private LocalDateTime dateCreationTime = LocalDateTime.now();

    @Column(name = "article_change_date")
    private LocalDateTime dateChangeTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Article article = (Article) o;
        return id != null && Objects.equals(id, article.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
