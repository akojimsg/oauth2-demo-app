package com.oauth2demo.server.api.dto;

import com.oauth2demo.server.utils.enums.Role;

import java.time.LocalDate;

public record UserDTO(
    String firstName,
    String lastName,
    LocalDate dob,
    String email,
    String username,
    String password,
    Role role
){};