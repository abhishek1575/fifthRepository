package com.ceinsys.controller;

import com.ceinsys.Service.UserService;
import com.ceinsys.dto.UserDto;
import com.ceinsys.model.User;
import com.ceinsys.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @PostMapping("/addUser")
    public ResponseEntity<?> addUser (@RequestBody User user){
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok("User Added Successfully");

    }

    @PutMapping("/modifyUser")
    public ResponseEntity<?> modifyUser(@RequestBody UserDto user){
        Optional<User> optionalUser =userRepository.findById(user.getId());
        try{
            if(optionalUser.isPresent()){
                User user1 = optionalUser.get();
                user1.setEmail(user.getEmail());
                user1.setName(user.getName());
                user1.setRole(user.getRole());
            }
            return ResponseEntity.ok("User Modify Successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unexpected Error");
        }

    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<?> deleteUser(@RequestParam Long id) {
        Optional<User> userInfo=userRepository.findById(id);

        if(userInfo.isPresent()){
            try{
                userRepository.deleteById(id);
                return ResponseEntity.ok("User Deleted Successfully");
            }catch (Exception e){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Unexpected error");
            }
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User Not Found");
        }
    }

    @GetMapping("/getUserById")
    public ResponseEntity<?> getUserById(@RequestParam Long id) {
        try {
            User user=userService.findById(id);
            UserDto userDto=new UserDto();
            userDto.setName(user.getName());
            userDto.setEmail(user.getEmail());
            userDto.setRole(user.getRole());
            return ResponseEntity.ok(user);
        } catch (Exception e) {
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unexpected Error"+ e.getMessage());
        }

    }
}
