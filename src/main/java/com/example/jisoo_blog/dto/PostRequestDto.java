package com.example.jisoo_blog.dto;

import lombok.Getter;

@Getter
public class PostRequestDto {
    private String posttitle;
    private String username;
    private String password;
    private String contents;
}
