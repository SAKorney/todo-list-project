package ru.hh.todoapp.service;

import jakarta.inject.Inject;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;

import java.util.Optional;
import java.util.List;

import ru.hh.todoapp.data.Mapper;
import ru.hh.todoapp.data.TodoTaskDto;
import ru.hh.todoapp.data.db.TodoDao;
import ru.hh.todoapp.data.db.TodoTask;

@Component
public class TodoService {
    private final static Logger LOGGER = getLogger(TodoTask.class);

    private final TodoDao todoDao;

    @Inject
    public TodoService(TodoDao todoDao) {
        this.todoDao = todoDao;
    }

    public List<TodoTaskDto> getTasks(Boolean status) {
        LOGGER.info("Get tasks with status [{}]", status);
        return  Optional.ofNullable(status)
                .map(todoDao::getByStatus)
                .orElseGet(todoDao::getAll)
                .stream().map(Mapper::toDto)
                .toList();
    }

    public TodoTaskDto getSpecificTaskById(Long id) {
        LOGGER.info("Get task with id [{}]", id);
        return todoDao.getById(id)
                .map(Mapper::toDto)
                .orElseThrow();
    }

    public TodoTaskDto registerTask(TodoTaskDto taskDto) {
        LOGGER.info("Add new task: [{}]", taskDto);
        var entity = Mapper.toNewEntity(taskDto);
        todoDao.add(entity);
        return Mapper.toDto(entity);
    }

    public void updateTask(TodoTaskDto task) {
        LOGGER.info("Updated task: [{}]", task);
        Long id = task.getId();
        todoDao.getById(id).orElseThrow();
        var entity = Mapper.toEntity(task);
        todoDao.update(entity);
    }
}
