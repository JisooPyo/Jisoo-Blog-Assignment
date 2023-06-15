package com.example.jisoo_blog.controller;

import com.example.jisoo_blog.dto.PostRequestDto;
import com.example.jisoo_blog.dto.PostResponseDto;
import com.example.jisoo_blog.entity.Post;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class PostController {

    private final Map< Long, Post > postList = new HashMap<>();

    @PostMapping( "/posts" )
    public PostResponseDto createPost( @RequestBody PostRequestDto requestDto ) {
        // RequestDto -> Entity
        Post post = new Post( requestDto );

        // Post Max ID Check -> Set ID
        Long maxId = postList.size() > 0 ? Collections.max( postList.keySet() ) + 1 : 1;
        post.setId( maxId );

        // Set Date
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd 'at' HH:mm:ss z" );
        Date now = new Date( System.currentTimeMillis() );
        String date = sdf.format( now );
        post.setDate( date );

        // DB 저장
        postList.put( post.getId(), post );

        // Entity -> ResponseDto
        PostResponseDto postResponseDto = new PostResponseDto( post );
        return postResponseDto;
    }

    @GetMapping( "/posts" )
    public List< PostResponseDto > getPosts() {
        // Map To List
        List< PostResponseDto > responseList = postList.values().stream()
                .map( PostResponseDto::new ).toList();

        return responseList;
    }
}
