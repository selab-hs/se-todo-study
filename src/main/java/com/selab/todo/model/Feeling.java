package com.selab.todo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Feeling {
    GOOD("Good", 1),
    BAD("Bad", 2),
    NOT_BAD("Not Bad",3),
    PERFECT("Perfect",4),
    WORST("Worst",5);

    private final String feel;
    private final int value;
}
