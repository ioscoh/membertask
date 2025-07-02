package com.example.membertask.dto.memberdto;

import lombok.Getter;

@Getter
public class MemberCreateResponseDto {

    private Integer status;

    private String message;

    private  Long id;

    public MemberCreateResponseDto(Integer status, String message, Long id) {
        this.status = status;
        this.message = message;
        this.id = id;
    }
}
