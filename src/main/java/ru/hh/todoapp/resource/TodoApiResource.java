package ru.hh.todoapp.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.stereotype.Component;

import ru.hh.todoapp.data.Status;
import ru.hh.todoapp.data.TodoTaskDto;
import ru.hh.todoapp.service.TodoService;

import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;

@Component
@Path("/api/v1")
public class TodoApiResource {
    private final TodoService todoService;

    @Inject
    public TodoApiResource(TodoService todoService) {
        this.todoService = todoService;
    }

    @GET
    @Path("/task")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getTasks(@QueryParam(value = "status") Status status) {
        var tasks = todoService.getTasks(status);
        return Response.ok().entity(tasks).build();
    }

    @GET
    @Path("/task/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTaskById(@PathParam("id") Long id) {
        var task = todoService.getSpecificTaskById(id);
        return Response.ok().entity(task).build();
    }

    @POST
    @Path("/task")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createTask(TodoTaskDto task) {
        todoService.registerTask(task);
        return Response.ok().entity(task).build();
    }

    @PUT
    @Path("/task")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateTask(TodoTaskDto task) {
        todoService.updateTask(task);
        return Response.ok().build();
    }
}
