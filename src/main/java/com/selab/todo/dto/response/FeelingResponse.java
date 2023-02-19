package com.selab.todo.dto.response;

import com.selab.todo.entity.Diary;
import com.selab.todo.entity.Feeling;
import lombok.Data;

@Data
public class FeelingResponse {
    private final Long id;
    private final String feel;

    public static FeelingResponse from(Diary diary){
        return new FeelingResponse(
                diary.getId(),
                diary.getFeel()
        );
    }
}
