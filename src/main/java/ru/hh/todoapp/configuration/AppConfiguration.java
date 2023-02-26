package ru.hh.todoapp.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import ru.hh.todoapp.data.TodoDao;
import ru.hh.todoapp.resource.TodoApiResource;
import ru.hh.todoapp.service.TodoService;

@Configuration
@Import({
    TodoApiResource.class,
    TodoService.class,
    TodoDao.class })
public class AppConfiguration {
}
