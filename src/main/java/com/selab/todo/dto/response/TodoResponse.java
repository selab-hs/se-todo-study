package com.selab.todo.dto.response;

import lombok.Data;

@Data
public class TodoResponse {
    private final Long id;
    private final String title;
    private final String content;
}
