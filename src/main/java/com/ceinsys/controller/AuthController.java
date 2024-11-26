package com.ceinsys.controller;


import com.ceinsys.Service.UserService;
import com.ceinsys.config.JWTUtil;
import com.ceinsys.dto.AuthResponse;
import com.ceinsys.model.AuthRequest;
import com.ceinsys.model.User;
import com.ceinsys.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest){
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));

                    if(authentication.isAuthenticated()){
                        final UserDetails userDetails = userService.loadUserByUsername(authRequest.getEmail());
                        User user = userService.findUserByEmail(authRequest.getEmail());
                        final String jwt = jwtUtil.getToken(authRequest.getEmail(), user.getRole());

                        String role = user.getRole().toString();

                        AuthResponse authResponse = new AuthResponse(
                            user.getId(),
                                user.getName(),
                                user.getEmail(),
                                role,
                                jwt
                        );

                        return ResponseEntity.ok(authResponse);
                    }else{
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid User Request");
                    }

    } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid User Request");
        }
}

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok("User Added Successfully");

    }

    @PostMapping("/forgotPassword")
    public ResponseEntity<?> forgetPassword(@RequestParam String email, @RequestParam String password){
        email = email.trim();

        System.out.println("Searching for user With email"+ email);
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isPresent()){
            User user  = optionalUser.get();
            try{
                user.setPassword(passwordEncoder.encode(password));
                userRepository.save(user);
                return ResponseEntity.ok("Password Change Successfully");
            }catch (Exception e){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Unexpected Error"+ e.getMessage());
            }
        }else{
            System.out.println("User not found"+email);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User Not Found");
        }
    }
}
