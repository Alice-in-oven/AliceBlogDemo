package com.aliceblog.demo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class JwtUtils {

    //设置token过期时间
    public static final long EXPIRE = 1000 * 60 * 60 * 24;  
    //密钥，随便写，做加密操作
    public static final String APP_SECRET ="xbrceXUKwYIRoQJndTPFNzAmhDagkLMExbrceXUKwYIRoQJndTPFNzAmhDagkLME";  
    
    //生成jwt字符串的方法
    public static String getJwtToken(String id, String nickname){

        String JwtToken = Jwts.builder()
                //设置头信息，固定
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                //设置过期时间
                .setSubject("guli-user")//名字随便取
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                //设置jwt主体部分
                .claim("id", id)
                .claim("userName", nickname)
                //根据密钥生成字符串
                .signWith(SignatureAlgorithm.HS256, APP_SECRET)
                .compact();

        return JwtToken;
    }

    /**
     * 判断jwt是否存在与有效
     * @param request
     * @return
     */
    public static boolean checkToken(HttpServletRequest request) {
        try {
            String jwtToken = request.getHeader("Cookie").split("=")[1];
            if(StringUtils.isEmpty(jwtToken)) return false;
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    /**
     * 根据jwt获取用户信息
     * @param request
     * @return
     */
    public static Claims getMemberByJwtToken(HttpServletRequest request) {
        String jwtToken = request.getHeader("Cookie").split("=")[1];
        if(StringUtils.isEmpty(jwtToken)) return null;
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        Claims claims = claimsJws.getBody();
        return claims;
    }
}

