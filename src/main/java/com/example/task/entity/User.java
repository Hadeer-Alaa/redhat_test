package com.example.task.entity;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.*;
@Data
@Entity(name="user_profile")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Email
    @NotBlank(message = "Email is mandatory")
    private String userName;

    @NotBlank(message = "Password is mandatory")
    @NotNull(message = "Password must be between 4 to 15 characters")
    @NotEmpty
    @Size(min = 4, max = 15)
    private String password;

    @NotBlank(message = "Address is mandatory")
    @Pattern(regexp = "^[a-zA-Z 0-9_.-]*$")
    private String address;

    @NotBlank(message = "Company ID is mandatory")
    @Pattern(regexp = "^[a-zA-Z 0-9_]*$")
    private String companyId;

    @NotBlank(message = "Phone is mandatory")
    @Pattern(regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10}$")
    private String phone;

}