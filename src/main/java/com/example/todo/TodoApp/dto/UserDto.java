package com.example.todo.TodoApp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
public class UserDto {
    private Integer user_id;

    @NotNull
    @NotEmpty
    @Size(min = 3, message = "Name must be 3 characters long")
    private String name;

    @Email(message = "Email is invalid")
    private String email;
    private String username;

    @Size(min = 4, message = "Password must be least 4 characters long")
    @NotEmpty
    @NotNull
    private String password;
}
