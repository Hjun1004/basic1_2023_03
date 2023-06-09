package com.ll.basic1.boundedContext.article.repository;


import com.ll.basic1.boundedContext.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    // 이건 외우자
    // AricleRepository를 JPA가 다루게 하기위해 interface로 ArticleRepository를 생성하고

    // JpaRepository를 상속받은 애는 Article을 다루고 Article 클래스의 주키는 Long을 다룬다.
    // extends JpaRepository<다루는 클래스, 주 키>
}
