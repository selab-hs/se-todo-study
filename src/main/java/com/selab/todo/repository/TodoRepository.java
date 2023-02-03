package com.selab.todo.repository;

import com.selab.todo.dto.response.TodoResponse;
import com.selab.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
}
