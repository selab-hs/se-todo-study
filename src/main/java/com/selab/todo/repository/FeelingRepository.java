package com.selab.todo.repository;

import com.selab.todo.entity.Feeling;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeelingRepository extends JpaRepository<Feeling, String> {
}
