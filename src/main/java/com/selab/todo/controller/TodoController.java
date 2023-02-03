package com.selab.todo.controller;

import com.selab.todo.common.dto.PageDto;
import com.selab.todo.common.dto.ResponseDto;
import com.selab.todo.dto.request.TodoRegisterRequest;
import com.selab.todo.dto.request.TodoUpdateRequest;
import com.selab.todo.service.TodoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

@Api(tags = {"TODO API"})
@RestController
@RequestMapping(value = "/api/v1/todos", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @ApiOperation(value = "TODO 등록하기")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/{feel}")
    public ResponseEntity<?> register(@RequestBody TodoRegisterRequest request, @PathVariable String feel) {
        var response = todoService.register(request, feel);
        return ResponseDto.created(response);
    }

    @ApiOperation(value = "TODO 단건 조회하기")
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        var response = todoService.get(id);
        return ResponseDto.ok(response);
    }

    @ApiOperation(value = "TODO 범위 조회")
    @GetMapping("/month/{month}")
    public ResponseEntity<?> getRange(
            @PathVariable int month,
            @PageableDefault Pageable pageable
    ) {
        var response = todoService.getRange(pageable,month);
        return PageDto.ok(response);
    }

    @ApiOperation(value = "TODO 전체 조회하기")
    @GetMapping
    public ResponseEntity<?> getAll(
            @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        var response = todoService.getAll(pageable);
        return PageDto.ok(response);
    }

    @ApiOperation(value = "TODO 수정하기")
    @PutMapping("/{id}") // @PatchMapping
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestBody TodoUpdateRequest request
    ) {
        var response = todoService.update(id, request);
        return ResponseDto.ok(response);
    }

    @ApiOperation(value = "TODO 삭제하기")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ) {
        todoService.delete(id);
        return ResponseDto.noContent();
    }
}
