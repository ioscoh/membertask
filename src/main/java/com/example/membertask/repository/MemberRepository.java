package com.example.membertask.repository;

import com.example.membertask.enetity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findAllByIsDeletedFalse();

    Optional<Member> findByIdAndIsDeletedFalse(Long id);

    //회원가입시 사용하는 매소드 입니다.
    boolean existsByEmail(String email);

    //로그인 서비스에서 member 를 삭제 되지 않은 이메일로 조회 할때 사용합니다.
    Optional<Member> findByEmailAndIsDeletedFalse(String email);




}
