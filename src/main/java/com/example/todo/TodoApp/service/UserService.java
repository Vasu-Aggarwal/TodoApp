package com.example.todo.TodoApp.service;

import com.example.todo.TodoApp.dto.UserDto;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto updateUser(UserDto userDto, Integer userId);
    void deleteUser(Integer userId);
    UserDto getUserById(Integer userId);
}
