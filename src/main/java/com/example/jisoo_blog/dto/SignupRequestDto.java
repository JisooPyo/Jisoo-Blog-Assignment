package com.example.jisoo_blog.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    @NotNull(message = "username은 필수 값입니다.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[0-9]).{4,10}$",
            message = "username은 최소 4자 이상, 10자 이하이며 알파벳 소문자, 숫자로 구성되어야 합니다.")
    private String username;

    @NotNull(message = "password는 필수 값입니다.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_\\-+=|\\\\{}\\[\\]:;'\"<>,.?/~`]).{8,15}$",
            message = "password는 최소 8자 이상, 15자 이하이며 알파벳 대소문자, 숫자, 특수문자로 구성되어야 합니다.")
    private String password;

    private boolean admin = false;
    private String adminToken = "";
}
