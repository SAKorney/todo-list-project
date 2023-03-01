package ru.hh.todoapp.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.stereotype.Component;
import ru.hh.todoapp.data.TodoTaskDto;
import ru.hh.todoapp.service.TodoService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
@Path("/api/v1")
public class TodoApiResource {
    private final TodoService todoService;

    @Inject
    public TodoApiResource(TodoService todoService) {
        this.todoService = todoService;
    }

    @GET
    @Path("/tasks")
    @Produces({MediaType.APPLICATION_JSON})
    public List<TodoTaskDto> getTasks(@QueryParam(value = "completed") Boolean isCompleted) {
        return todoService.getTasks(isCompleted);
    }

    @GET
    @Path("/task/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTaskById(@PathParam("id") Long id) {
        try {
            var task = todoService.getSpecificTaskById(id);
            return Response.ok().entity(task).build();
        }
        catch (NoSuchElementException exception) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("/task")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public TodoTaskDto createTask(TodoTaskDto task) {
        return todoService.registerTask(task);
    }

    @PUT
    @Path("/task")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateTask(TodoTaskDto task) {
        try {
            todoService.updateTask(task);
            return Response.ok().build();
        }
        catch (NoSuchElementException exception) {
           return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
