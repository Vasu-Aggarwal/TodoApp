package com.example.todo.TodoApp.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tasks")
@Data
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer task_id;
    @Column(nullable = false)
    private String task_content;
    @Column(nullable = false)
    private int status;

    @ManyToOne
    private User user;
}
