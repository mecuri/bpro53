package com.keduit.bpro53.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
	
	private Long reviewnum;
	
	private Long mno;
	
	private Long mid;
	
	private String nickname;
	
	private String email;
	
	private int grade;
	
	private String text;
	
	private LocalDateTime regDate, updateDate;
	
	
}
