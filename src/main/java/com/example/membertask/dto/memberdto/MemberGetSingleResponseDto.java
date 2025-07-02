package com.example.membertask.dto.memberdto;

import lombok.Getter;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Getter
public class MemberGetSingleResponseDto {
    private Integer status;

    private String message;

    private Long id;

    private String memberName;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


    public MemberGetSingleResponseDto(
            Integer status, String message, Long id, String memberName,
            LocalDateTime createdAt, LocalDateTime updatedAt
    ) {
        this.status = status;
        this.message = message;
        this.id = id;
        this.memberName = memberName;
        LocalDateTime localDateTime = createdAt;
        localDateTime.atOffset(ZoneOffset.UTC);
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}
