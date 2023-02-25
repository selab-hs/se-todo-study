package com.selab.todo.service;

import com.selab.todo.entity.Diary;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.DayOfWeek;
import java.time.Month;
import java.time.Year;

@Slf4j
@Service
@RequiredArgsConstructor
public class CriteriaService {

    @Transactional
    public void get(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try{
            Diary diary = new Diary(
                    "Test",
                    "test",
                    "Good",
                    Year.now(),
                    Month.of(2),
                    DayOfWeek.MONDAY
            );

            entityManager.persist(diary);
        }catch (Exception e){
            transaction.rollback();
        }finally {
            entityManager.close();
        }

        entityManagerFactory.close();
    }
}
