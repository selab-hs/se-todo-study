package com.selab.todo.service;

import com.selab.todo.dto.request.TodoRegisterRequest;
import com.selab.todo.dto.request.TodoUpdateRequest;
import com.selab.todo.dto.response.TodoResponse;
import com.selab.todo.entity.Todo;
import com.selab.todo.exception.TodoException;
import com.selab.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
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

        log.info("todo 등록했습니다. {}", todo.getId());

        return TodoResponse.from(savedTodo);
    }

    // 단건 조회
    @Transactional(readOnly = true)
    public TodoResponse get(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(TodoException::new);

        return TodoResponse.from(todo);
    }

    // 페이징 조회
    @Transactional(readOnly = true)
    public Page<TodoResponse> getAll(Pageable pageable) {
        return todoRepository.findAll(pageable)
                .map(TodoResponse::from);
    }

    // 수정
    @Transactional
    public TodoResponse update(Long id, TodoUpdateRequest request) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(TodoException::new);

        // 더티체킹 - 영속성 컨텍스트
        todo.update(request.getTitle(), request.getContent());

        log.info("todo 수정했습니다. {}", todo.getId());

        return TodoResponse.from(todo);
    }

    // 삭제
    @Transactional
    public void delete(Long id) {
        if (todoRepository.existsById(id)) {
            todoRepository.deleteById(id);
            log.info("todo 삭제했습니다.. {}", id);
        }
    }
}
