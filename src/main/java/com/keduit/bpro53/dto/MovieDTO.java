package com.keduit.bpro53.dto;

import java.util.List;
import java.time.LocalDateTime;
import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {
	
	private Long mno;
	
	private String title;
	
	@Builder.Default
	private List<MovieImageDTO> imageDTOList = new ArrayList<>();
	
	// 평점
	private double avg;
	
	// 리뷰수
	private int reviewCnt;
	
	private LocalDateTime regDate;
	
	private LocalDateTime updateDate;
}
