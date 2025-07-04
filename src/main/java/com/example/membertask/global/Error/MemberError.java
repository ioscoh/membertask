package com.example.membertask.global.Error;

import lombok.Getter;

@Getter
public enum MemberError {
    ALREADY_EXIST_EMAIL("이미 존재하는 이메일 입니다."),
    IT_THAT_THE_RIGHT_FORMAT("올바른 형식이 아닙니다.");

    private final String message;

    MemberError(String message) {
        this.message = message;
    }
}
