package com.selab.todo.service;

import com.selab.todo.dto.request.TodoRegisterRequest;
import com.selab.todo.dto.response.TodoResponse;
import com.selab.todo.entity.Todo;
import com.selab.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    // 삽입
    @Transactional
    public TodoResponse register(TodoRegisterRequest request) {
        Todo todo = new Todo(
                request.getTitle(),
                request.getContent()
        );

        Todo savedTodo = todoRepository.save(todo);

        return new TodoResponse(
                savedTodo.getId(),
                savedTodo.getTitle(),
                savedTodo.getContent()
        );
    }

    // 단건 조회
    @Transactional(readOnly = true)
    public TodoResponse get(Long id) {
        Todo todo = todoRepository.findById(id).get();

        return new TodoResponse(
                todo.getId(),
                todo.getTitle(),
                todo.getContent()
        );
    }

    // 페이징 조회

    // 수정

    // 삭제
}
