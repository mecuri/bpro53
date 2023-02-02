package com.keduit.bpro53.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.keduit.bpro53.dto.MovieDTO;
import com.keduit.bpro53.dto.MovieImageDTO;
import com.keduit.bpro53.entity.Movie;
import com.keduit.bpro53.entity.MovieImage;

public interface MovieService {
	
	Long register(MovieDTO movieDTO);
	
	// dto -> entity
	default Map<String, Object> dtoToEntity(MovieDTO movieDTO) { // MovieDTO로 받아서 Map으로 반환
		Map<String, Object> entityMap = new HashMap<>();
		
		Movie movie = Movie.builder().mno(movieDTO.getMno())
									 .title(movieDTO.getTitle())
									 .build();
		
		entityMap.put("movie", movie);
		
		List<MovieImageDTO> imageDTOList = movieDTO.getImageDTOList();
		
		if(imageDTOList != null && imageDTOList.size() > 0) { // 첨부 파일이 있을 경우
			List<MovieImage> movieImageList = imageDTOList.stream()
												.map(movieImageDTO -> { // map으로 imageDTOList로 하나씩 꺼내온 다음 List를 생성
													MovieImage movieImage = MovieImage.builder()
																					  .path(movieImageDTO.getPath())
																					  .imgName(movieImageDTO.getImgName())
																					  .uuid(movieImageDTO.getUuid())
																					  .movie(movie)
																					  .build();
													return movieImage;
												
												}).collect(Collectors.toList());
			entityMap.put("imgList", movieImageList);
		}
		return entityMap; // entityMap = movie, imgList를 가지고 있음
	}

}
