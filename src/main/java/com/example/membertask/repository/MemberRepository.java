package com.example.membertask.repository;

import com.example.membertask.enetity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findAllByIsDeletedFalse();
    Optional<Member> findByIdAndIsDeletedFalse(Long id);

    //회원가입시 사용하는 매소드 입니다.
    Optional<Member> findMemberByEmail(String email);

}
