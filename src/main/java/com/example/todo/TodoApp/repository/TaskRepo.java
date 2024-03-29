package com.example.todo.TodoApp.repository;

import com.example.todo.TodoApp.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepo extends JpaRepository<Task, Integer> {
}
