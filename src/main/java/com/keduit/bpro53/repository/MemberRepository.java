package com.keduit.bpro53.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.keduit.bpro53.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
