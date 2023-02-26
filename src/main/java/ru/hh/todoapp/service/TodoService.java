package ru.hh.todoapp.service;

import jakarta.inject.Inject;
import org.springframework.stereotype.Component;
import ru.hh.todoapp.data.*;

import java.util.List;
import java.util.Optional;

@Component
public class TodoService {

    @Inject
    TodoDao todoDao;

    public List<TodoTaskDto> getTasks(Status status) {
        return Optional.ofNullable(status)
                .map(todoDao::getByStatus)
                .orElseGet(todoDao::getAll)
                .stream().map(Mapper::toDto)
                .toList();
    }

    public TodoTaskDto getSpecificTaskById(Long id) {
       return todoDao.getById(id)
               .map(Mapper::toDto)
               .orElseGet(() -> null);
    }

    public void registerTask(TodoTaskDto taskDto) {
        var entity = Mapper.toEntity(taskDto);
        todoDao.add(entity);
        Mapper.updateDto(taskDto, entity);
    }

    public void updateTask(TodoTaskDto task) {
        TodoTask entity = Mapper.toEntity(task);
        todoDao.update(entity);
    }
}
