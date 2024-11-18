package com.example.kanban.service;

import com.example.kanban.model.Task;
import com.example.kanban.model.TaskStatus;
import com.example.kanban.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

@AllArgsConstructor
@Service
public class TaskService {

    private final TaskRepository repository;

    public Task createTask(Task novaTask) {
        LocalDate creationDate = LocalDate.now();

        novaTask.setCreationDate(creationDate);
        novaTask.setStatus(TaskStatus.TO_DO);

        return this.repository.save(novaTask);
    }

    public List<Task> getTasksByStatus() {
        List<Task> taskList = this.repository.listByStatus();
        return taskList;
    }

    public Task updateTaskStatus(Long id, TaskStatus novoStatus) {
        Task taskDomain = consultarPorId(id);

        // Define transições permitidas
        Map<TaskStatus, Set<TaskStatus>> transicoesPermitidas = new EnumMap<>(TaskStatus.class);
        transicoesPermitidas.put(TaskStatus.TO_DO, Set.of(TaskStatus.IN_PROGRESS));
        transicoesPermitidas.put(TaskStatus.IN_PROGRESS, Set.of(TaskStatus.TO_DO, TaskStatus.DONE));
        transicoesPermitidas.put(TaskStatus.DONE, Set.of());

        // Verifica se a transição é válida
        if (transicoesPermitidas.get(taskDomain.getStatus()).contains(novoStatus)) {
            taskDomain.setStatus(novoStatus);
            return this.repository.save(taskDomain);
        }

        throw new IllegalArgumentException("Transição de status inválida: "
                + taskDomain.getStatus() + " -> " + novoStatus);
    }

    public Task modify(Long id, Task novosDados) {
        Task taskExistente = consultarPorId(id);

        taskExistente.modify(novosDados);

        return this.repository.save(taskExistente);
    }

    public void delete(Long id) {
        this.repository.deleteById(id);
    }

    private Task consultarPorId(Long id) {
        Optional<Task> tarefaExistente = this.repository.findById(id);

        if(tarefaExistente.isEmpty()) {
            System.out.println("Tarefa não encontrada");
        }

        return tarefaExistente.get();
    }

    public List<Task> listByPriorityStatus() {
        return this.repository.listByPriorityStatus();
    }

    public List<Task> queryByPriorityLimitDate() {
        return this.repository.listByPriorityLimitDate();
    }

    public List<Task> getReport() {
        List<Task> tasks = this.repository.listByStatus();

        return tasks.stream()
                .sorted(Comparator
                        .comparing(Task::getDeadline)
                        .thenComparing(task -> task.getStatus().equals(TaskStatus.DONE))
                )
                .toList();
    }
}
