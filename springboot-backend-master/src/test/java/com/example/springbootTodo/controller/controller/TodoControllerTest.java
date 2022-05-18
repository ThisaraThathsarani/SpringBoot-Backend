package com.example.springbootTodo.controller.controller;

import com.example.springbootTodo.SpringboottodoApplication;
import com.example.springbootTodo.constant.Api;
import com.example.springbootTodo.constant.ExceptionMessage;
import com.example.springbootTodo.domain.Todo;
import com.example.springbootTodo.repository.TodoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoDatabase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = SpringboottodoApplication.class)
@ImportAutoConfiguration(exclude = EmbeddedMongoAutoConfiguration.class)
public class TodoControllerTest extends ControllerTestBase{

    private static final String MONGOD_COLLECTION_TODOS = "todo_info";
    private static final String MONGOD_DATABASE_NAME = "embedded_database_todo_service";
    private static final String SAMPLE_TODO_ID = "83832124-d79d-42fd-a714-73db65b2f330";
    private static final String SAMPLE_INVALID_TODO_ID = "bla-blabla-blablabla-bla-blabla";
    private static final String SAMPLE_TODO_LIST = "[" +
            "{\"id\":\"62188fd5397d15504869669u\",\"todo\":\"todo1\",\"description\":\"todo1\",\"status\":\"pending\"}," +
            "{\"id\":\"621e45949097660ad31112bn\",\"todo\":\"todo2\",\"description\":\"todo1\",\"status\":\"complted\"}" +
            "]";
    private static final String SAMPLE_Todo_CREATE_REQUEST =  "{\"id\":\"79b97f87-8e32-41b4-8de4-70c9f11301e2\",\"todo\":\"todo2\",\"description\":\"todo1\",\"status\":\"pending\"}";
    /**
     * The host.
     */
    public String host = "http://localhost";
    /**
     * The port.
     */
    @LocalServerPort
    String port;
    private RestTemplate restTemplate = null;

    @Autowired
    private TodoRepository todoRepository;

    private List<Todo> storedList;

    @BeforeEach
    public void setup() throws IOException {
        super.setup();
        // Set
        host = host.concat(":" + port + Api.API_BASE_URL + Api.TODO_PATH_URL);
        // Create a collection
        MongoDatabase mongoDatabase = mongoClient.getDatabase(MONGOD_DATABASE_NAME);
        mongoDatabase.createCollection(MONGOD_COLLECTION_TODOS);
        // Generate data for todos
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<Todo>> mapType = new TypeReference<List<Todo>>() {
        };
        List<Todo> todolistInfo = objectMapper.readValue(SAMPLE_TODO_LIST, mapType);
        storedList = todoRepository.saveAll(todolistInfo);
        // Initialize the rest template
        restTemplate = new RestTemplate();
    }

    @Test
    void createTodo() throws JsonProcessingException {
        // Initialize the request
        ObjectMapper objectMapper = new ObjectMapper();
        Todo todoRequest = objectMapper.readValue(SAMPLE_Todo_CREATE_REQUEST, Todo.class);
        // Initialize the request headers
        HttpHeaders httpHeaders = getDefaultHttpHeaders();
        // Initialize Http entity
        HttpEntity<Todo> httpEntity = new HttpEntity<Todo>(todoRequest, httpHeaders);
        // Send the request
        ResponseEntity<Todo> responseEntity = restTemplate.exchange(host, HttpMethod.POST, httpEntity, Todo.class);
        // Validate
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertNotNull(responseEntity.getBody());
        Todo receivedTodoInfo = responseEntity.getBody();
        Assertions.assertNotNull(receivedTodoInfo);
        Assertions.assertEquals(todoRequest.getId(), receivedTodoInfo.getId());
    }

    @Test
    void updateTodo() {
        // Get the request object
        Todo todoInfoRequest = storedList.get(0);
        String updatedTodo = todoInfoRequest.getTodo().concat("updated");
        todoInfoRequest.setTodo(updatedTodo);
        todoInfoRequest.setDescription(updatedTodo);
        todoInfoRequest.setStatus(updatedTodo);
        // Initialize the request headers
        HttpHeaders httpHeaders = getDefaultHttpHeaders();
        // Initialize Http entity
        HttpEntity<Todo> httpEntity = new HttpEntity<Todo>(todoInfoRequest, httpHeaders);
        // Send the request
        ResponseEntity<Todo> responseEntity = restTemplate.exchange(host + "/" + todoInfoRequest.getId(), HttpMethod.PUT, httpEntity, Todo.class);
        // Validate
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertNotNull(responseEntity.getBody());
        Todo receivedTodoInfo = responseEntity.getBody();
        Assertions.assertNotNull(receivedTodoInfo);
        Assertions.assertEquals(todoInfoRequest.getId(), receivedTodoInfo.getId());
        Assertions.assertEquals(updatedTodo, receivedTodoInfo.getTodo());
    }

