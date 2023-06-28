package com.example.jisoo_blog.service;

import com.example.jisoo_blog.dto.PostRequestDto;
import com.example.jisoo_blog.dto.PostResponseDto;
import com.example.jisoo_blog.entity.Post;
import com.example.jisoo_blog.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostResponseDto createPost(PostRequestDto requestDto) {
        // RequestDto -> Entity
        Post post = new Post(requestDto);

        // DB 저장
        Post savePost = postRepository.save(post);

        // Entity -> ResponseDto
        return new PostResponseDto(savePost);
    }

    public List<PostResponseDto> getPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc()
                .stream().map(PostResponseDto::new).toList();
    }

    public PostResponseDto getPost(Long id) {
        Post post = findPost(id);
        return new PostResponseDto(post);
    }

    @Transactional
    public PostResponseDto updatePost(Long id, PostRequestDto requestDto) {
        Post post = findPost(id);
        post.update(requestDto);
        return new PostResponseDto(post);
    }

    public Long deletePost(Long id) {
        // 해당 글이 DB에 존재하는 지 확인
        Post post = findPost(id);

        // 해당 글 삭제
        postRepository.delete(post);

        return id;
    }

    private Post findPost(Long id) {
        return postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 포스트는 존재하지 않습니다.")
        );
    }

}

