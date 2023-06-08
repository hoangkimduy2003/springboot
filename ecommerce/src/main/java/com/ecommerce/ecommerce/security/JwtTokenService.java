package com.ecommerce.ecommerce.security;

import com.ecommerce.ecommerce.dto.UserDTO;
import com.ecommerce.ecommerce.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtTokenService {
    @Value("${jwt.secret}")
    private String secretKey;

    private long validity = 50;// 50 phut

    public String createToken(User user) {
        Date now = new Date();
        Date exp = new Date(now.getTime() + validity * 60 * 1000);
        Claims claims = Jwts.claims().setSubject(user.getEmail());
        claims.put("id", user.getId());
        claims.put("phoneNumber", user.getPhoneNumber());
        claims.put("address", user.getAddress());
        claims.put("cart", user.getCart());
        claims.put("role", user.getRole());
        claims.put("time",exp.getTime());


        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String getUsername(String token) {
        try {
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
                    .getBody().getSubject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
