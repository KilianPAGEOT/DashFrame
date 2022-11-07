package com.github.dashframe.controller;

import com.github.dashframe.service.UserService;
import com.github.dashframe.models.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Map;

@RestController
public class LoginController  extends HttpServlet {

    private final OAuth2AuthorizedClientService authorizedClientService;
    private final UserService userService;

    private Cookie cookie;
    public LoginController(OAuth2AuthorizedClientService authorizedClientService, UserService userService){
        this.authorizedClientService = authorizedClientService;
        this.userService = userService;
    }

//    @GetMapping("/")
//    public String helloWorld() {
//        return "you don't need to be logged in";
//    }
//
//    @GetMapping("/not-restricted")
//    public String notRestricted() {
//        return "you don't need to be logged in";
//    }
//
//    @GetMapping("/restricted")
//    public String restricted(Principal User) {
//        return "if you see this you are logged in"+User.toString();
//    }

    @GetMapping("/user")
    public String getUserInfo(Principal user,HttpServletResponse response) throws IOException {
        System.out.println(user);
        StringBuffer userInfo= new StringBuffer();
        if(user instanceof UsernamePasswordAuthenticationToken){
            userInfo.append(getUsernamePasswordLoginInfo(user));
        }
        else if(user instanceof OAuth2AuthenticationToken){
            userInfo.append(getOauth2LoginInfo(user));
        }

        response.addCookie(this.cookie);
        response.sendRedirect("http://localhost:5176");
        return userInfo.toString();
    }

    private StringBuffer getOauth2LoginInfo(Principal user) {

        StringBuffer protectedInfo = new StringBuffer();

        OAuth2AuthenticationToken authToken = ((OAuth2AuthenticationToken) user);
        OAuth2AuthorizedClient authClient = this.authorizedClientService.loadAuthorizedClient(authToken.getAuthorizedClientRegistrationId(), authToken.getName());
        OAuth2User principal = ((OAuth2AuthenticationToken)user).getPrincipal();

        if(authToken.isAuthenticated()){

            Map<String,Object> userAttributes = ((DefaultOAuth2User) authToken.getPrincipal()).getAttributes();

            String userToken = authClient.getAccessToken().getTokenValue();

            protectedInfo.append("Welcome, " + userAttributes.get("name")+"<br><br>");
            protectedInfo.append("e-mail: " + userAttributes.get("email")+"<br><br>");
            protectedInfo.append("Access Token: " + userToken+"<br><br>");
            protectedInfo.append("test: "+userAttributes+"<br><br>");
            userService.processOAuthPostLogin((String) userAttributes.get("name")==null?(String) userAttributes.get("login"): (String) userAttributes.get("name"),userAttributes.get("sub")==null?userAttributes.get("id").toString():userAttributes.get("sub").toString(),null,userToken,false);
            this.cookie= new Cookie("token",userToken);
            this.cookie.setPath("/");
            this.cookie.setMaxAge(8 * 60 * 60);
            OidcIdToken idToken = getIdToken(principal);
            if(idToken != null) {

                protectedInfo.append("idToken value: " + idToken.getTokenValue()+"<br><br>");
                protectedInfo.append("Token mapped values <br><br>");

                Map<String, Object> claims = idToken.getClaims();

                for (String key : claims.keySet()) {
                    protectedInfo.append("  " + key + ": " + claims.get(key)+"<br>");
                }
            }
        }
        else{
            protectedInfo.append("NA");
        }

        return protectedInfo;
    }
    private StringBuffer getUsernamePasswordLoginInfo(Principal user)
    {
        StringBuffer usernameInfo = new StringBuffer();

        UsernamePasswordAuthenticationToken token = ((UsernamePasswordAuthenticationToken) user);
        if(token.isAuthenticated()){
            User u = (User) token.getPrincipal();
            usernameInfo.append("Welcome, " + u.getName());
        }
        else{
            usernameInfo.append("NA");
        }
        return usernameInfo;
    }
    private OidcIdToken getIdToken(OAuth2User principal){

        if(principal instanceof DefaultOidcUser) {
            DefaultOidcUser oidcUser = (DefaultOidcUser)principal;
            return oidcUser.getIdToken();
        }
        return null;
    }
}