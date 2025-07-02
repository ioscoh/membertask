package com.example.membertask.repository;

import com.example.membertask.enetity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {



}
