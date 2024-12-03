package com.ceinsys.dto;

import com.ceinsys.model.Role;
import lombok.Data;

@Data
public class UserDto {


    private Long id;

    private String name;

    private String email;

    private Role role;


}
