package com.keduit.bpro53.dto;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageResultDTO<DTO, EN> {

	private List<DTO> dtoList;
	
	private int totalPage;
	private int page;
	private int size;
	
	private int start, end;
	private boolean prev, next;
	
	private List<Integer> pageList;
	
	// 생성자?
	public PageResultDTO(Page<EN> result, Function<EN, DTO> fn) {
		
		dtoList = result.stream().map(fn).collect(Collectors.toList());
		
		totalPage = result.getTotalPages();
		makePageList(result.getPageable());
	}

	private void makePageList(Pageable pageable) {
		this.page = pageable.getPageNumber() + 1;
		this.size = pageable.getPageSize();
		int tempEnd = (int) (Math.ceil(page/10.0)) *10;
		
		start = tempEnd -9;
		
		prev = start > 1;
		next = totalPage > tempEnd;
		end = totalPage > tempEnd ? tempEnd + 1 : totalPage + 1;
		
		pageList = IntStream.rangeClosed(start, end-1).boxed().collect(Collectors.toList());
		
	}
	
}
