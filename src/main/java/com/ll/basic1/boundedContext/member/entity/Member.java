package com.ll.basic1.boundedContext.member.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
//@Data //Getter Setter 둘 다 가지고 있다.
@Getter
@ToString
public class Member {
    private static int lastId;
    private long id;
    private String username;
    private String password;

    static {
        lastId = 0;
    }

    public Member(String username, String password){
        this(++lastId,username,password);
    }

}
