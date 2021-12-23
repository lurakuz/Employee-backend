package net.javaguides.springboot.models.dto;

import lombok.Data;

@Data
public class EmployeeDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String emailId;
}
