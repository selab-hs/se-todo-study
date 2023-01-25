package com.selab.todo.controller;

import com.selab.todo.dto.request.TodoRegisterRequest;
import com.selab.todo.dto.request.TodoUpdateRequest;
import com.selab.todo.dto.response.TodoResponse;
import com.selab.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/todos", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    // RestControllerAdvice, ExceptionHandler, ResponseEntity
    // TODO : RESPONSE ENTITY, ERROR HANDLING
    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public TodoResponse register(@RequestBody TodoRegisterRequest request) {
        return todoService.register(request);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/{id}")
    public TodoResponse get(@PathVariable Long id) {
        return todoService.get(id);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping
    public Page<TodoResponse> getAll(
            @PageableDefault(page = 0, size = 10) Pageable pageable
    ) {
        return todoService.getAll(pageable);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @PutMapping("/{id}") // @PatchMapping
    public TodoResponse update(
            @PathVariable Long id,
            @RequestBody TodoUpdateRequest request
    ) {
        return todoService.update(id, request);
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id
    ) {
        todoService.delete(id);
    }
}
