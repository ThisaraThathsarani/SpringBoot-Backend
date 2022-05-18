package com.example.springbootTodo.service.MockitoTest;

import com.example.springbootTodo.domain.Todo;
import com.example.springbootTodo.repository.TodoRepository;
import com.example.springbootTodo.service.impl.TodoServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.*;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertAll;

@RunWith(MockitoJUnitRunner.class)
public class TodoServiceImplTest {

    @InjectMocks
    private TodoServiceImpl todoServiceImpl;

    @Mock
    private Todo todo;

    @Mock
    private TodoRepository todoRepository;

    private String todo_Id = "test_id";
    private String todo_Name = "test_title";
    private String todo_Description = "test_description";
    private String todo_Status = "test_pending";

    @Before
    public void setup() {
        todo = new Todo();
        todo.setId(todo_Id);
        todo.setTodo(todo_Name);
        todo.setDescription(todo_Description);
        todo.setStatus(todo_Status);
    }

    @Test
    public void createTodo() {
        doReturn(todo).when(todoRepository).save(any(Todo.class));
        Assertions.assertEquals(todo, todoServiceImpl.createTodo(todo));
        Assertions.assertEquals(todo.getId(),"test_id");

    }

    @Test
    public void getAllTodo() {
        doReturn(Arrays.asList(todo)).when(todoRepository).findAll();
        Assertions.assertEquals(Arrays.asList(todo), todoServiceImpl.getAllTodo());
    }

    @Test
    public void getTodoByIdTest(){
        doReturn(Optional.of(todo)).when(todoRepository).findById("test_id");
        Assertions.assertEquals(todo, todoServiceImpl.getTodo("test_id"));
        Assertions.assertEquals(todo.getTodo(),"test_title");
    }

    @Test
    public void getTodoByIdNotExistTest(){
        doReturn(Optional.ofNullable(null)).when(todoRepository).findById("test_id");
        Todo exceptionMessage = todoServiceImpl.getTodo("test_id" );
        Assertions.assertEquals("Provided Todo Id does not exists in the list", exceptionMessage.getErrorMessage());
        Assertions.assertEquals(todo.getTodo(),"test_title");
    }

    @Test
    public void deleteByIdTest(){
        doReturn(Optional.of(todo)).when(todoRepository).findById("test_id");
        Assertions.assertEquals(todo, todoServiceImpl.deleteTodo("test_id"));
        //Assertions.assertEquals(todo.getId(),"test_id");
    }

    @Test
    public void deleteByIdNotExistTest(){
        doReturn(Optional.ofNullable(null)).when(todoRepository).findById("test_id");
        Todo exceptionMessage = todoServiceImpl.deleteTodo("test_id" );
        Assertions.assertEquals("Provided Todo Id does not exists in the list", exceptionMessage.getErrorMessage());
       // Assertions.assertEquals(null, todoServiceImpl.deleteTodo("test_id"));
    }

    @Test
    public void updateTodoById(){
        doReturn(Optional.of(todo)).when(todoRepository).findById("test_id");
        doReturn(todo).when(todoRepository).save(any(Todo.class));
//        todo = new Todo();
//        todo.setId(todo_Id);
//        todo.setTodo("todo_Name");
//        todo.setDescription("todo_Description");
//        todo.setStatus("todo_Status");
        Todo response = todoServiceImpl.updateTodo("test_id" ,todo);
        assertAll("Should return Todo type object with todo's data",
                () -> Assertions.assertEquals("test_id", response.getId()),
                () -> Assertions.assertEquals("test_title", response.getTodo()),
                () -> Assertions.assertEquals(null, response.getErrorMessage())
        );
    }

    @Test
    public void updateTodoByIdNotExistTest(){
        doReturn(Optional.ofNullable(null)).when(todoRepository).findById("test_id");
        Todo exceptionMessage = todoServiceImpl.updateTodo("test_id" ,todo);
        Assertions.assertEquals("Provided Todo Id does not exists in the list", exceptionMessage.getErrorMessage());

    }
}
