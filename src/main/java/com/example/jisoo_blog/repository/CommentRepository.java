package com.example.jisoo_blog.repository;

import com.example.jisoo_blog.entity.Comment;
import com.example.jisoo_blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	List<Comment> findAllByPostOrderByCreatedAtDesc(Post post);
}
