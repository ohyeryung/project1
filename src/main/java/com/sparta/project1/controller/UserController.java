package com.sparta.project1.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.project1.dto.UserRequestDto;
import com.sparta.project1.security.UserDetailsImpl;
import com.sparta.project1.service.UserService;
import com.sparta.project1.validator.SignUpValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final SignUpValidator signUpValidator;
    private final UserService userService;


    // 회원 로그인 페이지
    @GetMapping("/user/login")
    public String login(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails != null) {
            model.addAttribute("loggedIn", true);
            model.addAttribute("message", "이미 로그인 하셨습니다.");
        } else {
            model.addAttribute("loggedIn", false);
        }
        return "login";
    }

    // 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup() {
        return "signup";
    }

    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public String registerUser (@Valid UserRequestDto requestDto, Errors errors, Model model){
        String message = signUpValidator.getValidMessage(requestDto, errors);
        System.out.println(message);
        if (message.equals("회원가입 성공")) {
            userService.registerUser(requestDto);
            return "login";
        }
        model.addAttribute("message", message);
        return "signup";
    }

    @GetMapping("/user/kakao/callback")
    public String kakaoLogin(@RequestParam String code) throws JsonProcessingException {
        userService.kakaoLogin(code);

        return "redirect:/";
    }

    }
