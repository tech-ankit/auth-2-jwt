package com.security.auth_2.controller;

import com.security.auth_2.entity.AppUser;
import com.security.auth_2.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @GetMapping("/auth/google/success")
    public String googleLoginSuccess(@AuthenticationPrincipal OAuth2User appUser) {
        String email = appUser.getAttribute("email");
        String name = appUser.getAttribute("name");
        String picture = appUser.getAttribute("picture");
        String locale = appUser.getAttribute("locale");
        String sub = appUser.getAttribute("sub");
        return JwtUtil.generateToken(email);
    }
}

