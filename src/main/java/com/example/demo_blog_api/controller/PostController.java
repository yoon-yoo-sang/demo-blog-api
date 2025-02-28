package com.example.demo_blog_api.controller;

import com.example.demo_blog_api.domain.Post;
import com.example.demo_blog_api.domain.User;
import com.example.demo_blog_api.dto.PostCreateDto;
import com.example.demo_blog_api.service.PostService;
import com.example.demo_blog_api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final UserService userService;
    private final PostService postService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getPostById(@PathVariable("id") Long id) {
        Optional<Post> postOptional = postService.getPostById(id);
        if (postOptional.isEmpty()) {
            HashMap<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Post not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
        return ResponseEntity.ok(postOptional.get());
    }

    @PostMapping("")
    public ResponseEntity<?> createPost(@RequestBody PostCreateDto postCreateDto) {
        Optional<User> userOptional = userService.getUserById(postCreateDto.getUser());
        if (userOptional.isEmpty()) {
            HashMap<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "User not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

        Post post = new Post();
        post.setUser(userOptional.get());
        post.setTitle(postCreateDto.getTitle());
        post.setContent(postCreateDto.getContent());
        post.setTags(postCreateDto.getTags());

        Post createdPost = postService.createPost(post);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
    }
}

