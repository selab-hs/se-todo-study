package com.selab.todo.dto.request.diary;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class DiaryRegisterRequest {
    private final String title;
    private final String content;
    private final String feel;
    private int year;
    private int month;
    private int day;
}
