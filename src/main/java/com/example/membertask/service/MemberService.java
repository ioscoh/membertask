package com.example.membertask.service;


import com.example.membertask.global.Error.MemberError;
import com.example.membertask.global.Error.MemberException;
import com.example.membertask.dto.memberdto.*;
import com.example.membertask.dto.memberdto.MemberGetAllResponseDto.GetAllResponseDto;
import com.example.membertask.enetity.Member;
import com.example.membertask.global.encoder.PasswordEncoder;
import com.example.membertask.global.jwt.JwtService;
import com.example.membertask.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
public class MemberService {

    //속
    private final MemberRepository memberRepository;

    private static final Pattern EMAIL =
            Pattern.compile(
                    "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
            );

    private static final Pattern PASSWORD = Pattern.compile(
            "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*" +
                    "[!@#$%^&*()_+\\-\\=\\[\\]{};:'\"\\\\|,.<>\\/?]).{8,}$"
    );

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;




    //생


    //기
    /**
     *생성 서비스
     */
    /**
     * 1. 요청값 검사
     * 1-0. 이메일 형식 검사.
     * 1-1.이메일 중복 검사
     * 1-2. 비밀번호 형식 검사
     *
     * 2. 비지니스 로직
     * 2-1. 패스워드 암호화
     */
    @Transactional
    public MemberCreateResponseDto memberCreateService(
            MemberCreateRequestDto memberCreateRequestDto
    ) {
        String foundMemberName = memberCreateRequestDto.getMemberName();
        String foundEmail = memberCreateRequestDto.getEmail();
        String foundPassword = memberCreateRequestDto.getPassword();

        //1-0 이메일 혛식 검사(이메일 검증)

        //TODO: 아래로직 이해 필요.
        // if (!EMAIL.matcher(foundEmail).matches()) {
        // throw new MemberException(MemberError.IT_THAT_THE_RIGHT_FORMAT);
        // }
        Matcher matcher = EMAIL.matcher(foundEmail);
        boolean pass = matcher.matches();

        if (!pass) {
            throw new MemberException(MemberError.IT_THAT_THE_RIGHT_FORMAT);
        }


        // 1-1.이베일 중복 검사(이메일 중복 확인)
//        if (memberRepository.existsByEmail(foundEmail)) {
//            throw new MemberException(MemberError.ALREADY_EXIST_EMAIL);
//        }
        boolean existsEmail = memberRepository.existsByEmail(foundEmail);

        if (existsEmail) {
            throw new MemberException(MemberError.ALREADY_EXIST_EMAIL);
        }



        //1-2. 비밀번호 형식 검사(비밀번호 검증)
        if (!PASSWORD.matcher(foundPassword).matches()) {
            throw new MemberException(MemberError.IT_THAT_THE_RIGHT_FORMAT);
        }

        //2-1. 패스워드 암호화(비밀번호 암호화)
        String encryptedPassword = passwordEncoder.encode(foundPassword);

        //TODO: MemberCreateRequestDto 를 MemberEntity 로 변환하는 코드 표현 개선 필요.
//                Member fromMemberCreateRequestDto
//                = Member.createFromMemberCreateRequestDto(memberName, email, password);
        Member newMember = new Member(foundMemberName, foundEmail, encryptedPassword);

        Member save = memberRepository.save(newMember);
        Long saveId = save.getId();

        return new MemberCreateResponseDto(201, "created" ,saveId);
    }
    /**
     * 로그인 서비스
     */
    @Transactional
    public MemberLoginResponseDto memberLoginService(MemberLoginRequestDto memberLoginRequestDto) {
        // 서명 만들기

        //- 1-1. 디티오로 들어오는 데이터 받기.
        String foundDtoEmail = memberLoginRequestDto.getEmail();
        String foundDtoPassword = memberLoginRequestDto.getPassword();

        //-1-2 member 를 삭제 되지 않은 이메일로 조회하기
        Member member = memberRepository.findByEmailAndIsDeletedFalse(foundDtoEmail)
                .orElseThrow(() -> new MemberException(MemberError.NOT_FOUND_EMAIL));
        Long foundDbMemberId = member.getId();
        String foundDbMemberName = member.getMemberName();
        String foundDbMemberEmail = member.getEmail();
        String foundDbPassword = member.getPassword();

        //-1-3. 데이터 베이스의 들어있던 멤버의 비밀번호와
        //디티오에서 찾아온 이메일에 해당하는 비밀번호의 정보가 일치하는지 검사하기
        if (!passwordEncoder.matches(foundDtoPassword, foundDbPassword)) {
            throw new RuntimeException("fail");
        }
        //- 1-4. 토큰 발급하기.
        String createJwt = jwtService.createJwt(foundDbMemberEmail);
  
        return new MemberLoginResponseDto(
                200, "success", foundDbMemberId, foundDbMemberName, createJwt
        );
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
    //Member member: all 는 member 의 속성들이 반복됨을 의미한다.
    // member. 으로 필요한 값들(api 에 맞춰)을 가져온다.
    // 이제 그 값들을 디티오로 감싸 변수에 담아주고,
    //컬랙션 변수에 다시 담아준다.
    @Transactional
    public MemberGetAllResponseDto memberGetAllService() {
        List<Member> all = memberRepository.findAllByIsDeletedFalse();

        List<GetAllResponseDto> allMember
                = all.stream()
                .map(member -> new GetAllResponseDto(
                                member.getId(), member.getMemberName(), member.getCreatedAt(), member.getUpdatedAt()
                        )
                ).toList();

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

        Member foundMember = memberRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("member not found"));

        Member updateMember = foundMember.update(memberName, email, password);

        return new MemberUpdateResponseDto(200, "success", updateMember.getId());

    }
    /**
     * 회원 삭제 서비스
     */
    @Transactional
    public MemberDeleteResponseDto memberDeleteService(Long id) {
        Member foundMember = memberRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("member not found"));

        foundMember.delete();

        return new MemberDeleteResponseDto(200, "delete");
    }
}
