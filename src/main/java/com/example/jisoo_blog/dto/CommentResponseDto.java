package com.example.jisoo_blog.dto;

import com.example.jisoo_blog.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto extends ApiResponseDto {
	private Long id;
	private String username;
	private LocalDateTime createdAt;
	private LocalDateTime modifiedAt;
	private String contents;
	private long commentLike;

	public CommentResponseDto(Comment comment) {
		this.id = comment.getId();
		this.username = comment.getUser().getUsername();
		this.createdAt = comment.getCreatedAt();
		this.modifiedAt = comment.getModifiedAt();
		this.contents = comment.getContents();
		this.commentLike = comment.getCommentLike();
	}


}
