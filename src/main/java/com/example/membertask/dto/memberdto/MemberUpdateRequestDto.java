package com.example.membertask.dto.memberdto;

import lombok.Getter;

@Getter
public class MemberUpdateRequestDto {

    private String memberName;

    private String email;

    private String password;

    public MemberUpdateRequestDto(
            String memberName, String email, String password
    ) {
        this.memberName = memberName;
        this.email = email;
        this.password = password;
    }



}
