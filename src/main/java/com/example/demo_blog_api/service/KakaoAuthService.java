package com.example.demo_blog_api.service;


import com.example.demo_blog_api.dto.KakaoProfile;
import com.example.demo_blog_api.domain.User;
import com.example.demo_blog_api.dto.KakaoToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


@Service
public class KakaoAuthService {
    @Value("${spring.kakao.clientId}")
    private String clientId;

    @Value("${spring.kakao.redirectUri}")
    private String redirectUri;

    public String getKakaoLoginUrl() {
        return "https://kauth.kakao.com/oauth/authorize?client_id=" + clientId
                + "&redirect_uri=" + redirectUri + "&response_type=code";
    }

    public KakaoToken getAccessToken (String code) {
        try {
            RestTemplate rt = new RestTemplate();

            HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = getMultiValueMapHttpEntity(code);

            String kakaoTokenUri = "https://kauth.kakao.com/oauth/token";
            ResponseEntity<String> response =
                    rt.exchange(kakaoTokenUri, HttpMethod.POST, kakaoTokenRequest, String.class);

            String responseBody = response.getBody();

            return new KakaoToken(responseBody);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public User getUserInfo(String accessToken) {
        String reqUrl = "https://kapi.kakao.com/v2/user/me";

        RestTemplate rt = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String, String>> userRequest = new HttpEntity<>(headers);

        ResponseEntity<String> response =
                rt.exchange(reqUrl, HttpMethod.POST, userRequest, String.class);

        KakaoProfile kakaoProfile = new KakaoProfile(response.getBody());

        User user = new User();
        user.setKakaoId(kakaoProfile.getId());
        user.setNickname(kakaoProfile.getNickname());

        return user;
    }

    private HttpEntity<MultiValueMap<String, String>> getMultiValueMapHttpEntity(String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", clientId);
        params.add("redirect_uri", redirectUri);

        params.add("code", code);

        return new HttpEntity<>(params, headers);
    }
}
