package com.ceinsys.dto;

import com.ceinsys.model.AuthRequest;
import com.ceinsys.model.Role;
import com.sun.jdi.event.StepEvent;
import lombok.Data;

@Data
public class AuthResponse {
    private Long id;
    private String name;
    private String role;
    private String email;
    private String type="Barer";
    private String jwt;

    public AuthResponse (Long id, String name, String email, String role, String jwt){
        this.id = id;
        this.name=name;
        this.role= role;
        this.jwt = jwt;
        this.email=email;
    }
}
