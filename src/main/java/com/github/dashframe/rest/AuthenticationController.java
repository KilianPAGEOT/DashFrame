package com.github.dashframe.rest;

import com.github.dashframe.config.MyUserDetailsService;
import com.github.dashframe.config.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    private final TokenService tokenService;

    @Autowired
    private MyUserDetailsService userDetailsService;

    public AuthenticationController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/token")
    public String login(Authentication authentication) {
        if (this.userDetailsService.loadUserByUsername(authentication.getName()) == null) {
            throw new UsernameNotFoundException(authentication.getName());
        }

        return tokenService.generateToken(authentication);
    }
}
