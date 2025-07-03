package com.example.membertask.dto.taskdto;

import lombok.Getter;

@Getter
public class TaskCreateResponseDto {

    private Integer status;

    private String message;

    private Long memberId;

    private Long taskId;

    public TaskCreateResponseDto(
            Integer status, String message, Long memberId, Long taskId
    ) {
        this.status = status;
        this.message = message;
        this.memberId = memberId;
        this.taskId = taskId;
    }
}
