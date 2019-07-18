package com.myapp.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserDTO {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String phone_number;
    @NotBlank
    private String email;
}
