package com.example.membertask.dto.taskdto;

import lombok.Getter;

@Getter
public class TaskCreateRequestDto {

    private Long memberId;

    private String title;

    private String content;

    public TaskCreateRequestDto(Long memberId, String title, String content) {
        this.memberId = memberId;
        this.title = title;
        this.content = content;
    }
}
