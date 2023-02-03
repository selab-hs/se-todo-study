package com.selab.todo.dto.response;

import com.selab.todo.entity.Todo;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TodoResponse {
    private final Long id;
    private final String title;
    private final String content;
    private final LocalDateTime localDateTime;

    public static TodoResponse from(Todo todo) {
        return new TodoResponse(
                todo.getId(),
                todo.getTitle(),
                todo.getContent(),
                todo.getCreatedAt()
        );
    }
}
