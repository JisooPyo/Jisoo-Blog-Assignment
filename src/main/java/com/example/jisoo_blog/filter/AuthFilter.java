package com.example.jisoo_blog.filter;

import com.example.jisoo_blog.entity.User;
import com.example.jisoo_blog.jwt.JwtUtil;
import com.example.jisoo_blog.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Slf4j(topic = "AuthFilter")
@Component
@Order(1)
public class AuthFilter implements Filter {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public AuthFilter(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String url = httpServletRequest.getRequestURI();
        // 회원가입, 로그인 URL은 인증 처리 X
        if (StringUtils.hasText(url) && url.startsWith("/JisooBlog/user")
        ) {
            // 다음 Filter로 이동
            chain.doFilter(request, response);
        } else {
            // 나머지 API 요청은 인증 처리 진행( 게시글 관련 )
            // 토큰 확인
            String tokenValue = jwtUtil.getTokenFromRequest(httpServletRequest);

            // 토큰이 존재하면 검증 시작
            if (StringUtils.hasText(tokenValue)) {
                // JWT 토큰 substring
                String token = jwtUtil.substringToken(tokenValue);

                // 토큰 검증
                if (!jwtUtil.validateToken(token)) {
                    throw new IllegalArgumentException("Token Error");
                }

                // 토큰에서 사용자 정보 가져오기
                Claims info = jwtUtil.getUserInfoFromToken(token);

                User user = userRepository.findByUsername(info.getSubject()).orElseThrow(() ->
                        new NullPointerException("Not Found User")
                );

                request.setAttribute("username", user.getUsername());
                chain.doFilter(request, response);   // 다음 Filter로 이동
            } else {
                throw new IllegalArgumentException("Not Found Token");
            }


        }
    }
}
