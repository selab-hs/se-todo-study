package com.selab.todo.dto.request.diary;

import lombok.Data;

@Data
public class DiaryUpdateRequest {
    private final String title;
    private final String content;
    private final String feel;
}
