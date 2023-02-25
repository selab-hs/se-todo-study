package com.selab.todo.service;

import com.selab.todo.dto.response.DiaryResponse;
import com.selab.todo.entity.Diary;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.DayOfWeek;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CriteriaService {

    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");

    @Transactional
    public Page<DiaryResponse> searchFeel(String feeling){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Diary> criteriaQuery = criteriaBuilder.createQuery(Diary.class);
        Root<Diary> root = criteriaQuery.from(Diary.class);

        Predicate feelEqual = criteriaBuilder.equal(root.get("feeling"), feeling);

        javax.persistence.criteria.Order idDesc = criteriaBuilder.desc(root.get("id"));

        criteriaQuery.select(root)
                .where(feelEqual)
                .orderBy(idDesc);

        List<Diary> result = entityManager.createQuery(criteriaQuery).getResultList();

        List<DiaryResponse> mapping = new ArrayList<>();

        result.stream()
                .forEach(a->{
                    mapping.add(DiaryResponse.from(a));
                });

        return makePage(mapping);
    }

    private PageImpl<DiaryResponse> makePage(List<DiaryResponse> process) {
        PageRequest pageRequest = PageRequest.of(0, 10);
        int start = (int) pageRequest.getOffset();
        int end = Math.min((start + pageRequest.getPageSize()), process.size());
        return new PageImpl<>(process.subList(start, end), pageRequest, process.size());
    }
}
