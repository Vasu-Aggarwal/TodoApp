package com.example.todo.TodoApp.repository;

import com.example.todo.TodoApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
}
