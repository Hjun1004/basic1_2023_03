package com.ll.basic1.boundedContext.article.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Article {
    @Id // id가 메인키다. 주된 키다.
    @GeneratedValue(strategy = IDENTITY) // Auto_INCREMENT
    private long id;

    @CreatedDate
    private LocalDateTime createDate; // 시간을 저장하기 위한 자료형으로 LocalDateTime으로 설정
    @LastModifiedDate  // 이렇게 하면 LocalDateTime.now()
    private LocalDateTime modifyDate;
    private String title;
    private String body;
}
