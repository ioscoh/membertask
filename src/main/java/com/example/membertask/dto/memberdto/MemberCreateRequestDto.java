package com.example.membertask.dto.memberdto;

import lombok.Getter;

@Getter
public class MemberCreateRequestDto {

    private String memberName;

    private String email;

    private String password;

}
