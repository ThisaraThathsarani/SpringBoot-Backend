package com.example.springbootTodo.service.impl;

import com.example.springbootTodo.constant.ExceptionMessage;
import com.example.springbootTodo.domain.Todo;
import com.example.springbootTodo.repository.TodoRepository;
import com.example.springbootTodo.service.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * The type Todos service
 */
@Service
public class TodoServiceImpl implements TodoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TodoServiceImpl.class);

    @Autowired
    private final TodoRepository todoRepo;

    /**
     * Instantiates a new Todos service.
     *
     * @param todoRepo the todos repository
     */
    public TodoServiceImpl(TodoRepository todoRepo) {
        this.todoRepo = todoRepo;
    }

    @Override
    public Todo createTodo(Todo todos) {
        LOGGER.info("TodoServiceImpl : createTodo");
        return todoRepo.save(todos);
    }

    @Override
    public Todo updateTodo(String id, Todo todos) {
        LOGGER.info("TodoServiceImpl : updateTodo");
        Optional<Todo> todosOptional = todoRepo.findById(id);
        if(todosOptional.isPresent()){
            LOGGER.info("TodoServiceImpl : update Todo");
            Todo todoSave = todosOptional.get();
            todoSave.setTodo(todos.getTodo() != null ? todos.getTodo() : todoSave.getTodo());
            todoSave.setDescription(todos.getDescription() != null ? todos.getDescription() : todoSave.getDescription());
            todoSave.setStatus(todos.getStatus() != null ? todos.getStatus() : todoSave.getStatus());
            todoSave.setUpdatedAt(new Date());
            return todoRepo.save(todoSave);
        }else{
            LOGGER.error("TodoServiceImpl : update Todo: {}", ExceptionMessage.TODO_DOES_NOT_EXISTS);
            //throw new NotFoundException(ExceptionMessage.TODO_DOES_NOT_EXISTS , HttpStatus.NOT_FOUND);
            //return null;
            return  new Todo("Provided Todo Id does not exists in the list");
        }
    }

    @Override
    public List<Todo> getAllTodo() {
        LOGGER.info("TodoServiceImpl : getAllTodo");
        return todoRepo.findAll();
    }

    @Override
    public Todo getTodo(String id) {
        LOGGER.info("TodoServiceImpl : getTodo");
        Optional<Todo> todosOptional = todoRepo.findById(id);
        if(todosOptional.isPresent()){
            LOGGER.info("TodoServiceImpl : get Todo");
            return todosOptional.get();
        }else {
            LOGGER.error("TodoServiceImpl : get Todo : {}", ExceptionMessage.TODO_DOES_NOT_EXISTS);
            return  new Todo("Provided Todo Id does not exists in the list");
            //throw new NotFoundException(ExceptionMessage.TODO_DOES_NOT_EXISTS,  HttpStatus.NOT_FOUND);
        }
    }
    @Override
    public Todo deleteTodo(String id) {
        Optional<Todo> todosOptional = todoRepo.findById(id);
        if(todosOptional.isPresent()){
            LOGGER.info("TodoServiceImpl : delete specific id todo in the list");
            Todo deleteTodo = todosOptional.get();
            todoRepo.delete(deleteTodo);
            return  deleteTodo;
        }else{
            LOGGER.error("TodoServiceImpl : delete Todo : {}" , ExceptionMessage.TODO_DOES_NOT_EXISTS);
            //throw new NotFoundException(ExceptionMessage.TODO_DOES_NOT_EXISTS,  HttpStatus.NOT_FOUND);
            //return null;
            return  new Todo("Provided Todo Id does not exists in the list");
        }
    }
}
