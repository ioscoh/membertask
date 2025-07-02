package com.example.membertask.dto.memberdto;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class MemberGetAllResponseDto {

    //속
    private Integer status;

    private String message;

    private List<GetAllResponseDto> allMember;

    //생
    public MemberGetAllResponseDto(
            Integer status, String message, List<GetAllResponseDto> allMember
    ) {
        this.status = status;
        this.message = message;
        this.allMember = allMember;

    }
    /**
     * 아래는 private List<> allMember 를 위한 내부 클래스 입니다.
     */
    @Getter
    public static class GetAllResponseDto {
        //속
        private Long id;

        private String memberName;

        private LocalDateTime createdAt;

        private LocalDateTime updatedAt;

        public GetAllResponseDto(
                Long id, String memberName, LocalDateTime createdAt, LocalDateTime updatedAt
        ) {
            this.id = id;
            this.memberName = memberName;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
        }
    }

}
