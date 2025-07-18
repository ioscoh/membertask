package com.example.membertask.service;

import com.example.membertask.dto.taskdto.*;
import com.example.membertask.enetity.Member;
import com.example.membertask.enetity.Task;
import com.example.membertask.repository.MemberRepository;
import com.example.membertask.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {
    //속
    private final TaskRepository taskRepository;

    private final MemberRepository memberRepository;

    //생
    public TaskService(TaskRepository taskRepository, MemberRepository memberRepository) {
        this.taskRepository = taskRepository;
        this.memberRepository = memberRepository;
    }


    //기

    /**
     * 생성 서비스
     */
    @Transactional
    public TaskCreateResponseDto taskCreateService(TaskCreateRequestDto taskCreateRequestDto) {

        //드라이버1번을 쓰려면 1번 드라이버를 가져온다.
        //멤버1번을 쓰려면 1번 멤버를 가져온다.
        Long memberId = taskCreateRequestDto.getMemberId();
        String title = taskCreateRequestDto.getTitle();
        String content = taskCreateRequestDto.getContent();

        //멤버를 아이디로 조회하고 검증로직을 실행
        Member foundMember = memberRepository.findByIdAndIsDeletedFalse(memberId)
                .orElseThrow(() -> new RuntimeException("not found member"));

        //엔티티 만들기
        Task newTask = new Task(foundMember, title, content);

        //저장
        Task saveTask = taskRepository.save(newTask);
        Long saveTaskMemberIdId = saveTask.getMemberId();
        Long saveTaskId = saveTask.getTaskId();

        return new TaskCreateResponseDto(200, "success", saveTaskMemberIdId, saveTaskId);
    }

    /**
     * 단건 조회 서비스
     */
    @Transactional
    public TaskGetSingleResponseDto taskGetSingleService(Long taskId) {
        Task foundTask = taskRepository.findByIdAndIsDeletedFalse(taskId)
                .orElseThrow(() -> new RuntimeException("not found task"));
        Long foundTaskId = foundTask.getTaskId();
        Member foundTaskMember = foundTask.getMember();
        Long foundedMemberId = foundTaskMember.getId();
        String foundMemberName = foundTaskMember.getMemberName();//클린코드 관점 중 하나
        String taskTitle = foundTask.getTaskTitle();
        String taskContent = foundTask.getTaskContent();

        return new TaskGetSingleResponseDto(
                200, "success", foundedMemberId,
                foundTaskId, foundMemberName, taskTitle, taskContent
        );
    }
    /**
     * 다건 조회 서비스
     */
    @Transactional
    public TaskGetAllResponseDto taskGetAllService() {
        //조회
        List<Task> foundTasks = taskRepository.findAllByIsDeletedFalse();

         List<TaskGetAllResponseDto.TaskAllResponseDto> allTask
                 = foundTasks.stream()
                .map(task -> new TaskGetAllResponseDto.TaskAllResponseDto(
                        task.getMemberId(), task.getTaskId(),
                        task.getMember().getMemberName(), task.getTaskTitle(),
                        task.getTaskContent()
                )).toList();

         return new TaskGetAllResponseDto(200, "success", allTask);
    }
    /**
     * 수정 서비스
     */
    @Transactional
    public TaskUpdateResponseDto taskUpdateAllService(
            TaskUpdateRequestDto taskUpdateRequestDto, Long updateTaskId
    ) {
        //데이터 준비
        String taskContent = taskUpdateRequestDto.getContent();
        String taskTitle = taskUpdateRequestDto.getTitle();
        Long memberId = taskUpdateRequestDto.getMemberId();

        //memberId 와 taskId 비교
        //테스크 조회 & 검증
        Task foundTask = taskRepository.findByIdAndIsDeletedFalse(updateTaskId)
                .orElseThrow(() -> new RuntimeException("found not task"));
        Long foundTaskId = foundTask.getTaskId();
        Member foundTaskMember = foundTask.getMember();
        Long foundMemberId = foundTaskMember.getId();


        //멤버 조회 & 검증
        if (Objects.equals(foundMemberId, memberId)) {
            //업데이트
            Task updateTask = foundTask.update(taskTitle, taskContent);
            String updatedTitle = updateTask.getTaskTitle();
            String updatedContent = updateTask.getTaskContent();

            return new TaskUpdateResponseDto(
                    200, "success",
                    foundMemberId, foundTaskId, updatedTitle, updatedContent);
        } else {
            throw new RuntimeException("bad request");
        }
    }
    /**
     * 삭제 서비스
     */
    @Transactional
    public TaskDeleteResponseDto taskDeleteSingleService(Long deleteTaskId) {
        Task foundTask = taskRepository.findByIdAndIsDeletedFalse(deleteTaskId)
                .orElseThrow(() -> new RuntimeException("found not task"));

        foundTask.delete();

        return new TaskDeleteResponseDto(200, "delete");
    }
}
