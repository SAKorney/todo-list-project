package ru.hh.todoapp.configuration;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import ru.hh.todoapp.resource.TodoApiResource;

@Component
public class JerseyConfiguration extends ResourceConfig {
    public JerseyConfiguration() {
        register(TodoApiResource.class);
    }
}
