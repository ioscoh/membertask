package com.example.membertask.dto.memberdto;

import lombok.Getter;

@Getter
public class MemberUpdateResponseDto {
    private Integer status;

    private String message;

    private Long id;

    public MemberUpdateResponseDto(Integer status, String message, Long id) {
        this.status = status;
        this.message = message;
        this.id = id;
    }
}
