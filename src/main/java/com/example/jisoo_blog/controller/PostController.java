package com.example.jisoo_blog.controller;

import com.example.jisoo_blog.dto.ApiResponseDto;
import com.example.jisoo_blog.dto.PostRequestDto;
import com.example.jisoo_blog.dto.PostResponseDto;
import com.example.jisoo_blog.dto.PostsResponseDto;
import com.example.jisoo_blog.security.UserDetailsImpl;
import com.example.jisoo_blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.RejectedExecutionException;

@RestController
@RequestMapping("/JisooBlog")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    // 전체 게시글 목록 조회
    // http://localhost:8080/JisooBlog/view/all
    @GetMapping("/view/all")
    public ResponseEntity<List<PostResponseDto>> getPosts() {
        List<PostResponseDto> results = postService.getPosts();

        return ResponseEntity.ok().body(results);
    }

    // 선택한 게시글 조회 -->
    // http://localhost:8080/JisooBlog/view/1
    @GetMapping("/view/{id}")
    public ResponseEntity<PostResponseDto> getPostById(@PathVariable Long id) {
        PostResponseDto result = postService.getPostById(id);
        return ResponseEntity.ok().body(result);
    }

    // 게시글 작성
    // http://localhost:8080/JisooBlog/post
    @PostMapping("/post")
    public ResponseEntity<PostResponseDto> createPost(@RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        PostResponseDto result = postService.createPost(requestDto, userDetails.getUser());
        // 예외는 어떻게 되는거지?
        return ResponseEntity.status(201).body(result);
    }

    // 선택한 게시글 수정
    // http://localhost:8080/JisooBlog/post?id=1
    @PutMapping("/post")
    public ResponseEntity<ApiResponseDto> updatePost(Long id,
                                                      @RequestBody PostRequestDto requestDto,
                                                      @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            postService.updatePost(id, requestDto, userDetails.getUser());
            return ResponseEntity.ok().body(new ApiResponseDto("게시글 수정 성공", HttpStatus.OK.value()));
        } catch (RejectedExecutionException e){
            return ResponseEntity.badRequest().body(new ApiResponseDto("작성자만 수정 할 수 있습니다.",
                    HttpStatus.BAD_REQUEST.value()));
        }
    }

    // 선택한 게시글 삭제
    // http://localhost:8080/JisooBlog/post?id=1
    @DeleteMapping("/post")
    public ResponseEntity<ApiResponseDto> deletePost(Long id,
                                                     @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            postService.deletePost(id, userDetails.getUser());
            return ResponseEntity.ok().body(new ApiResponseDto("게시글 삭제 성공",HttpStatus.OK.value()));
        } catch (RejectedExecutionException e) {
            return ResponseEntity.badRequest().body(new ApiResponseDto("작성자만 삭제할 수 있습니다.",
                    HttpStatus.BAD_REQUEST.value()));
        }
    }
}
