package com.example.jisoo_blog.service;

import com.example.jisoo_blog.dto.LoginRequestDto;
import com.example.jisoo_blog.dto.SignupRequestDto;
import com.example.jisoo_blog.jwt.JwtUtil;
import com.example.jisoo_blog.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public void signup(SignupRequestDto requestDto) {

    }

    public void login(LoginRequestDto requestDto, HttpServletResponse res) {

    }
}
