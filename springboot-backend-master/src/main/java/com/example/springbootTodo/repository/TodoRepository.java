package com.example.springbootTodo.repository;

import com.example.springbootTodo.domain.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TodoRepository extends MongoRepository<Todo, String> {


}
