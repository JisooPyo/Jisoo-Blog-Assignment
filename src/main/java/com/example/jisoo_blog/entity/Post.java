package com.example.jisoo_blog.entity;

import com.example.jisoo_blog.dto.PostRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="posts")
@NoArgsConstructor
public class Post extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String posttitle, username;
    @Column
    private String contents;

    public Post(PostRequestDto requestDto) {
        this.posttitle = requestDto.getPosttitle();
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
    }

    public void update(PostRequestDto requestDto) {
        this.posttitle = requestDto.getPosttitle();
        this.contents = requestDto.getContents();
    }
}
