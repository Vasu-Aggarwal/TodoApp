package com.example.todo.TodoApp.repository;

import com.example.todo.TodoApp.entity.Task;
import com.example.todo.TodoApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepo extends JpaRepository<Task, Integer> {
    List<Task> findByUser(User user);
    List<Task> findByTitleContaining(String title);
}
