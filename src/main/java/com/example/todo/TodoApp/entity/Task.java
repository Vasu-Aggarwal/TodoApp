package com.example.todo.TodoApp.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "tasks")
@Data
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer task_id;
    @Column(name = "taskTitle", nullable = false)
    private String title;
    @Column(nullable = false)
    private String task_content;
    @Column(nullable = false)
    private int status;
    private Date addedDate;


    @ManyToOne
    private User user;
}
