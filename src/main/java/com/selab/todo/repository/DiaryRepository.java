package com.selab.todo.repository;

import com.selab.todo.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {

    //월별 삭제 쿼리
    @Query(
            value = "delete diary" +
                    "where month > : month"
            ,nativeQuery = true
    )
    public List<Diary> deleteDiariesByMonth(@Param(value = "month") int month);
}
