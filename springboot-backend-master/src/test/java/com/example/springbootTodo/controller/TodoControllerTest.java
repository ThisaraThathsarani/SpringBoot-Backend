//package com.example.springbootTodo.controller;
//
//import com.example.springbootTodo.domain.Todo;
//import com.example.springbootTodo.repository.TodoRepository;
//import com.example.springbootTodo.service.impl.TodoServiceImpl;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import java.util.Arrays;
//
//import static javax.servlet.http.HttpServletResponse.SC_OK;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.Mockito.doReturn;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class TodoControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    private Todo todo;
//
//    private TodoRepository todoRepository;
//
//    @MockBean
//    private TodoServiceImpl todoServiceImpl;
//
//    @BeforeEach
//    void setUp() {
//
//        todo = new Todo();
//        todo.setId("test_id");
//        todo.setTodo("test_name");
//        todo.setDescription("test_description");
//        todo.setStatus("test_status");
//
//    }
//
//    @Test
//    void getAllTodo()  throws Exception {
//       // doReturn(Arrays.asList(todo)).when(todoServiceImpl).getAllTodo();
//        mockMvc.perform(
//                MockMvcRequestBuilders.get("/api/todos"))
//                .andExpect(status().is(SC_OK));
//    }
//
//    @Test
//    void addTodo() throws  Exception {
//        doReturn(todo).when(todoServiceImpl).createTodo(any(Todo.class));
//        mockMvc.perform(
//                MockMvcRequestBuilders.post("/api/todos/")
//                        .content(asJsonString(todo))
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().is(SC_OK));
//    }
//
//    @Test
//    void updateTodoTest() throws Exception {
//        doReturn(todo).when(todoServiceImpl).updateTodo(eq("test_id") ,any(Todo.class));
//        mockMvc.perform(
//                MockMvcRequestBuilders.put("/api/todos/test_id")
//                        .content(asJsonString(todo))
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                        .andExpect(status().is(SC_OK));
//    }
//
//    @Test
//    void getTodoById() throws Exception {
//
//        doReturn(todo).when(todoServiceImpl).getTodo("test_id");
//        mockMvc.perform(
//                MockMvcRequestBuilders.get("/api/todos/test_id"))
//                .andExpect(content().json("{}"))
//                .andExpect(status().is(SC_OK));
//        //Assertions.assertEquals(todo.getTodo(),"test_name");
//    }
//
//    @Test
//    void deleteTodo() throws Exception  {
//        doReturn(todo).when(todoServiceImpl).deleteTodo("test_id");
//
//            mockMvc.perform(
//                    MockMvcRequestBuilders.delete("/api/todos/test_id")
//                            .content(asJsonString(todo))
//                            .contentType(MediaType.APPLICATION_JSON)
//                            .accept(MediaType.APPLICATION_JSON))
//                    .andExpect(status().is(SC_OK));
//
//    }
//
//    static String asJsonString(final Object obj) {
//        try {
//            return new ObjectMapper().writeValueAsString(obj);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//}

