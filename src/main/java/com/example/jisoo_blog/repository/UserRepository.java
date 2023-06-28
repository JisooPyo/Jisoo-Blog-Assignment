package com.example.jisoo_blog.repository;

import com.example.jisoo_blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
