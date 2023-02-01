package com.selab.todo.controller;

import com.selab.todo.common.dto.PageDto;
import com.selab.todo.common.dto.ResponseDto;
import com.selab.todo.dto.request.TodoRegisterRequest;
import com.selab.todo.dto.request.TodoUpdateRequest;
import com.selab.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/todos", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> register(@RequestBody TodoRegisterRequest request) {
        var response = todoService.register(request);
        return ResponseDto.created(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        var response = todoService.get(id);
        return ResponseDto.ok(response);
    }

    @GetMapping
    public ResponseEntity<?> getAll(
            @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        var response = todoService.getAll(pageable);
        return PageDto.ok(response);
    }

    @PutMapping("/{id}") // @PatchMapping
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestBody TodoUpdateRequest request
    ) {
        var response = todoService.update(id, request);
        return ResponseDto.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ) {
        todoService.delete(id);
        return ResponseDto.noContent();
    }
}
