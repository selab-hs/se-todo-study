package com.selab.todo.exception;

public class DiaryException extends BusinessException {
    public DiaryException() {
        super(ErrorMessage.TODO_NOT_FOUND_ERROR);
    }
}
