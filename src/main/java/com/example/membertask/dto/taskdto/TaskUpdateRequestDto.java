package com.example.membertask.dto.taskdto;

import com.example.membertask.enetity.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
public class TaskUpdateRequestDto {

    private Long memberId;

    private String title;

    private String content;

    public TaskUpdateRequestDto(
            Long memberId,String title, String content
    ) {
        this.memberId = memberId;
        this.title = title;
        this.content = content;
    }
}
