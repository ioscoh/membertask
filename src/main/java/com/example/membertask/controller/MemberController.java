package com.example.membertask.controller;

import com.example.membertask.dto.memberdto.*;
import com.example.membertask.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/members")
public class MemberController {
    //속
    private final MemberService memberService;

    //생


    //기

    /**
     *생성 api
     */
    @PostMapping
    public MemberCreateResponseDto createMemberApi(
            @RequestBody MemberCreateRequestDto memberCreateRequestDto
    ) {
        return memberService.memberCreateService(memberCreateRequestDto);
    }

    /**
     * 단건 조회 api
     */
    @GetMapping("/{id}")
    public MemberGetSingleResponseDto getMemberSingleApi(
            @PathVariable("id") Long id
            ) {
        return  memberService.memberGetSingleService(id);
    }
    /**
     * 다건 조회 api
     */
    @GetMapping
    public MemberGetAllResponseDto getMemberAllApi(
            ) {
        return memberService.memberGetAllService();
    }
    /**
     * 회원 수정 API
     */
    @PutMapping("/{id}")
    public MemberUpdateResponseDto updateMemberAllApi(
            //TODO: 2번 회원이 1번 회원 정보를 수정시 정상 작동 하므로 본인의 값만 수정할 수 있도록 코드개선 필요.(인증인가 구현 이후)
            @PathVariable("id") Long id,
            @RequestBody MemberUpdateRequestDto memberUpdateRequestDto
    ) {
        return memberService.memberUpdateAllService(id, memberUpdateRequestDto);
    }
    /**
     * 회원 삭제 API
     */
    @DeleteMapping("/{id}")
    public MemberDeleteResponseDto deleteMemberApi(
            //TODO: 2번 회원이 1번 회원 정보를 삭제시 정상 작동 하므로 본인의 값만 삭제할 수 있도록 코드개선 필요.(인증인가 구현 이후)
            @PathVariable("id") Long id
    ) {
        return memberService.memberDeleteService(id);
    }
}
