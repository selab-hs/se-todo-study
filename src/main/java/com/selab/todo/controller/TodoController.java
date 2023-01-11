package com.selab.todo.controller;

import com.selab.todo.dto.request.TodoRegisterRequest;
import com.selab.todo.dto.response.TodoResponse;
import com.selab.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/todos", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public TodoResponse register(@RequestBody TodoRegisterRequest request) {
        return todoService.register(request);
    }

    @GetMapping("/{id}")
    public TodoResponse get(@PathVariable Long id) {
        return todoService.get(id);
    }
}
