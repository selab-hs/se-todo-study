package com.selab.todo.dto.request.feel;

import lombok.Data;

@Data
public class FeelingRegisterRequest {
    private final Long id;
    private final String feel;
}
