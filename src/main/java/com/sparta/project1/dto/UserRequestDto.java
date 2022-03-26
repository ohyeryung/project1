package com.sparta.project1.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Setter
@Getter
@NoArgsConstructor
public class UserRequestDto {

    @NotBlank(message = "닉네임을 입력해주세요.")
    @Pattern(regexp = "(?=.*[0-9])|(?=.*[a-zA-Z]).{3,}", message = "닉네임은 영문자와 숫자를 조합한 3자 이상으로 입력해주세요.")
    private String username;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Size(min =4, message = "비밀번호는 4자리 이상 입력해주세요.")
    private String password;

    private String password_check;

}