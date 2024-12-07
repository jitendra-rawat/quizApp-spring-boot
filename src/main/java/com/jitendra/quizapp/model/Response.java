package com.jitendra.quizapp.model;

public class Response {

    private Integer id;
    private String response;

    // Default constructor
    public Response() {
    }

    // Parameterized constructor
    public Response(Integer id, String response) {
        this.id = id;
        this.response = response;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    // Override toString method for logging/debugging
    @Override
    public String toString() {
        return "Response{" +
                "id=" + id +
                ", response='" + response + '\'' +
                '}';
    }
}
