package ru.hh.todoapp.data;

public class Mapper {
    public static TodoTaskDto toDto(TodoTask task) {
        return new TodoTaskDto(task.getId(), task.getDescription(), task.isCompleted());
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
