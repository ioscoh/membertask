package com.example.membertask.dto.taskdto;

import com.example.membertask.enetity.Task;
import lombok.Getter;

import java.util.List;

@Getter
public class TaskGetAllResponseDto {

    private Integer status;

    private String message;

    private List<TaskAllResponseDto> taskList;

    public TaskGetAllResponseDto(
            Integer status, String message,
            List<TaskAllResponseDto> taskList
    ) {
        this.status = status;
        this.message = message;
        this.taskList = taskList;
    }

    /**
     * 아래는 다건조회 서비스를 위한 내부 클래스 입니다,
     */
    @Getter
    public static class TaskAllResponseDto {
        private Long memberId;

        private Long taskId;

        private String memberName;

        private String title;

        private String content;

        public TaskAllResponseDto(
                Long memberId, Long taskId,
                String memberName, String title,
                String content
        ) {
            this.memberId = memberId;
            this.taskId = taskId;
            this.memberName = memberName;
            this.title = title;
            this.content = content;
        }
    }
}
