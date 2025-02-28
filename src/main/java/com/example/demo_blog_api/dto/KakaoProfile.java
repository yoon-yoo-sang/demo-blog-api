package com.example.demo_blog_api.dto;

import lombok.Getter;
import org.json.JSONObject;


@Getter
public class KakaoProfile {
    private final String id;
    private final String nickname;

    public KakaoProfile(String jsonResponseBody){
        JSONObject res = new JSONObject(jsonResponseBody);
        this.id = res.getInt("id") + "";
        JSONObject properties = res.getJSONObject("properties");
        this.nickname = properties.getString("nickname");
    }
}
