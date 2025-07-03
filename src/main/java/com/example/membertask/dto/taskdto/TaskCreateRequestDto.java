package com.example.membertask.dto.taskdto;

import lombok.Getter;

@Getter
public class TaskCreateRequestDto {

    private Long memberId;

    private String title;

    private String content;

}
