//package com.example.springbootTodo.controller;
//
//import com.example.springbootTodo.SpringboottodoApplication;
//import com.example.springbootTodo.constant.Api;
//import com.example.springbootTodo.domain.Todo;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.FixMethodOrder;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.MethodSorters;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.*;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.List;
//import java.util.UUID;
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = SpringboottodoApplication.class)
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//public class TodoControllerIntegrationTest {
//
//    private String host = "http://localhost";
//    private String urlTodo = "";
//
//    @LocalServerPort
//    private String port;
//
//    private RestTemplate restTemplate;
//
//    private HttpHeaders httpHeaders;
//
//    private String id = "09c40c6e-37e8-4781-8f69-6b6e9e1dea0c";
//
//    /**
//     * Set up common part and run before the test cases
//     */
//    @Before
//    public void setup() {
//        restTemplate = new RestTemplate();
//        httpHeaders = new HttpHeaders();
//        httpHeaders.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
//        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//        host = host.concat(":" + port);
//        urlTodo = urlTodo.concat(host + Api.API_BASE_URL + Api.TODO_PATH_URL);
//    }
//
//    /**
//     * Test create new list
//     */
//    @Test
//    public void createTodoTest() {
//        String url = urlTodo;
//        Todo todo = new Todo();
//        todo.setId(UUID.randomUUID().toString());
//        todo.setTodo("create new programme");
//        todo.setDescription("I am going to implement backend application using Spring boot");
//        todo.setStatus("pending");
//        HttpEntity<Todo> httpEntity = new HttpEntity<>(todo, httpHeaders);
//        ResponseEntity<Todo> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, Todo.class);
//        Assert.assertNotNull(responseEntity.getBody());
//        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//    }
//
//    /**
//     * Test view all_todo item within the list
//     */
//    @Test
//    public void viewAllTodoList() {
//        String url = urlTodo.concat("");
//        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);
//        ResponseEntity<List<Todo>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<Todo>>() {
//        });
//        Assert.assertNotNull(responseEntity.getBody());
//        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//    }
//
//    /**
//     * test todo_by_id
//     */
//    @Test
//    public void getTodoByIdTest() {
//        String url = urlTodo.concat(id);
//        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);
//        ResponseEntity<Todo> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, Todo.class);
//
//        Assert.assertNotNull(responseEntity.getBody());
//        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//    }
//
//    /**
//     * Test update part by id
//     */
//    @Test
//    public void updateByIdTest(){
//        String url = urlTodo.concat(id);
//        Todo todo = new Todo();
//        todo.setTodo("create programme");
//        todo.setDescription(" backend application using Spring boot");
//        todo.setStatus("pending");
//        HttpEntity<Todo> httpEntity = new HttpEntity<>(todo, httpHeaders);
//        ResponseEntity<Todo> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, httpEntity, Todo.class);
//        Assert.assertNotNull(responseEntity.getBody());
//        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//    }
//
//    /**
//     * test delete todo_item by id and this id within the system
//     */
//    @Test
//    public void deleteTodoByIdTest() {
//        String url = urlTodo.concat(id );
//        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);
//        ResponseEntity<Void> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, httpEntity, Void.class);
//        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//    }
//
//}
