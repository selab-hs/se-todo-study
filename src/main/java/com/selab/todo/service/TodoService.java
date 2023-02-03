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
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Slf4j
@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    // 삽입
    @Transactional
    public TodoResponse register(TodoRegisterRequest request, String feel) {
        Todo todo = new Todo(
                request.getTitle(),
                request.getContent(),
                feel
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
        log.info("todo 조회했습니다, {}", todo.getId());
        return TodoResponse.from(todo);
    }

    // 페이징 조회
    @Transactional(readOnly = true)
    public Page<TodoResponse> getAll(Pageable pageable) {
        log.info("todo 전체 조회");
        return todoRepository.findAll(pageable)
                .map(TodoResponse::from);
    }

    //범위 조회
    @Transactional(readOnly = true)
    public Page<TodoResponse> getRange(Pageable pageable, int month) {
        log.info("Todo 범위 조회");
        Page<TodoResponse> allPage = todoRepository.findAll(pageable).map(TodoResponse::from);
        List<TodoResponse> middleProcess = allPage.stream().filter(a->a.getLocalDateTime().getMonth().getValue()==month).collect(Collectors.toList());
        PageRequest pageRequest = PageRequest.of(0,10);
        int start = (int) pageRequest.getOffset();
        int end = Math.min((start+pageRequest.getPageSize()), middleProcess.size());
        return new PageImpl<>(middleProcess.subList(start,end),pageRequest,middleProcess.size());
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
