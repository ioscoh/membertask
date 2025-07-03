package com.example.membertask.dto.taskdto;

import lombok.Getter;

@Getter
public class TaskDeleteResponseDto {

    private Integer status;

    private String message;

    public TaskDeleteResponseDto(Integer status, String message) {
        this.status = status;
        this.message = message;
    }
}
