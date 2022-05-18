package com.example.springbootTodo.service;

import com.example.springbootTodo.domain.Todo;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * The interface Todos service
 */

public interface TodoService {

     /**
      * create todos todos name
      *
      * @param todos the todos
      * @return the todos
      */
     Todo createTodo(Todo todos);

     /**
      * update the todos
      *
      * @param id the todos id
      * @param todos the todos list
      * @return the todos list
      */
     Todo updateTodo(String id, Todo todos) ;

     /**
      * Get all todos
      *
      * @return the all todos in the list
      */
     List<Todo> getAllTodo();

     /**
      * Get todos by todos id
      *
      * @param id the todos id
      * @return the todos by todos id
      */
     Todo getTodo(String id) ;

     /**
      * Delete todos
      *
      * @param id the todos id
      * @return
      */
     Todo deleteTodo(String id);
}
