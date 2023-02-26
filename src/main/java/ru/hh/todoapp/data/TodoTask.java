package ru.hh.todoapp.data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class TodoTask {

    @Getter @Setter
    private Long id;

    @Getter @Setter
    private String description;

    @Getter @Setter
    private boolean completed;

    @Deprecated
    public TodoTask() {}

    public TodoTask(String description) {
        this(0L, description);
    }

    public TodoTask(Long id, String description) {
        this(id, description, false);
    }

    public TodoTask(Long id, String description, boolean completed) {
        this.id = id;
        this.description = description;
        this.completed = completed;
    }
}
