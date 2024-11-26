package com.ceinsys.Service;

import com.ceinsys.dto.UserDto;
import com.ceinsys.model.User;
import com.ceinsys.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException("User not found Exception : " + email));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                List.of(new SimpleGrantedAuthority(user.getRole().name())));
    }

    public User findUserByEmail(String email) {
        Optional<User> optionalUser=userRepository.findByEmail(email);
        return optionalUser.get();
    }

    public String addUser (User user){
        Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());
            if(optionalUser.isPresent()) {
                return "User Already Present";
            }else{
                userRepository.save(user);
                return "User Added Successfully";
            }
    }

    public User findById(Long id){
        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.get();
        return user;
    }


    // Method is for get all User(admin, user, super admin)
    public List<UserDto> getAll (){
        List<User> userList = userRepository.getAllUser();
        List<UserDto> userDtos = new ArrayList<>();

        for(User user: userList){
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setName(user.getName());
            userDto.setEmail(user.getEmail());
            userDto.setRole(user.getRole());

            userDtos.add(userDto);
        }
        return userDtos;
    }


    public List<UserDto> getAllAdmin(){
        List<User> userList = userRepository.getAllAdmins();
        List<UserDto> userDtoList = new ArrayList<>();

        for(User user: userList){
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setName(user.getName());
            userDto.setEmail(user.getEmail());
            userDto.setRole(user.getRole());

            userDtoList.add(userDto);
        }
        return userDtoList;
    }



}
