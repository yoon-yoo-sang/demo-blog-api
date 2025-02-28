package com.example.demo_blog_api.controller;

import com.example.demo_blog_api.domain.User;
import com.example.demo_blog_api.dto.KakaoToken;
import com.example.demo_blog_api.service.JwtService;
import com.example.demo_blog_api.service.KakaoAuthService;
import com.example.demo_blog_api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final KakaoAuthService kakaoAuthService;
    private final JwtService jwtService;
    private final UserService userService;

    @GetMapping("/kakao")
    public ResponseEntity<?> loginPage() {
        String kakaoLoginUrl = kakaoAuthService.getKakaoLoginUrl();
        return ResponseEntity.ok(kakaoLoginUrl);
    }

    @GetMapping("/kakao/callback")
    public ResponseEntity<?> kakaoLogin(@RequestParam String code) {
        KakaoToken kakaoToken = kakaoAuthService.getAccessToken(code);

        User kakaoUser = kakaoAuthService.getUserInfo(kakaoToken.getAccessToken());

        User user = userService.getOrCreateUser(kakaoUser.getKakaoId(), kakaoUser.getNickname());

        Map<String, String> jwtToken = jwtService.generateToken(user);

        return ResponseEntity.ok(jwtToken);
    }
}
