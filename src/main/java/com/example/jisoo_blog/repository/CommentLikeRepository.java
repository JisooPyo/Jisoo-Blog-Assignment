package com.example.jisoo_blog.repository;

import com.example.jisoo_blog.entity.Comment;
import com.example.jisoo_blog.entity.CommentLike;
import com.example.jisoo_blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
	Optional<CommentLike> findByUserAndComment(User user, Comment comment);
}
