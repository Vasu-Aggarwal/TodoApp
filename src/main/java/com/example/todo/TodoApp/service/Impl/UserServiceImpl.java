package com.example.todo.TodoApp.service.Impl;

import com.example.todo.TodoApp.dto.UserDto;
import com.example.todo.TodoApp.entity.User;
import com.example.todo.TodoApp.exception.ResourceNotFoundException;
import com.example.todo.TodoApp.repository.UserRepo;
import com.example.todo.TodoApp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDto createUser(UserDto userDto) {
        User newUser = this.modelMapper.map(userDto, User.class);
        User user = this.userRepo.save(newUser);
        return this.modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "userId", userId));
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        user.setUsername(userDto.getUsername());
        User updatedUser = this.userRepo.save(user);
        return this.modelMapper.map(updatedUser, UserDto.class);
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "userId", userId));
        this.userRepo.delete(user);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "userId", userId));
        return this.modelMapper.map(user, UserDto.class);
    }
}
