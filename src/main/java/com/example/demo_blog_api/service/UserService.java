package com.example.demo_blog_api.service;

import com.example.demo_blog_api.domain.User;
import com.example.demo_blog_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User getOrCreateUser(String kakaoId, String nickname) {
        return userRepository.findByKakaoId(kakaoId)  // ✅ DB에서 조회
                .orElseGet(() -> {  // ✅ 없으면 새로 생성 후 저장
                    User newUser = new User();
                    newUser.setKakaoId(kakaoId);
                    newUser.setNickname(nickname);
                    return userRepository.save(newUser);
                });
    }
}
