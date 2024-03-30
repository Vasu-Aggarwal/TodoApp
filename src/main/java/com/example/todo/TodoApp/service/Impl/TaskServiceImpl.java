package com.example.todo.TodoApp.service.Impl;

import com.example.todo.TodoApp.dto.TaskDto;
import com.example.todo.TodoApp.entity.Task;
import com.example.todo.TodoApp.entity.User;
import com.example.todo.TodoApp.exception.ResourceNotFoundException;
import com.example.todo.TodoApp.payload.TaskResponse;
import com.example.todo.TodoApp.repository.TaskRepo;
import com.example.todo.TodoApp.repository.UserRepo;
import com.example.todo.TodoApp.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TaskRepo taskRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public TaskDto createTask(TaskDto taskDto, Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "userId", userId));
        Task task = this.modelMapper.map(taskDto, Task.class);
        task.setUser(user);
        task.setAddedDate(new Date());
        Task newTask = this.taskRepo.save(task);
        return this.modelMapper.map(this.taskRepo.save(newTask), TaskDto.class);
    }

    @Override
    public void deleteTask(Integer taskId) {
        Task task = this.taskRepo.findById(taskId).orElseThrow(()-> new ResourceNotFoundException("Task", "taskId", taskId));
        this.taskRepo.delete(task);
    }

    @Override
    public TaskDto updateTask(TaskDto taskDto, Integer taskId) {
        Task task = this.taskRepo.findById(taskId).orElseThrow(()-> new ResourceNotFoundException("Task", "taskId", taskId));
        task.setTask_content(taskDto.getTask_content());
        task.setStatus(taskDto.getStatus());
        Task updatedTask = this.taskRepo.save(task);
        return this.modelMapper.map(updatedTask, TaskDto.class);
    }

    @Override
    public TaskDto readTaskById(Integer taskId) {
        Task task = this.taskRepo.findById(taskId).orElseThrow(()-> new ResourceNotFoundException("Task", "taskId", taskId));
        return this.modelMapper.map(task, TaskDto.class);
    }

    @Override
    public TaskResponse getAllTasks(Integer pageNumber, Integer pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));

        Page<Task> taskPage = this.taskRepo.findAll(pageable);
        List<Task> allTask = taskPage.getContent();
        List<TaskDto> taskDtos = allTask.stream().map(task -> this.modelMapper.map(task, TaskDto.class)).collect(Collectors.toList());

        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setContent(taskDtos);
        taskResponse.setPageNumber(taskPage.getNumber());
        taskResponse.setPageSize(taskPage.getSize());
        taskResponse.setTotalElements(taskPage.getTotalElements());
        taskResponse.setLastPage(taskPage.isLast());

        return taskResponse;
    }

    @Override
    public List<TaskDto> getTasksByUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "userId", userId));
        List<Task> tasks = this.taskRepo.findByUser(user);
        return tasks.stream().map(t -> this.modelMapper.map(t, TaskDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<TaskDto> searchTask(String keyword) {
        List<Task> tasks = this.taskRepo.findByTitleContaining(keyword);
        return tasks.stream().map(t -> this.modelMapper.map(t, TaskDto.class)).collect(Collectors.toList());
    }
}
