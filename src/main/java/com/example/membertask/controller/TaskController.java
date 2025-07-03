package com.example.membertask.controller;

import com.example.membertask.dto.taskdto.*;
import com.example.membertask.enetity.Task;
import com.example.membertask.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    //속
    private final TaskService taskService;


    //셍

    //기
    /**
     *생성 api
     */
    @PostMapping
    public TaskCreateResponseDto createTaskApi(
            @RequestBody TaskCreateRequestDto taskCreateRequestDto
            ) {
        return taskService.taskCreateService(taskCreateRequestDto);
    }
    /**
     * 단건 조회 api
     */
    @GetMapping("/{taskId}")
    public TaskGetSingleResponseDto getTaskSingleApi(
            @PathVariable("taskId") Long taskId
    ) {
        return taskService.taskGetSingleService(taskId);
    }
    /**
     * 다건 조회 api
     */
    @GetMapping
    public TaskGetAllResponseDto getTaskAllApi() {
        return taskService.taskGetAllService();
    }
    /**
     * 수정 api
     */
    @PutMapping("/{taskId}")
    public TaskUpdateResponseDto updateAllApi(
            @PathVariable("taskId") Long taskId,
            @RequestBody TaskUpdateRequestDto taskUpdateRequestDto
            ) {
        return taskService.taskUpdateAllService(taskUpdateRequestDto, taskId);
    }
    /**
     * 삭제 api
     */
    @DeleteMapping("/{taskId}")
    public TaskDeleteResponseDto deleteSingleApi(
            @PathVariable("taskId") Long taskId
    ) {
        return taskService.taskDeleteSingleService(taskId);
    }


}
