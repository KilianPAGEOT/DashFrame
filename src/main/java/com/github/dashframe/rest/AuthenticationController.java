package com.github.dashframe.rest;

import com.github.dashframe.config.MyUserDetailsService;
import com.github.dashframe.config.TokenService;
import com.github.dashframe.models.User;
import java.io.IOException;
import java.security.Principal;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    private final TokenService tokenService;

    @Autowired
    private MyUserDetailsService userDetailsService;

    private final OAuth2AuthorizedClientService authorizedClientService;
    private final UserController userController;

    private Cookie cookie;

    public AuthenticationController(
        TokenService tokenService,
        OAuth2AuthorizedClientService authorizedClientService,
        UserController userController
    ) {
        this.tokenService = tokenService;
        this.authorizedClientService = authorizedClientService;
        this.userController = userController;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/login", produces = { "text/plain", "application/json" })
    public String createToken(Authentication authentication) {
        if (this.userDetailsService.loadUserByUsername(authentication.getName()) == null) {
            throw new UsernameNotFoundException(authentication.getName());
        }

        return tokenService.generateToken(authentication);
    }

    @GetMapping("/users-oauth2")
    public void getUserInfo(Principal user, HttpServletResponse response) throws IOException {
        if (user instanceof OAuth2AuthenticationToken) {
            getOauth2LoginInfo(user);
        }
        if (this.cookie != null) {
            response.addCookie(this.cookie); //add cookie and redirect to fronted client
        }
        response.sendRedirect("http://localhost:5173");
    }

    private void getOauth2LoginInfo(Principal user) { // function if user login with OAuth2
        OAuth2AuthenticationToken authToken = ((OAuth2AuthenticationToken) user);
        OAuth2AuthorizedClient authClient =
            this.authorizedClientService.loadAuthorizedClient(
                    authToken.getAuthorizedClientRegistrationId(),
                    authToken.getName()
                );

        if (authToken.isAuthenticated()) {
            Map<String, Object> userAttributes = authToken.getPrincipal().getAttributes();

            String userToken = authClient.getAccessToken().getTokenValue();

            userController.processOAuthPostLogin(
                userAttributes.get("name") == null
                    ? (String) userAttributes.get("login")
                    : (String) userAttributes.get("name"),
                userAttributes.get("sub") == null
                    ? userAttributes.get("id").toString()
                    : userAttributes.get("sub").toString(),
                null,
                userToken,
                false
            ); // create user in db with OAuth2 informations
            this.cookie = new Cookie("token", userToken); // create cookie with token
            this.cookie.setPath("/");
            this.cookie.setMaxAge(8 * 60 * 60);
        }
    }
}
