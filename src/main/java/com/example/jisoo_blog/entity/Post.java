package com.example.jisoo_blog.entity;

import com.example.jisoo_blog.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Post {
    private Long id;
    private String posttitle;
    private String username;
    private String password;
    private String contents;
    private String createdAt;
    private String modifiedAt;

    public Post( PostRequestDto requestDto ) {
        this.posttitle = requestDto.getPosttitle();
        this.username = requestDto.getUsername();
        this.password = requestDto.getPassword();
        this.contents = requestDto.getContents();
    }

    public void update( PostRequestDto requestDto ) {
        this.posttitle = requestDto.getPosttitle();
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
    }
}
