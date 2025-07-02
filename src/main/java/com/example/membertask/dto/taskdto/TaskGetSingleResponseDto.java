package com.example.membertask.dto.taskdto;

import lombok.Getter;

@Getter
public class TaskGetSingleResponseDto {
    private Integer status;

    private String message;

    private Long memberId;

    private Long taskId;

    private String memberName;

    private String title;

    private String content;

    public TaskGetSingleResponseDto(
            Integer status, String message, Long memberId,
            Long taskId, String memberName, String title,
            String content
    ) {
        this.status = status;
        this.message = message;
        this.memberId = memberId;
        this.taskId = taskId;
        this.memberName = memberName;
        this.title = title;
        this.content = content;
    }
}
