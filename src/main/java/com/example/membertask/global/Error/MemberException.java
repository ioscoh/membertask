package com.example.membertask.global.Error;

public class MemberException extends RuntimeException {
    private final MemberError memberError;

    public MemberException(MemberError memberError) {
        super(memberError.getMessage());
        this.memberError = memberError;
    }
}
