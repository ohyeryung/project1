package com.sparta.project1.model;

import com.sparta.project1.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class Comment extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    private Long articleId;

    public Comment(CommentRequestDto requestDto) {
        this.content = requestDto.getContent();
        this.writer = requestDto.getWriter();
        this.articleId = requestDto.getArticleId();
    }

    public void update(CommentRequestDto requestDto) {
        this.articleId = requestDto.getArticleId();
        this.writer = requestDto.getWriter();
        this.content = requestDto.getContent();
    }
}
