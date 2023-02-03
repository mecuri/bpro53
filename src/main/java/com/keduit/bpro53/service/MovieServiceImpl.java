package com.keduit.bpro53.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.keduit.bpro53.dto.MovieDTO;
import com.keduit.bpro53.dto.PageRequestDTO;
import com.keduit.bpro53.dto.PageResultDTO;
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
		
		log.info(movieImageList); // null 오류남
		
		movieRepository.save(movie);
		
		movieImageList.forEach(movieImage -> {
			movieImageRepository.save(movieImage);
		});
		return movie.getMno(); // 등록 번호 반환해야 함
	}

	@Override
	public PageResultDTO<MovieDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {

		Pageable pageable = pageRequestDTO.getPageable(Sort.by("mno").descending());
		
		Page<Object[]> result = movieRepository.getListPage(pageable);
		
		log.info("--------------------------------");
		
		result.getContent().forEach(arr -> {
			log.info(Arrays.toString(arr));
		});
		
		// Object를 주면 MovieDTO로 
		Function<Object[], MovieDTO> fn = (arr -> entitiesToDTO(
					(Movie)arr[0],
					(List<MovieImage>)(Arrays.asList((MovieImage)arr[1])),
					(Double)arr[2],
					(Long)arr[3])
		);
		
		return new PageResultDTO<>(result, fn);
	}
	
	// 한 건 읽기
	@Override
	public MovieDTO getMovie(Long mno) {

		List<Object[]> result = movieRepository.getMovieWithAll(mno);
		
		Movie movie = (Movie)result.get(0)[0];
		
		List<MovieImage> movieImageList = new ArrayList<>(); // 이미지 처리
		
		result.forEach(arr -> {
			MovieImage movieImage = (MovieImage)arr[1];
			movieImageList.add(movieImage);
		});
		
		Double avg = (Double)result.get(0)[2];
		Long reviewCnt = (Long)result.get(0)[3];
		
		return entitiesToDTO(movie, movieImageList, avg, reviewCnt);
	}

}
