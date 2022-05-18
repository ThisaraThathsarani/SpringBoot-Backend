package com.example.springbootTodo.controller;

import com.example.springbootTodo.constant.Api;
import com.example.springbootTodo.domain.Todo;
import com.example.springbootTodo.service.TodoService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Todo Controller class
 */
@CrossOrigin
@RestController
@RequestMapping(Api.API_BASE_URL + Api.TODO_PATH_URL)
public class TodoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TodoController.class);

    private final TodoService todoService;

    public TodoController(TodoService todoService) {

        this.todoService = todoService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully returned all the todos"),
    })
    public ResponseEntity<List<Todo>> getAllTodo() {
        LOGGER.info("TodoController: ViewToDo");
        return new ResponseEntity<>(todoService.getAllTodo(), HttpStatus.OK);
    }

    @GetMapping(path = Api.API_ID_PATH , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully returned the todo "),
            @ApiResponse(responseCode = "404", description = "Could not find the given todo"),
    })
    private ResponseEntity<Todo> getTodo(@PathVariable("id") String id)  {
        LOGGER.info("TodoController : getTodo");
        return new ResponseEntity<>(todoService.getTodo(id), HttpStatus.OK);
    }


    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created the todo"),
            @ApiResponse(responseCode = "400", description = "Bad request, validation error"),
    })
    private ResponseEntity<Todo> createTodo(@RequestBody Todo todos) {
        LOGGER.info("TodoController : createTodo");
            return new ResponseEntity<>(todoService.createTodo(todos), HttpStatus.OK);
    }


    @PutMapping(path =  Api.API_ID_PATH , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated the todo"),
            @ApiResponse(responseCode = "404", description = "Could not find the given todo"),
    })
    private ResponseEntity<Todo> updateTodo(@PathVariable("id") String id, @RequestBody Todo todos)  {
        LOGGER.info("TodoController : updateTodo");
            return new ResponseEntity<>(todoService.updateTodo(id, todos), HttpStatus.OK);
    }

    @DeleteMapping(path = Api.API_ID_PATH , produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted the todo"),
            @ApiResponse(responseCode = "404", description = "Could not find the given todo"),
    })
    public Todo deleteTodo(@PathVariable ("id") String id){
        LOGGER.info("TodoController : deleteTodo");
            return todoService.deleteTodo(id);
    }
}

