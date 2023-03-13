package com.example.user.interceptor;

import com.example.user.interceptor.JwtUtil;
import com.example.user.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthJwtFilter extends OncePerRequestFilter {

    @Autowired
    protected UserServiceImpl userService;

    @Autowired
    protected JwtUtil jwtUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String uuid = null;
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            authorizationHeader = authorizationHeader.replace("Bearer", "");
            uuid = jwtUtil.extractUsername(authorizationHeader.trim());
        }

        if (uuid != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userService.loadUserByUsername(uuid);
            if (jwtUtil.validateToken(authorizationHeader, userDetails)) {

                UsernamePasswordAuthenticationToken
                        usernamePasswordAuthenticationToken = new
                        UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        filterChain.doFilter(request, response);
    }

}

