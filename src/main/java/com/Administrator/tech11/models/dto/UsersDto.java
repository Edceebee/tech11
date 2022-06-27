package com.Administrator.tech11.models.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UsersDto {

    private String firstName;

    private String lastName;

    private String email;

    private LocalDate birthday;

    private String password;
}
