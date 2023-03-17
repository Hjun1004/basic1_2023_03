package com.ll.basic1.boundedContext.article.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    @Id // id가 메인키다. 주된 키다.
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    private LocalDateTime createDate; // 시간을 저장하기 위한 자료형으로 LocalDateTime으로 설정
    private LocalDateTime modifyDate;
    private String title;
    private String body;
}
