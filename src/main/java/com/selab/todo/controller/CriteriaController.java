package com.selab.todo.controller;

import com.selab.todo.common.dto.ResponseDto;
import com.selab.todo.service.CriteriaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"Diary API"})
@RestController
@RequestMapping(value = "/api/v1/diaries/criteria", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CriteriaController {

    private final CriteriaService criteriaService;

    @ApiOperation("Creieria Feeling Search")
    @GetMapping("/search-feeling/{feeling}")
    public ResponseEntity<?> searchFeeling(@PathVariable String feeling){
        var response = criteriaService.searchFeel(feeling);

        return ResponseDto.ok(response);
    }
}
