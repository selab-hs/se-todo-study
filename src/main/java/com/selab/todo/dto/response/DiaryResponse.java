package com.selab.todo.dto.response;

import com.selab.todo.entity.Diary;
import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;

@Data
public class DiaryResponse {
    private final Long id;
    private final String title;
    private final String content;
    private final LocalDateTime localDateTime;
    private final String feel;
    private final Year year;
    private final Month month;
    private final DayOfWeek day;

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
