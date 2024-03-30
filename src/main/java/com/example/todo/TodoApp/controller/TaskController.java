package com.example.todo.TodoApp.controller;

import com.example.todo.TodoApp.dto.TaskDto;
import com.example.todo.TodoApp.payload.TaskResponse;
import com.example.todo.TodoApp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/user/{userId}/tasks")
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto, @PathVariable Integer userId){
        TaskDto task = this.taskService.createTask(taskDto, userId);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @DeleteMapping("/tasks/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable Integer taskId){
        this.taskService.deleteTask(taskId);
        return new ResponseEntity<>(Map.of("message", "Task is deleted successfully"), HttpStatus.OK);
    }

    @PutMapping("/tasks/{taskId}")
    public ResponseEntity<TaskDto> updateTask(@RequestBody TaskDto taskDto, @PathVariable Integer taskId){
        TaskDto task = this.taskService.updateTask(taskDto, taskId);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @GetMapping("/tasks/{taskId}")
    public ResponseEntity<TaskDto> readTaskById(@PathVariable Integer taskId){
        TaskDto task = this.taskService.readTaskById(taskId);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @GetMapping("/tasks")
    public ResponseEntity<TaskResponse> getAllTasks(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "2", required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = "title", required = false) String sortBy
    ){
        TaskResponse taskResponse = this.taskService.getAllTasks(pageNumber, pageSize, sortBy);
        return new ResponseEntity<>(taskResponse, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/tasks")
    public ResponseEntity<List<TaskDto>> getTasksByUser(@PathVariable Integer userId){
        List<TaskDto> tasks = this.taskService.getTasksByUser(userId);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/tasks/search/{keyword}")
    public ResponseEntity<List<TaskDto>> searchTasks(@PathVariable String keyword){
        List<TaskDto> tasks = this.taskService.searchTask(keyword);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

}
