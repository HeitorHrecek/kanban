package com.example.kanban.controller;

import com.example.kanban.controller.dto.TaskStatusRequest;
import com.example.kanban.model.Task;
import com.example.kanban.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public Task createTask(@RequestBody Task novaTask) {
        return this.taskService.createTask(novaTask);
    }

    @GetMapping
    public List<Task> getTasksByStatus() {
        return this.taskService.getTasksByStatus();
    }

    @PatchMapping("/{id}/move")
    public Task updateTaskStatus(@RequestBody TaskStatusRequest novoStatus, @PathVariable("id") Long id) {
        return this.taskService.updateTaskStatus(id, novoStatus.getStatus());
    }

    @GetMapping("/priority")
    public List<Task> listByPriorityStatus() {
        return this.taskService.listByPriorityStatus();
    }

    @GetMapping("/priority/deadline")
    public List<Task> queryByPriorityLimitDate() {
        return this.taskService.queryByPriorityLimitDate();
    }

    @GetMapping("/report")
    public List<Task> getReport() {
        return this.taskService.getReport();
    }



    @PutMapping("/{id}")
    public Task modify(@RequestBody Task novosDados, @PathVariable("id") Long id) {
        return this.taskService.modify(id, novosDados);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        this.taskService.delete(id);
    }
}
