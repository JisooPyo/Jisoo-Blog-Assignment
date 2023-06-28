package com.example.jisoo_blog.controller;

import com.example.jisoo_blog.dto.LoginRequestDto;
import com.example.jisoo_blog.dto.SignupRequestDto;
import com.example.jisoo_blog.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/JisooBlog")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user/signup")
    public String signup(@RequestBody SignupRequestDto requestDto){
        userService.signup(requestDto);
        return "회원가입 완료!";
    }

    @PostMapping("/user/login")
    public String login(@RequestBody LoginRequestDto requestDto, HttpServletResponse res){
        try {
            userService.login(requestDto,res);
            System.out.println("로그인 성공");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return "로그인 완료!";
    }
}
