package com.selab.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = {"com.selab.todo.entity"})
@SpringBootApplication
public class SelabTodoApplication {
    public static void main(String[] args) {
        SpringApplication.run(SelabTodoApplication.class, args);
    }
}
