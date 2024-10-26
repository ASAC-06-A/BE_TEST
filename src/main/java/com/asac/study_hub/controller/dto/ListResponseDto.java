package com.asac.study_hub.controller.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ListResponseDto<T> {
    Integer total;
    List<T> response;

    public ListResponseDto(Integer total, List<T> responseDto) {
        this.total = total;
        this.response = responseDto;
    }
}
