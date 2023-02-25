package com.selab.todo;

import com.selab.todo.entity.Diary;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.DayOfWeek;
import java.time.Month;
import java.time.Year;

public class CriteriaServiceTest {

    @Test
    public void a(){
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
