package com.example.springbootTodo.domain;

import com.example.springbootTodo.constant.Constant;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection= Constant.TodoService.TODO_COLLECTION)
public class Todo {

    @Id
    private String id;

    private String todo;

    private String description;

    private String status;

    @CreatedDate
    @JsonFormat(pattern = Constant.MONGODB_DATE_FORMAT)
    private Date createdAt;

    @CreatedDate
    @JsonFormat(pattern = Constant.MONGODB_DATE_FORMAT)
    private Date updatedAt;

    private String errorMessage;

    //private Todo todos;

    public  Todo(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Instantiate a new TodoVariable
     *
     * @param id
     * @param todo
     * @param description
     * @param status
     */
    public Todo(String id, String todo, String description, String status ){
        this.id = id;
        this.todo = todo;
        this.description = description;
        this.status = status;

    }

    public Todo() {

    }

//    public Todo getTodos() {
//        return todos;
//    }

    /**
     * Gets id.
     *
     * @return the id
     * @param
     */
    public String getId() {
        return id;
    }

    /**
     * Gets todos name
     *
     * @return the todos name
     */
    public String getTodo(){
        return todo;
    }

    /**
     *Gets todos description
     *
     * @return the todos description
     */
    public String getDescription() {

        return description;
    }

    /**
     * Gets todos status pending or completed
     *
     * @return the todos status
     */
    public String getStatus() {

        return status;
    }

    /**
     * Gets todos created date and time
     *
     * @return the created date and time
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * Gets todos updated date and time
     *
     * @return the updated date and time
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Sets todos id
     *
     * @param id the todos id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Sets todos name
     *
     * @param todo
     */
    public void setTodo(String todo) {

        this.todo = todo;
    }

    /**
     * Sets todos description
     *
     * @param description the todos description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets todos status pending or completed
     *
     * @param status the todos status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Sets todos created date and time
     *
     * @param createdAt the todos created date and time
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Sets todos updated date and time
     *
     * @param updatedAt the todos updated date and time
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

}
