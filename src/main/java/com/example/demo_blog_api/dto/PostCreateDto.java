package com.example.demo_blog_api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class PostCreateDto {
    Long user;
    String title;
    String content;
    List<String> tags;
}
