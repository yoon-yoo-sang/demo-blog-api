package com.example.demo_blog_api.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@MappedSuperclass
public abstract class Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected LocalDateTime created;
    protected LocalDateTime updated;

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);
        created = now;
        updated = now;
    }

    @PreUpdate
    protected void onUpdate() {
        updated = LocalDateTime.now(ZoneOffset.UTC);
    }
}
