package com.sparta.project1.model;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
public class User {

    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    // nullable: null 허용 여부
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique= true)
    private Long kakaoId;

    // 일반 로그인
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.kakaoId = null;
    }

    // 카카오 로그인
    public User(String username, String password, Long kakaoId) {
        this.username = username;
        this.password = password;
        this.kakaoId = kakaoId;
    }

}

