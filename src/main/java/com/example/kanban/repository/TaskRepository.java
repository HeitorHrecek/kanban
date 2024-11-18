package com.example.kanban.repository;

import com.example.kanban.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("""
            SELECT ta FROM Task ta 
            ORDER BY 
                CASE ta.status
                    WHEN 'TO_DO' THEN 1
                    WHEN 'IN_PROGRESS' THEN 2
                    WHEN 'DONE' THEN 3
                END 
            """)
    List<Task> listByStatus();

    @Query("""
            SELECT ta FROM Task ta 
            ORDER BY 
                CASE ta.taskPriority
                    WHEN 'HIGH' THEN 1
                    WHEN 'MEDIUM' THEN 2
                    WHEN 'LOW' THEN 3
                END,
                CASE ta.status
                    WHEN 'TO_DO' THEN 1
                    WHEN 'IN_PROGRESS' THEN 2
                    WHEN 'DONE' THEN 3
                END 
            """)
    List<Task> listByPriorityStatus();

    @Query("""
            SELECT ta FROM Task ta 
            ORDER BY 
                CASE ta.taskPriority
                    WHEN 'HIGH' THEN 1
                    WHEN 'MEDIUM' THEN 2
                    WHEN 'LOW' THEN 3
                END,
            ta.deadline
            """)
    List<Task> listByPriorityLimitDate();
}

