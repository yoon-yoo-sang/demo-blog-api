package com.example.demo_blog_api.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "post")
public class Post extends Base {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(name = "comment_count", nullable = false)
    private int commentCount = 0;

    @ElementCollection
    private List<String> tags;
}
