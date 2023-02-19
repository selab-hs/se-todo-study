package com.selab.todo.controller;

import com.selab.todo.common.dto.PageDto;
import com.selab.todo.common.dto.ResponseDto;
import com.selab.todo.dto.request.diary.DiaryRegisterRequest;
import com.selab.todo.dto.request.diary.DiaryUpdateRequest;
import com.selab.todo.dto.request.feel.FeelingUpdateRequest;
import com.selab.todo.service.DiaryService;
import com.selab.todo.service.FeelingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"Diary API"})
@RestController
@RequestMapping(value = "/api/v1/Diarys", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class DiaryController {
    private final DiaryService diaryService;
    private final FeelingService feelingService;

    @ApiOperation(value = "Diary 등록하기")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/register")
    public ResponseEntity<?> register(@RequestBody DiaryRegisterRequest request) {
        var response = diaryService.register(request);
        return ResponseDto.created(response);
    }

    @ApiOperation(value = "Diary 단건 조회하기")
    @GetMapping("/search/single/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        var response = diaryService.get(id);
        return ResponseDto.ok(response);
    }

    @ApiOperation(value = "Diary 범위 조회")
    @GetMapping("/search/month/{month}")
    public ResponseEntity<?> getRange(
            @PathVariable int month,
            @PageableDefault Pageable pageable
    ) {
        var response = diaryService.getRange(pageable, month);
        return PageDto.ok(response);
    }

    @ApiOperation(value = "Diary 전체 조회하기")
    @GetMapping("/search/all")
    public ResponseEntity<?> getAll(
            @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        var response = diaryService.getAll(pageable);
        return PageDto.ok(response);
    }

    @ApiOperation(value = "Diary 수정하기")
    @PutMapping("/update/{id}") // @PatchMapping
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestBody DiaryUpdateRequest request
    ) {
        var response = diaryService.update(id, request);
        return ResponseDto.ok(response);
    }

    @ApiOperation(value = "Diary 삭제하기")
    @DeleteMapping("/delete/id/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ) {
        diaryService.delete(id);
        return ResponseDto.noContent();
    }

    @ApiOperation(value = "Diary 월별 삭제하기")
    @DeleteMapping("/delete/month/{month}")
    public ResponseEntity<Void> monthDelete(
            @PathVariable int month
    ) {
        diaryService.monthDelete(month);
        return ResponseDto.noContent();
    }

    @ApiOperation(value = "Feeling 수정하기")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/update/feeling")
    public ResponseEntity<?> updateFeeling(@RequestBody FeelingUpdateRequest request) {
        var response = feelingService.updateFeeling(request);
        return ResponseDto.ok(response);
    }

    @ApiOperation(value = "Feeling 전체 조회하기")
    @GetMapping("/serach/feeling/all")
    public ResponseEntity<?> getAllFeeling(@PageableDefault Pageable pageable) {
        var response = feelingService.getAllFeeling(pageable);
        return PageDto.ok(response);
    }
}
