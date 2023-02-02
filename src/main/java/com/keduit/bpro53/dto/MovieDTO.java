package com.keduit.bpro53.dto;

import java.util.List;
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
}
