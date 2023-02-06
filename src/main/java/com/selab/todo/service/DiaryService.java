package com.selab.todo.service;

import com.selab.todo.dto.request.DiaryRegisterRequest;
import com.selab.todo.dto.request.DiaryUpdateRequest;
import com.selab.todo.dto.response.DiaryResponse;
import com.selab.todo.entity.Diary;
import com.selab.todo.exception.DiaryException;
import com.selab.todo.repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class DiaryService {
    private final DiaryRepository diaryRepository;

    // 삽입
    @Transactional
    public DiaryResponse register(DiaryRegisterRequest request) {
        Diary diary = new Diary(
                request.getTitle(),
                request.getContent(),
                request.getFeel(),
                request.getYear(),
                request.getMonth(),
                request.getDay()
        );

        Diary savedDiary = diaryRepository.save(diary);

        log.info("todo 등록했습니다. {}", diary.getId());

        return DiaryResponse.from(savedDiary);
    }

    // 단건 조회
    @Transactional(readOnly = true)
    public DiaryResponse get(Long id) {
        Diary diary = diaryRepository.findById(id)
                .orElseThrow(DiaryException::new);
        log.info("todo 조회했습니다, {}", diary.getId());
        return DiaryResponse.from(diary);
    }

    // 페이징 조회
    @Transactional(readOnly = true)
    public Page<DiaryResponse> getAll(Pageable pageable) {
        log.info("todo 전체 조회");
        return diaryRepository.findAll(pageable)
                .map(DiaryResponse::from);
    }

    //범위 조회
    @Transactional(readOnly = true)
    public Page<DiaryResponse> getRange(Pageable pageable, int month) {
        log.info("Todo 범위 조회");
        Page<DiaryResponse> allPage = diaryRepository.findAll(pageable).map(DiaryResponse::from);
        List<DiaryResponse> middleProcess = allPage.stream().filter(a -> a.getLocalDateTime().getMonth().getValue() == month).collect(Collectors.toList());
        PageRequest pageRequest = PageRequest.of(0, 10);
        int start = (int) pageRequest.getOffset();
        int end = Math.min((start + pageRequest.getPageSize()), middleProcess.size());
        return new PageImpl<>(middleProcess.subList(start, end), pageRequest, middleProcess.size());
    }

    // 수정
    @Transactional
    public DiaryResponse update(Long id, DiaryUpdateRequest request) {
        Diary diary = diaryRepository.findById(id)
                .orElseThrow(DiaryException::new);

        // 더티체킹 - 영속성 컨텍스트
        diary.update(request.getTitle(), request.getContent(), request.getFeel());

        log.info("todo 수정했습니다. {}", diary.getId());

        return DiaryResponse.from(diary);
    }

    // 삭제
    @Transactional
    public void delete(Long id) {
        if (diaryRepository.existsById(id)) {
            diaryRepository.deleteById(id);
            log.info("todo 삭제했습니다.. {}", id);
        }
    }

    //월별 삭제
    @Transactional
    public void monthDelete(int month) {
        List<Long> deleteId = new LinkedList<>();

        diaryRepository.findAll().forEach(eachDiary -> {
            if (eachDiary.getMonth() == month) {
                deleteId.add(eachDiary.getId());
            }
        });

        deleteId.forEach(id -> {
            if (diaryRepository.existsById(id)) {
                diaryRepository.deleteById(id);
                log.info("Month Todo 삭제했습니다. {}", id);
            }
        });
    }
}
