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
    //(@PathVariable 인자에는 @GetMapping("/{id}")에 있는 값으로 동일하게 적어 줘야한다.
    // 그렇다고 해서 엔티티 필드에 memberId 라고 안해도 되는데
    //그 이유는 값이 /member 라는 식으로 되기 때문에 클라이 언트 측에서도
    //개발자 입장에서도 혼선이 없기 대문이다.
    //그럼 왜 굳이 @PathVariable("id") 라고 명시해야할까?
    //그 이유는 언랜 생략이 가능한 부분이었으나
    //내부 코드가 더 디벨롭 됨에 따라 복잡해 져 명시 하지 않을 경우
    //해당 값을 인식을 못 할 수 있기 때문에 명시하는 걸 권장하는 것이다.
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
