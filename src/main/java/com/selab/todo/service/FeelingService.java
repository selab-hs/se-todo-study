package com.selab.todo.service;

import com.selab.todo.dto.request.diary.DiaryUpdateRequest;
import com.selab.todo.dto.request.feel.FeelingRegisterRequest;
import com.selab.todo.dto.request.feel.FeelingUpdateRequest;
import com.selab.todo.dto.response.DiaryResponse;
import com.selab.todo.dto.response.FeelingResponse;
import com.selab.todo.entity.Diary;
import com.selab.todo.entity.Feeling;
import com.selab.todo.exception.DiaryException;
import com.selab.todo.repository.DiaryRepository;
import com.selab.todo.repository.FeelingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeelingService {
    private final DiaryRepository diaryRepository;
    private final FeelingRepository feelingRepository;

    @Transactional
    public FeelingResponse register(FeelingRegisterRequest request){
        Feeling feeling = new Feeling(
                request.getId(),
                request.getFeel()
        );
        return FeelingResponse.from(feeling);
    }

    @Transactional
    public FeelingResponse updateFeeling(Long id, FeelingUpdateRequest request){
        Feeling feeling = feelingRepository.findById(id).orElseThrow(DiaryException::new);

        log.info("Diary feeling 수정했습니다. {}", feeling.getId());

        return FeelingResponse.from(feeling);
    }

    @Transactional(readOnly = true)
    public Page<FeelingResponse> getAllFeeling(Pageable pageable){
        log.info("Diary feeling 전체 조회");
        return diaryRepository.findAll(pageable)
                .map(FeelingResponse::from);
    }
}
