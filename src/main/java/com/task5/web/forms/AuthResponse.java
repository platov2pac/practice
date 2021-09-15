package com.task5.web.forms;

import com.task5.dto.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private String login;
    private List<Role> roles;
}
