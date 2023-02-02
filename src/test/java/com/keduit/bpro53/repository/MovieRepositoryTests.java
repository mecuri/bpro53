package com.keduit.bpro53.repository;

import java.util.UUID;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import com.keduit.bpro53.entity.Movie;
import com.keduit.bpro53.entity.MovieImage;

@SpringBootTest
public class MovieRepositoryTests {
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private MovieImageRepository movieImageRepository;
	
	@Test
	@Transactional
	@Commit
	public void insertMovies() {
		
		IntStream.rangeClosed(0, 100).forEach(i -> {
			
			Movie movie  = Movie.builder().title("Movie....." + i).build();
			
			System.out.println("------------------------");
			
			movieRepository.save(movie);
			
			int count = (int)(Math.random() * 5) + 1;
			
			for(int j = 0 ; j < count; j++) {
				MovieImage movieImage = MovieImage.builder()
						.uuid(UUID.randomUUID().toString())
						.movie(movie)
						.imgName("test img"+j+".jpg").build();
				
				movieImageRepository.save(movieImage);
			}
		});
	}

}
