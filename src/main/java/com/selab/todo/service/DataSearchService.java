package com.selab.todo.service;

import com.selab.todo.dto.request.search.SearchRequest;
import com.selab.todo.dto.response.DiaryResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class DataSearchService {
    public Page<DiaryResponse> searchMonth(Page<DiaryResponse> allPage, int month) {
        List<DiaryResponse> middleProcess = allPage.stream()
                .filter(a -> a.getMonth() == month)
                .collect(Collectors.toList());

        log.info("Month 조회. {}", month);
        return makePage(middleProcess);
    }

    public Page<DiaryResponse> searchYear(Page<DiaryResponse> allPage, int year) {
        List<DiaryResponse> middleProcess = allPage.stream()
                .filter(a -> a.getMonth() == year)
                .collect(Collectors.toList());

        log.info("Year 조회. {}", year);
        return makePage(middleProcess);
    }

    public Page<DiaryResponse> search(Page<DiaryResponse> allPage, SearchRequest request) {
        List<DiaryResponse> middleProcess = allPage.stream()
                .filter(a -> a.getYear() == request.getYear())
                .filter(a -> a.getMonth() == request.getMonth())
                .filter(a -> a.getDay() == a.getDay())
                .collect(Collectors.toList());

        log.info("전체 날짜 조회.");
        return makePage(middleProcess);
    }

    public Page<DiaryResponse> searchFeel(Page<DiaryResponse> allPage, String feeling) {
        List<DiaryResponse> middleProcess = allPage.stream()
                .filter(a -> a.getFeel().equals(feeling))
                .collect(Collectors.toList());

        log.info("Feeling 조회. {}", feeling);
        return makePage(middleProcess);
    }

    private PageImpl<DiaryResponse> makePage(List<DiaryResponse> process) {
        PageRequest pageRequest = PageRequest.of(0, 10);
        int start = (int) pageRequest.getOffset();
        int end = Math.min((start + pageRequest.getPageSize()), process.size());
        return new PageImpl<>(process.subList(start, end), pageRequest, process.size());
    }
}