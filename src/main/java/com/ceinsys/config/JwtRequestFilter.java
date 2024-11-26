package com.ceinsys.config;

import com.ceinsys.Service.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@EnableWebSecurity
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    private UserService userService;

    @Autowired
    private final JWTUtil jwtUtil;

    public JwtRequestFilter (JWTUtil jwtUtil, UserService userService){
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }


    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
            final String authorizationHeader =request.getHeader("Authorization");
            String email= null;
            String jwt = null;

            if(authorizationHeader!=null && authorizationHeader.startsWith("Bearer")){
                jwt = authorizationHeader.substring(7);

                try{
                    email = jwtUtil.getEmailFromToken(jwt);
                }catch(IllegalArgumentException exception){
                    System.out.println("Enable to get JWT Token");
                }catch (ExpiredJwtException exception){
                    System.out.println("JWT Token has Expired");
                }
            }else {
                logger.warn("jwt token does not being with Bearer String");
            }

            if(email!= null && SecurityContextHolder.getContext().getAuthentication() == null){
                UserDetails userDetails = this.userService.loadUserByUsername(email);

                if(jwtUtil.validateToken(jwt, userDetails.getUsername())){
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities()
                    );

                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
            filterChain.doFilter(request, response);
}
}
