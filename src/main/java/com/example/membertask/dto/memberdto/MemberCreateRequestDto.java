package com.example.membertask.dto.memberdto;

import lombok.Getter;

@Getter
public class MemberCreateRequestDto {

    private String memberName;

    private String email;

    private String password;

    public MemberCreateRequestDto(
            String memberName, String email, String password
    ) {
        this.memberName = memberName;
        this.email = email;
        this.password = password;
    }

}
