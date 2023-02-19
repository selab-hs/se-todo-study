package com.selab.todo.service;

import com.selab.todo.dto.response.DiaryResponse;
import com.selab.todo.repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FindService {
    public Page<DiaryResponse> getMonthRange(Page<DiaryResponse> allPage, int month) {
        List<DiaryResponse> middleProcess = allPage
                .stream()
                .filter(a -> a.getMonth() == month)
                .collect(Collectors.toList());

        PageRequest pageRequest = PageRequest.of(0, 10);

        int start = (int) pageRequest.getOffset();
        int end = Math.min((start + pageRequest.getPageSize()), middleProcess.size());
        return new PageImpl<>(middleProcess.subList(start, end), pageRequest, middleProcess.size());
    }
}