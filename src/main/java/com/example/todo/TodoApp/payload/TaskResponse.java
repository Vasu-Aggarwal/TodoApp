package com.example.todo.TodoApp.payload;

import com.example.todo.TodoApp.dto.TaskDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class TaskResponse {
    private List<TaskDto> content;
    private int pageSize;
    private int pageNumber;
    private long totalElements;
    private int totalPages;
    private boolean lastPage;
}
