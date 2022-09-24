package com.lee.nltx.util;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtil {
    /**
     * @Description: 私钥，需要妥善保存
     * @param data 将个人信息封装到map中，存放到data
     * @param validTime 设置JWT有效时间（秒）
     * @return JWT Token
     */
    private static final String JWT_TOKEN = "@&%(*longcodingLN!@@!$%&";

    public static String createToken(Map<String, Object> data, Long validTime) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("data", data);
        /**
         * signWith: 签发算法，秘钥为jwtToken
         * setClaims: body数据，要唯一，自行设置
         * setIssuedAt: 设置签发时间
         * setExpiration: 设置有效时间
         */
        JwtBuilder jwtBuilder = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, JWT_TOKEN)
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + validTime));
        return jwtBuilder.compact();
    }

    /**
     * @param token
     * @return 如果验证失败，返回null
     */
    public static Map<String, Object> checkToken(String token) {
        try {
            Jwt parse = Jwts.parser().setSigningKey(JWT_TOKEN).parse(token);
            return (Map<String, Object>) parse.getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
	
    public static void main(String[] args) {
        Map<String, Object> data = new HashMap<>();
        String token = JWTUtil.createToken(data,100L);
        System.out.println(token);
        Map<String, Object> mp = JWTUtil.checkToken(token);
        assert mp != null;
        System.out.println(mp.get("userId"));
    }
}