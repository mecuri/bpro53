package com.keduit.bpro53.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.keduit.bpro53.dto.ReviewDTO;
import com.keduit.bpro53.service.ReviewService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/reviews")
@Log4j2
@RequiredArgsConstructor // final만 생성자로 가짐
public class ReviewController {
	
	private final ReviewService reviewService;
	
	@GetMapping("/{mno}/all")
	public ResponseEntity<List<ReviewDTO>> getList(@PathVariable("mno") Long mno) {
		
		log.info("----------------list-------------------");
		log.info("MNO : " + mno);
		
		List<ReviewDTO> reviewDTOList = reviewService.getListOfMovie(mno);
		
		return new ResponseEntity<>(reviewDTOList, HttpStatus.OK);
	}
	
	@PostMapping("/{mno}")
	public ResponseEntity<Long> addReview(@RequestBody ReviewDTO reviewDTO) {
		
		log.info("----------------add---------------------");
		log.info("reviewDTO : " + reviewDTO);
		
		Long reviewnum =  reviewService.register(reviewDTO);
		
		return new ResponseEntity<Long>(reviewnum, HttpStatus.OK);
	}
	
	@PutMapping("/{mno}/{reviewnum}")
	public ResponseEntity<Long> modifyReview(@PathVariable Long reviewnum, @RequestBody ReviewDTO reviewDTO) {
		
		log.info("-----------------modify---------------");
		log.info("reviewDTO : " + reviewDTO);
		
		reviewService.modify(reviewDTO);
		
		return new ResponseEntity<Long>(reviewnum, HttpStatus.OK);
	} 
	
	@DeleteMapping("/{mno}/{reviewnum}")
	public ResponseEntity<Long> removeReview(@PathVariable Long reviewnum) {
		
		log.info("--------------remove-------------------");
		log.info("reviewnum : " + reviewnum);
		
		reviewService.remove(reviewnum);
		
		return new ResponseEntity<Long>(reviewnum, HttpStatus.OK);
	}
	
}
