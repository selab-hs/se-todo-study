package com.selab.todo.dto.response;

import com.selab.todo.entity.Diary;
import com.selab.todo.model.Feeling;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DiaryResponse {
    private final Long id;
    private final String title;
    private final String content;
    private final LocalDateTime localDateTime;
    private final Feeling feel;
    private final int year;
    private final int month;
    private final int day;

    public static DiaryResponse from(Diary diary) {
        return new DiaryResponse(
                diary.getId(),
                diary.getTitle(),
                diary.getContent(),
                diary.getCreatedAt(),
                diary.getFeel(),
                diary.getYear(),
                diary.getMonth(),
                diary.getDay()
        );
    }
}
