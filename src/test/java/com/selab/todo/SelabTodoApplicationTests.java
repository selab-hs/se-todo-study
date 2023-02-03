package com.selab.todo;

import com.selab.todo.dto.response.TodoResponse;
import com.selab.todo.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class SelabTodoApplicationTests {
    TodoRepository todoRepository;
    @Test
    void contextLoads(@PageableDefault Pageable pageable) {
        Page<TodoResponse> page = todoRepository.findAll(pageable).map(TodoResponse::from);
        List<TodoResponse> asd = page.stream().filter(a->a.getLocalDateTime().getMonth().getValue()==2).collect(Collectors.toList());
    }

}
