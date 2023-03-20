package com.ll.basic1.boundedContext.member.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@AllArgsConstructor
//@Data //Getter Setter 둘 다 가지고 있다.
@Getter
@ToString
@Entity
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Builder
public class Member {

    @Id // id가 메인키다. 주된 키다.
    @GeneratedValue(strategy = IDENTITY) // Auto_INCREMENT
    private long id;
    @CreatedDate
    private LocalDateTime createDate; // 시간을 저장하기 위한 자료형으로 LocalDateTime으로 설정
    @LastModifiedDate  // 이렇게 하면 LocalDateTime.now()
    private LocalDateTime modifyDate;
    @Column(unique = true)
    private String username;
    private String password;


}
