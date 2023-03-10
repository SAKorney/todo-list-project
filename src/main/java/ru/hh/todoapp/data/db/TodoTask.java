package ru.hh.todoapp.data.db;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Entity
@ToString
@Table(name = "task")
public class TodoTask {

    @Id
    @Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id", nullable = false, unique = true)
    private Long id;

    @Getter @Setter
    @Column(name = "task_description")
    private String description;

    @Getter @Setter
    @Column(name = "task_completed")
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

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        TodoTask task = (TodoTask) other;
        return Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description);
    }
}
