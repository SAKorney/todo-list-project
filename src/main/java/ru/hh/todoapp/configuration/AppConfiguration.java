package ru.hh.todoapp.configuration;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import ru.hh.todoapp.data.db.TodoDao;
import ru.hh.todoapp.data.db.utils.DbFactory;
import ru.hh.todoapp.data.db.utils.TransactionHelper;
import ru.hh.todoapp.resource.TodoApiResource;
import ru.hh.todoapp.service.TodoService;

@Configuration
@Import({
    TodoApiResource.class,
    TodoService.class,
    TodoDao.class,
    TransactionHelper.class
})
public class AppConfiguration {
    @Bean
    public SessionFactory sessionFactory() {
        return DbFactory.createSessionFactory();
    }
}
