package com.example.todo.TodoApp.service;

import com.example.todo.TodoApp.dto.TaskDto;
import com.example.todo.TodoApp.payload.TaskResponse;

import java.util.List;

public interface TaskService {
    TaskDto createTask(TaskDto taskDto, Integer userId);
    void deleteTask(Integer taskId);
    TaskDto updateTask(TaskDto taskDto, Integer taskId);
    TaskDto readTaskById(Integer taskId);
    TaskResponse getAllTasks(Integer pageNumber, Integer pageSize, String sortBy);
    List<TaskDto> getTasksByUser(Integer userId);
    List<TaskDto> searchTask(String keyword);
}
