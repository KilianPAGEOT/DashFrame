package com.github.dashframe.rest;

import com.github.dashframe.config.MyUserDetailsService;
import com.github.dashframe.config.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    private final TokenService tokenService;

    @Autowired
    private MyUserDetailsService userDetailsService;

    public AuthenticationController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/token", produces = { "text/plain", "application/json" })
    public String createToken(Authentication authentication) {
        if (this.userDetailsService.loadUserByUsername(authentication.getName()) == null) {
            throw new UsernameNotFoundException(authentication.getName());
        }

        return tokenService.generateToken(authentication);
    }
}
