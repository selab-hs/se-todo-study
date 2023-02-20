package com.selab.todo.dto.request.search;

import lombok.Data;

@Data
public class SearchRequest {
    private final int year;
    private final int month;
    private final int day;
}
