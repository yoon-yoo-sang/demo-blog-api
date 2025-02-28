package com.example.demo_blog_api.repository;

import com.example.demo_blog_api.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByUserId(Long userId);
}
