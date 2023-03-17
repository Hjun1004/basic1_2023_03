package com.ll.basic1.boundedContext.article.repository;


import com.ll.basic1.boundedContext.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    // 이건 외우자
    // AricleRepository를 JPA가 다루게 하기위해 interface
}
