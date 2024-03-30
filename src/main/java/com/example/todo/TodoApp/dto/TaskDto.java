package com.example.todo.TodoApp.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class TaskDto {

    private Integer task_id;
    private String title;
    @NotEmpty
    private String task_content;
    private int status;
    private Date addedDate;
    private UserDto user;
}
