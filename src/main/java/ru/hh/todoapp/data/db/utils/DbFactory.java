package ru.hh.todoapp.data.db.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.hh.todoapp.data.db.TodoTask;

import java.util.List;

public class DbFactory {
    private static final List<Class<?>> ENTITY_CLASSES_REGISTRY = List.of(
            TodoTask.class
    );

    public static SessionFactory createSessionFactory() {
        var serviceRegistry = new StandardServiceRegistryBuilder()
                .loadProperties("hibernate.properties")
                .build();
        var metadataSources = new MetadataSources(serviceRegistry);
        ENTITY_CLASSES_REGISTRY.forEach(metadataSources::addAnnotatedClass);

        return metadataSources.buildMetadata().buildSessionFactory();
    }
}
