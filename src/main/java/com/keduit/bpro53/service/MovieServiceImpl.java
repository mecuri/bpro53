package com.keduit.bpro53.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.keduit.bpro53.dto.MovieDTO;
import com.keduit.bpro53.entity.Movie;
import com.keduit.bpro53.entity.MovieImage;
import com.keduit.bpro53.repository.MovieImageRepository;
import com.keduit.bpro53.repository.MovieRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
	
	private final MovieRepository movieRepository;
	private final MovieImageRepository movieImageRepository;
	
	// 등록하기
	@Override
	public Long register(MovieDTO movieDTO) {

		// 파일 첨부 후
		Map<String, Object> entityMap = dtoToEntity(movieDTO); // dto를 entity로 변환
		Movie movie = (Movie) entityMap.get("movie");
		List<MovieImage> movieImageList = (List<MovieImage>)entityMap.get("imgList"); // 형변환 해줌
		
		movieRepository.save(movie);
		
		movieImageList.forEach(movieImage -> {
			movieImageRepository.save(movieImage);
		});
		return movie.getMno(); // 등록 번호 반환해야 함
	}

}
