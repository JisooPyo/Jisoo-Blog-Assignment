package com.example.jisoo_blog.controller;

import com.example.jisoo_blog.dto.ApiResponseDto;
import com.example.jisoo_blog.dto.LoginRequestDto;
import com.example.jisoo_blog.dto.SignupRequestDto;
import com.example.jisoo_blog.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/JisooBlog")
public class UserController {

    private final UserService userService;

    @PostMapping("/user/signup")
    @ResponseBody
    public ResponseEntity<ApiResponseDto> signup(@Valid @RequestBody SignupRequestDto requestDto, BindingResult bindingResult, HttpServletResponse response) {
        // Validation 예외처리 - signupRequestDto에서 설정한 글자수, 문자규칙(a~z,A~Z,0~9)에 위배되는 경우 fieldError 리스트에 내용이 추가됨
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        StringBuilder errorMessage = new StringBuilder();

        //1건 이상 Validation 관련 에러가 발견된 경우 - 에러메시지(1개~ 여러 개)를 message응답으로 client에 전달
        if (fieldErrors.size() > 0) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
                errorMessage.append(fieldError.getDefaultMessage()).append("\n ");
            }
            response.setStatus(400);
            return ResponseEntity.status(400).body(new ApiResponseDto(errorMessage.toString(), response.getStatus()));
        }

        try {
            return ResponseEntity.status(response.getStatus()).body(userService.signup(requestDto, response));
        } catch (IllegalArgumentException ex) { //signup 메서드에서 오류발생 시, 에러메시지와 상태값 리턴
            response.setStatus(400);
            return ResponseEntity.status(400).body(new ApiResponseDto(ex.getMessage(), response.getStatus()));
        }
    }

}
