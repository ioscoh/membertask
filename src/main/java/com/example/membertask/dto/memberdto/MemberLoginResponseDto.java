package com.example.membertask.dto.memberdto;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class MemberLoginResponseDto {
    private Integer status;

    private String message;

    private Long memberId;

    private String memberName;

    private String token;
}
