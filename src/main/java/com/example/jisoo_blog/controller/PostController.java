//package com.example.jisoo_blog.controller;
//
//import com.example.jisoo_blog.dto.PostRequestDto;
//import com.example.jisoo_blog.dto.PostResponseDto;
//import com.example.jisoo_blog.service.PostService;
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/JisooBlog")
//public class PostController {
//    private final PostService postService;
//
//    public PostController(PostService postService) {
//        this.postService = postService;
//    }
//
//    // 전체 게시글 목록 조회
//    // http://localhost:8080/JisooBlog
//    @GetMapping("/")
//    public List<PostResponseDto> getPosts() {
//        return postService.getPosts();
//    }
//
//    // 게시글 작성
//    // http://localhost:8080/JisooBlog/post
//    @PostMapping("/post")
//    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto, HttpServletRequest req) {
//        requestDto.setUsername((String) req.getAttribute("username"));
//        return postService.createPost(requestDto);
//    }
//
//    // 선택한 게시글 조회 -->
//    // http://localhost:8080/JisooBlog/1
//    @GetMapping("/{postid}")
//    public PostResponseDto getPost(@PathVariable Long postid) {
//        return postService.getPost(postid);
//    }
//
//
//    // 선택한 게시글 수정
//    // http://localhost:8080/JisooBlog/post?id=1
//    @PutMapping("/post")
//    public PostResponseDto updatePost(Long id, @RequestBody PostRequestDto requestDto) {
//        return postService.updatePost(id, requestDto);
//    }
//
//    // 선택한 게시글 삭제
//    // http://localhost:8080/JisooBlog/post?id=1
//    @DeleteMapping("/post")
//    public Long deletePost(Long id) {
//        return postService.deletePost(id);
//    }
//}
