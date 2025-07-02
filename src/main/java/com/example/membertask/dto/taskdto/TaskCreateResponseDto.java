package com.example.membertask.dto.taskdto;

import lombok.Getter;

@Getter
public class TaskCreateResponseDto {

    private Integer status;

    private String message;

    private Long id;

    public TaskCreateResponseDto(
            Integer status, String message, Long id
    ) {
        this.status = status;
        this.message = message;
        this.id = id;
    }
}
