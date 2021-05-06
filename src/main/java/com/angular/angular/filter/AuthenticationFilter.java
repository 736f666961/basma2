package com.angular.angular.filter;


import com.angular.angular.custom.CustomUserDetails;
import com.angular.angular.request.UserLoginRequest;
import com.angular.angular.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            UserLoginRequest userLoginRequest = new ObjectMapper()
                    .readValue(request.getInputStream(), UserLoginRequest.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userLoginRequest.getUsername(),
                            userLoginRequest.getPassword(), new ArrayList<>())
            );

        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String username = ((CustomUserDetails) authResult.getPrincipal()).getUsername();
//
//        // Search in database for that email
//        UserService userService = (UserService) SpringApplicationContext.getBean("userServiceImpl");
//        UserDto userDto = userService.getUser(email);
//
//        // List of roles
//        ArrayList<String> roles = new ArrayList<>();
//
//        // Loop through roles
//        for (RoleDto role : userDto.getRoles()) {
//            roles.add(role.getRoleName());
//        }

//        String token = Jwts.builder()
//                .setSubject(email)
//                .claim("id", userDto.getPublicUserId())
//                .claim("roles", roles)
//                .setExpiration(new Date(System.currentTimeMillis() + TokenConfig.TOKEN_EXPIRATION_TIME))
//                .signWith(SignatureAlgorithm.HS512, TokenConfig.TOKEN_SECRET )
//                .compact();
//
//        response.addHeader(TokenConfig.HEADER_AUTHORIZATION,TokenConfig .TOKEN_PREFIX + token);
        response.addHeader("username", username);
//        response.addHeader("access-control-expose-headers", "authorization");
    }
}
