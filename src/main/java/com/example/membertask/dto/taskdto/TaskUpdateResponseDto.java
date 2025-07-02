package com.example.membertask.dto.taskdto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TaskUpdateResponseDto {

    private Integer status;

    private String message;

    private String title;

    private String content;

    private Long memberId;

    private Long taskId;

    public TaskUpdateResponseDto(
            Integer status, String message,
            Long memberId, Long taskId,
            String title, String content
    ) {
        this.status = status;
        this.message = message;
        this.memberId = memberId;
        this.taskId = taskId;
        this.title = title;
        this.content = content;
    }
}
