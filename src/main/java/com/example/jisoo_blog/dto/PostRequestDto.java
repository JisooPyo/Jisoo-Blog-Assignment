package com.example.jisoo_blog.dto;

import lombok.Getter;

@Getter
public class PostRequestDto {
    private String posttitle;
    private String username;
    private String contents;

    // Setter는 함부로 쓰면 안되지만.. 어떻게 username을 넘길지를 잘 모르겠다.
    public void setUsername(String username) {
        this.username = username;
    }
}
