package com.example.jisoo_blog.dto;

import java.util.List;

public class PostsResponseDto {
	private List<PostResponseDto> postsList;

	public PostsResponseDto(List<PostResponseDto> postsList) {
		this.postsList = postsList;
	}
}
