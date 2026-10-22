package com.example.task_api.repository;

import com.example.task_api.modle.Task; 
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class TaskRepository {
    private final List<Task> tasks = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong();

    public List<Task> findAll() {
        return tasks;
    }

    public Task findById(Long id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id)) 
                .findFirst()
                .orElse(null);
    }

    public Task save(Task task) {
        if (task.getId() == null || task.getId() == 0) {
            task.setId(idGenerator.incrementAndGet());
        } else {
            tasks.removeIf(t -> t.getId().equals(task.getId()));
        }
        tasks.add(task);
        return task;
    }

    public void delete(Long id) {
        tasks.removeIf(t -> t.getId().equals(id));
    }
}