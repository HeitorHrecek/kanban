package com.example.kanban.controller.dto;

import com.example.kanban.model.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TaskStatusRequest {
    private TaskStatus status;
}
