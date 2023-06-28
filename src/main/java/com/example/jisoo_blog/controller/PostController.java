package com.example.jisoo_blog.controller;

import com.example.jisoo_blog.dto.PostRequestDto;
import com.example.jisoo_blog.dto.PostResponseDto;
import com.example.jisoo_blog.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/JisooBlog" )
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // 게시글 작성
    // http://localhost:8080/JisooBlog/posts
    @PostMapping( "/posts" )
    public PostResponseDto createPost( @RequestBody PostRequestDto requestDto ) {
        return postService.createPost(requestDto);
    }

    // 전체 게시글 목록 조회
    // http://localhost:8080/JisooBlog/posts
    @GetMapping( "/posts" )
    public List< PostResponseDto > getPosts() {
        return postService.getPosts();
    }

    // 선택한 게시글 조회
    // http://localhost:8080/JisooBlog/post?id=1
    @GetMapping( "/post" )
    public PostResponseDto getPost( Long id ) {
        return postService.getPost(id);
    }


    // 선택한 게시글 수정
    // http://localhost:8080/JisooBlog/post?id=1
    @PutMapping( "/post" )
    public Long updatePost( Long id, @RequestBody PostRequestDto requestDto ) {
        return postService.updatePost(id,requestDto);
    }

    // 선택한 게시글 삭제
    // http://localhost:8080/JisooBlog/post?id=1&password=비밀번호1
    @DeleteMapping( "/post" )
    public Long deletePost( Long id, String password ) {
        return postService.deletePost(id,password);
    }
}
