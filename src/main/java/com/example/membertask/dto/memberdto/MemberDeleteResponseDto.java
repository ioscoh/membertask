package com.example.membertask.dto.memberdto;

import lombok.Getter;

@Getter
public class MemberDeleteResponseDto {
    private Integer status;

    private String message;

    public MemberDeleteResponseDto(Integer status, String message) {
        this.status = status;
        this.message = message;
    }
}
