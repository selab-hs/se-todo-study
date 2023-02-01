package com.selab.todo.dto.response;

import com.selab.todo.entity.Todo;
import lombok.Data;

@Data
public class TodoResponse {
    private final Long id;
    private final String title;
    private final String content;

    public static TodoResponse from(Todo todo) {
        return new TodoResponse(
                todo.getId(),
                todo.getTitle(),
                todo.getContent()
        );
    }
}
