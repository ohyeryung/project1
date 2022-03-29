package com.sparta.project1.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentRequestDto {
    private String content;
    private String writer;
    private Long articleId;
}
