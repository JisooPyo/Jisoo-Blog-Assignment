package com.example.jisoo_blog.repository;

import com.example.jisoo_blog.entity.Post;
import com.example.jisoo_blog.entity.PostLike;
import com.example.jisoo_blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
	Optional<PostLike> findByUserAndPost(User user, Post post);
}
