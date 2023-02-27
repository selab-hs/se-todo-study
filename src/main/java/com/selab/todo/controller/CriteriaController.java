package com.selab.todo.controller;

import com.selab.todo.common.dto.ResponseDto;
import com.selab.todo.dto.request.diary.DiaryRegisterRequest;
import com.selab.todo.service.CriteriaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"Diary API"})
@RestController
@RequestMapping(value = "/api/v1/diaries/criteria", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CriteriaController {

    private final CriteriaService criteriaService;

    @ApiOperation("Criteria Register")
    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> register(@RequestBody DiaryRegisterRequest request){
        var response = criteriaService.register(request);
        return ResponseDto.created(response);
    }

    @ApiOperation("Criteria 단건 조회")
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id){
        var response = criteriaService.get(id);
        return ResponseDto.ok(response);
    }

    @ApiOperation("Criteria 전체 조회")
    @GetMapping(value = "/getAll")
    public ResponseEntity<?> getAll(){
        var response = criteriaService.getAll();
        return ResponseDto.ok(response);
    }
}
