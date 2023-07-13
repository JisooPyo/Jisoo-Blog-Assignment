package com.example.jisoo_blog.dto;

import com.example.jisoo_blog.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Getter
public class PostResponseDto extends ApiResponseDto{
    private Long id;
    private String title;
    private String username;
    private String contents;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private List<CommentResponseDto> comments;


    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.username = post.getUser().getUsername();
        this.contents = post.getContents();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
        if (!(post.getComments() == null)) {
            this.comments = post.getComments().stream()
                    .map(CommentResponseDto::new)
                    .sorted(Comparator.comparing(CommentResponseDto::getCreatedAt).reversed()) // 작성날짜 내림차순 - reversed,
                    // getCreatedAt - 작성일자, comparing - 비교 연산자, sorted - 정렬
                    .toList();
        }
    }
}
