package com.selab.todo.exception;

public class TodoException extends BusinessException {
    public TodoException() {
        super(ErrorMessage.TODO_NOT_FOUND_ERROR);
    }
}
