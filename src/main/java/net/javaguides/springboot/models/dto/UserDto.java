package net.javaguides.springboot.models.dto;

import lombok.Data;

@Data
public class UserDto {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
}
