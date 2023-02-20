package com.selab.todo.controller;

import com.selab.todo.dto.request.search.MonthSearchRequest;
import com.selab.todo.dto.request.search.SearchRequest;
import com.selab.todo.dto.request.search.YearSearchRequest;
import com.selab.todo.dto.request.search.FeelingSearchRequest;
import com.selab.todo.dto.response.DiaryResponse;
import com.selab.todo.service.DiaryService;
import com.selab.todo.service.DataSearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"Diary API"})
@RestController
@RequestMapping(value = "/api/v1/Diarys/search", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class SearchController {
    private final DataSearchService dataSearchService;
    private final DiaryService diaryService;

    @ApiOperation("Month 별로 조회하기")
    @GetMapping(value = "/month",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Page<DiaryResponse> searchMonth(@PageableDefault Pageable pageable, MonthSearchRequest request) {
        Page<DiaryResponse> diaryResponses = diaryService.getAll(pageable);
        return dataSearchService.searchMonth(diaryResponses, request.getMonth());
    }

    @ApiOperation("Year 별로 조회하기")
    @GetMapping(value = "/year",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Page<DiaryResponse> searchYear(@PageableDefault Pageable pageable, YearSearchRequest request) {
        Page<DiaryResponse> diaryResponses = diaryService.getAll(pageable);
        return dataSearchService.searchYear(diaryResponses, request.getYear());
    }

    @ApiOperation("Year, Month, Day 조회")
    @GetMapping(value = "/year-month-day",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Page<DiaryResponse> searchYear(@PageableDefault Pageable pageable, SearchRequest request) {
        Page<DiaryResponse> diaryResponses = diaryService.getAll(pageable);
        return dataSearchService.search(diaryResponses, request);
    }

    @ApiOperation("Feeling 조회")
    @GetMapping(value = "/feel")
    public Page<DiaryResponse> searchFeeling(@PageableDefault Pageable pageable, FeelingSearchRequest request){
        Page<DiaryResponse> diaryResponses = diaryService.getAll(pageable);
        return dataSearchService.searchFeel(diaryResponses, request.getFeeling());
    }
}
