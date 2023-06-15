package com.example.jisoo_blog.dto;

import com.example.jisoo_blog.entity.Post;
import lombok.Getter;

@Getter
public class PostResponseDto {
    private Long id;
    private String posttitle;
    private String username;
    private String contents;
    private String createdAt;
    private String modifiedAt;

    public PostResponseDto( Post post ) {
        this.id = post.getId();
        this.posttitle = post.getPosttitle();
        this.username = post.getUsername();
        this.contents = post.getContents();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
    }
}
