package ru.hh.todoapp.data;

import ru.hh.todoapp.data.db.TodoTask;

public class Mapper {
    public static TodoTaskDto toDto(TodoTask task) {
        return new TodoTaskDto(task.getId(), task.getDescription(), task.isCompleted());
    }

    public static TodoTask toNewEntity(TodoTaskDto taskDto) {
        return new TodoTask(taskDto.getDescription());
    }

    public static TodoTask toEntity(TodoTaskDto task) {
        return new TodoTask(task.getId(), task.getDescription(), task.isCompleted());
    }

    public static void updateDto(TodoTaskDto taskDto, TodoTask updates) {
        taskDto.setId(updates.getId());
        taskDto.setDescription(updates.getDescription());
        taskDto.setCompleted(updates.isCompleted());
    }
}
