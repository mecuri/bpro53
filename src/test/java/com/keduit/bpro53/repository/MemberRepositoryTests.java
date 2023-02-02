package com.keduit.bpro53.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import com.keduit.bpro53.entity.Member;

@SpringBootTest
public class MemberRepositoryTests {
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Test
	public void insertMembers() {
		
		IntStream.rangeClosed(0, 100).forEach(i -> {
			Member member = Member.builder()
								  .email("r" + i + "@abc.com")
								  .pw("1111")
								  .nickname("reviewer" + i).build();
			
			memberRepository.save(member);
		});
	}
	
	@Test
	@Transactional
	@Commit
	public void testDeleteMemner() {
		
		Long mid = 2L;	// 멤버.
		
		Member member = Member.builder().mid(mid).build();
		
		reviewRepository.deleteByMember(member);
		memberRepository.deleteById(mid);
	}
	
	

}
