package com.security.auth_2.util;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.security.auth_2.entity.AppUser;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.Date;
@Component
public class JwtUtil {

    @Value("${jwt.key}")
    private String key;

    @Value("${jwt.issuer}")
    private static String issuer;

    @Value("${jwt.expiry}")
    private static int expiry;

    private static Algorithm algorithm;

    @PostConstruct
    private void setAlgorithm(){

        algorithm = Algorithm.HMAC256(key);
    }

    public static String generateToken(String email){
        return JWT.create()
                .withClaim("name", email)
                .withExpiresAt(new Date(System.currentTimeMillis()+expiry))
                .withIssuer(issuer)
                .sign(algorithm);
    }

    public String getUsername(String token){
        DecodedJWT decodedJWT = JWT
                .require(algorithm)
                .withIssuer(issuer)
                .build()
                .verify(token);
        String username =  decodedJWT.getClaim("name").asString();
        Date expiresAt = decodedJWT.getExpiresAt();
        if (expiresAt.after(new Date())){
            return null;
        }else {
            return username;
        }

    }
}
