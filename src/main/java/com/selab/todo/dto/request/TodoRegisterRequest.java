package com.selab.todo.dto.request;

import lombok.Data;

@Data
public class TodoRegisterRequest {
    private final String title;
    private final String content;
    private final String feel;
}
