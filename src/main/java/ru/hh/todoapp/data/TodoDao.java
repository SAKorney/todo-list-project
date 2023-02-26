package ru.hh.todoapp.data;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class TodoDao {

    Map<Long, TodoTask> storage = new HashMap<>();
    Long id = 3L;

    public TodoDao() {
        storage.put(1L, new TodoTask(1L, "First task"));
        storage.put(2L, new TodoTask(2L, "Second task"));
    }

    public List<TodoTask> getAll() {
        return storage.values().stream().toList();
    }

    public List<TodoTask> getByStatus(Status status) {
        boolean flag = status == Status.COMPLETED;
        return storage.values().stream()
                .filter(task -> task.isCompleted() == flag).toList();
    }

    public Optional<TodoTask> getById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    public void add(TodoTask task) {
        task.setId(id++);
        storage.put(task.getId(), task);
    }

    public void update(TodoTask entity) {
        var record = storage.get(entity.getId());
        record.setDescription(entity.getDescription());
        record.setCompleted(entity.isCompleted());
    }
}
