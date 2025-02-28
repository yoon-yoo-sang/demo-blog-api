package com.example.demo_blog_api.dto;

import lombok.Getter;
import org.json.JSONObject;


@Getter
public class KakaoToken {
    String tokenType;
    String accessToken;
    int expiresIn;
    String refreshToken;
    int refreshTokenExpiresIn;

    public KakaoToken(String jsonResponseBody){
        JSONObject res = new JSONObject(jsonResponseBody);
        this.tokenType = res.getString("token_type");
        this.accessToken = res.getString("access_token");
        this.expiresIn = res.getInt("expires_in");
        this.refreshToken = res.getString("refresh_token");
        this.refreshTokenExpiresIn = res.getInt("refresh_token_expires_in");
    }
}
