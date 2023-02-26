package ru.hh.todoapp.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString(includeFieldNames = true)
public class TodoTaskDto {
    @Setter @Getter
    private Long id;

    @Setter @Getter
    private String description;

    @Setter @Getter
    private boolean completed;

    public TodoTaskDto(String description) {
        this.description = description;
        this.completed = false;
    }

    @JsonCreator
    public TodoTaskDto(
            @JsonProperty("id") Long id,
            @JsonProperty("description") String description,
            @JsonProperty("completed") boolean completed
    ) {
        this.id = id;
        this.description = description;
        this.completed = completed;
    }
}
