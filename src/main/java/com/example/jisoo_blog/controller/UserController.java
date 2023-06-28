package com.example.jisoo_blog.controller;

import com.example.jisoo_blog.dto.LoginRequestDto;
import com.example.jisoo_blog.dto.SignupRequestDto;
import com.example.jisoo_blog.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/JisooBlog")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user/signup")
    public String signup(SignupRequestDto requestDto){
        userService.signup(requestDto);

        return "회원가입 완료";
    }

    @PostMapping("/user/login")
    public String login(LoginRequestDto requestDto, HttpServletResponse res){
        try {
            userService.login(requestDto,res);
        } catch (Exception e){
            return "로그인 실패";
        }
        return "로그인 성공";
    }
}
