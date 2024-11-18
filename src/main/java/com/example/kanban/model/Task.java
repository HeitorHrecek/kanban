package com.example.kanban.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "Task")
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    private String description;

    @Column(name = "creationDate")
    private LocalDate creationDate;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Enumerated(EnumType.STRING)
    private TaskPriority taskPriority;

    @Column(name = "deadline")
    private LocalDate deadline;

    public void modify(Task novosDados) {
        this.title = novosDados.getTitle();
        this.description = novosDados.getDescription();
        this.status = novosDados.getStatus();
        this.taskPriority = novosDados.getTaskPriority();
        this.deadline = novosDados.getDeadline();
    }
}
