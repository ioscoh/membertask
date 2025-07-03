package com.example.membertask.service;


import com.example.membertask.dto.memberdto.*;
import com.example.membertask.dto.memberdto.MemberGetAllResponseDto.GetAllResponseDto;
import com.example.membertask.enetity.Member;
import com.example.membertask.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class MemberService {

    //속
    private final MemberRepository memberRepository;

    //생


    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }



    //기
    /**
     *생성 서비스
     */
    @Transactional
    public MemberCreateResponseDto memberCreateService(
            MemberCreateRequestDto memberCreateRequestDto
    ) {
        String memberName = memberCreateRequestDto.getMemberName();
        String email = memberCreateRequestDto.getEmail();
        String password = memberCreateRequestDto.getPassword();

//        Member fromMemberCreateRequestDto
//                = Member.createFromMemberCreateRequestDto(memberName, email, password);

        Member newMember = new Member(memberName, email, password);

        Member save = memberRepository.save(newMember);
        Long saveId = save.getId();

        return new MemberCreateResponseDto(201, "created" ,saveId);
    }

    /**
     * 단건 조회 서비스
     */
    @Transactional
    public MemberGetSingleResponseDto memberGetSingleService(Long id) {

        //조회 & 검증
        Member member = memberRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("member not found"));

        Long foundId = member.getId();
        String foundMemberName = member.getMemberName();
        LocalDateTime foundCreatedAt = member.getCreatedAt();
        LocalDateTime foundUpdatedAt = member.getUpdatedAt();
        Integer status = 200;
        String message = "success";

        return new MemberGetSingleResponseDto(
                status, message, foundId, foundMemberName, foundCreatedAt, foundUpdatedAt
        );
    }
    /**
     * 다건 조회 서비스
     */
    //포문을 써도 되고 스트임을 써도 되지만 둘다 써보고 연습하는게 좋다.
    //--> 오늘은 포문만 해보았습니다.
    //Member member: all 는 member 의 속성들이 반복됨을 의미한다.
    // member. 으로 필요한 값들(api 에 맞춰)을 가져온다.
    // 이제 그 값들을 디티오로 감싸 변수에 담아주고,
    //컬랙션 변수에 다시 담아준다.
    @Transactional
    public MemberGetAllResponseDto memberGetAllService() {
        List<Member> all = memberRepository.findAllByIsDeletedFalse();

        //포문
//        List<GetAllResponseDto> allMember = new ArrayList<>();
//
//        for (Member member : all) {
//            Long id = member.getId();
//            String memberName = member.getMemberName();
//            LocalDateTime createdAt = member.getCreatedAt();
//            LocalDateTime updatedAt = member.getUpdatedAt();
//
//            GetAllResponseDto getAllResponseDto
//                    = new GetAllResponseDto(id, memberName, createdAt, updatedAt);
//        }

        //스트림
        List<GetAllResponseDto> allMember
                = all.stream()
                .map(member -> new GetAllResponseDto(
                                member.getId(), member.getMemberName(), member.getCreatedAt(), member.getUpdatedAt()
                        )
                ).collect(Collectors.toList());

        return new MemberGetAllResponseDto(
                200, "success", allMember
        );
    }
    /**
     * 회원 수정 서비스
     */
    @Transactional
    public MemberUpdateResponseDto memberUpdateAllService(
            Long id, MemberUpdateRequestDto memberUpdateRequestDto
    ) {
        String memberName = memberUpdateRequestDto.getMemberName();
        String email = memberUpdateRequestDto.getEmail();
        String password = memberUpdateRequestDto.getPassword();

        Member foundMember = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("member not found"));

        Member updateMember = foundMember.update(memberName, email, password);

        return new MemberUpdateResponseDto(200, "success", updateMember.getId());

    }
    /**
     * 회원 삭제 서비스
     */
    @Transactional
    public MemberDeleteResponseDto memberDeleteService(Long id) {
        Member foundMember = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("member not found"));

        foundMember.delete();

        return new MemberDeleteResponseDto(200, "delete");
    }
}