    @Test
    void updateTodoInvalidTodoId() {
        // Get the request object
        Todo todoInfoRequest = new Todo(storedList.get(0).getId());
        String updatedTodoName = storedList.get(0).getTodo().concat("updated");
        todoInfoRequest.setTodo(updatedTodoName);
        // Initialize the request headers
        HttpHeaders httpHeaders = getDefaultHttpHeaders();
        // Initialize Http entity
        HttpEntity<Todo> httpEntity = new HttpEntity<Todo>(todoInfoRequest, httpHeaders);
        // Send the request
        try {
            restTemplate.exchange(host + "/" + SAMPLE_INVALID_TODO_ID, HttpMethod.PUT, httpEntity, Todo.class);
            //Assertions.fail();
        } catch (HttpClientErrorException ex) {
            Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), ex.getRawStatusCode());
            Assertions.assertNotNull(ex.getResponseBodyAsString());
            Assertions.assertTrue(ex.getResponseBodyAsString().contains(ExceptionMessage.TODO_DOES_NOT_EXISTS));
        }
    }

    @Test
    void deleteTodo() {
        // Get the tenant id to delete
        String todoIdToDelete = storedList.get(0).getId();
        // Initialize the request headers
        HttpHeaders httpHeaders = getDefaultHttpHeaders();
        // Initialize Http entity
        HttpEntity<String> httpEntity = new HttpEntity<String>(null, httpHeaders);
        // Send the request
        ResponseEntity<String> responseEntity = restTemplate.exchange(host + "/" + todoIdToDelete, HttpMethod.DELETE, httpEntity, String.class);
        // Validate
        Assertions.assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    void deleteTodoInvalidTodoId() {
        // Initialize the request headers
        HttpHeaders httpHeaders = getDefaultHttpHeaders();
        // Initialize Http entity
        HttpEntity<String> httpEntity = new HttpEntity<String>(null, httpHeaders);
        // Send the request
        try {
            restTemplate.exchange(host + "/" + SAMPLE_INVALID_TODO_ID, HttpMethod.DELETE, httpEntity, String.class);
            //Assertions.fail();
        } catch (HttpClientErrorException ex) {
            Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), ex.getRawStatusCode());
            Assertions.assertNotNull(ex.getResponseBodyAsString());
            Assertions.assertTrue(ex.getResponseBodyAsString().contains(ExceptionMessage.TODO_DOES_NOT_EXISTS));
        }
    }

    @Test
    void getAllTodos() {
        // Initialize the request headers
        HttpHeaders httpHeaders = getDefaultHttpHeaders();
        // Initialize Http entity
        HttpEntity<String> httpEntity = new HttpEntity<String>(null, httpHeaders);
        // Send the request
        ResponseEntity<List<Todo>> responseEntity = restTemplate.exchange(host, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<Todo>>() {
        });
        // Validate
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertNotNull(responseEntity.getBody());
        List<Todo> todoInfoList = responseEntity.getBody();
        Assertions.assertNotNull(todoInfoList);
        Assertions.assertTrue(todoInfoList.size() > 0);
    }

    @Test
    void getTodoByTodoId() throws JsonProcessingException {
        // Initialize the request headers
        HttpHeaders httpHeaders = getDefaultHttpHeaders();
        // Initialize Http entity
        HttpEntity<String> httpEntity = new HttpEntity<String>(null, httpHeaders);
        // Send the request
        ResponseEntity<String> responseEntity = restTemplate.exchange(host + "/" + SAMPLE_TODO_ID, HttpMethod.GET, httpEntity, String.class);
        // Validate
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertNotNull(responseEntity.getBody());
        ObjectMapper objectMapper = new ObjectMapper();
        Todo todoInfo = objectMapper.readValue(responseEntity.getBody(), Todo.class);
        Assertions.assertNotNull(todoInfo);
        Assertions.assertEquals(SAMPLE_TODO_ID, todoInfo.getId());
    }

    @Test
    void getTodoByInvalidTodoId() {
        // Initialize the request headers
        HttpHeaders httpHeaders = getDefaultHttpHeaders();
        // Initialize Http entity
        HttpEntity<String> httpEntity = new HttpEntity<String>(null, httpHeaders);
        // Send the request
        try {
            restTemplate.exchange(host + "/" + SAMPLE_INVALID_TODO_ID, HttpMethod.GET, httpEntity, String.class);
            //Assertions.fail();
        } catch (HttpClientErrorException ex) {
            Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), ex.getRawStatusCode());
            Assertions.assertNotNull(ex.getResponseBodyAsString());
            Assertions.assertTrue(ex.getResponseBodyAsString().contains(ExceptionMessage.TODO_DOES_NOT_EXISTS));
        }
    }

    @AfterEach
    public void tearDown() throws Exception {
        super.tearDown();
    }

    private HttpHeaders getDefaultHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        // Return
        return httpHeaders;
    }
}


