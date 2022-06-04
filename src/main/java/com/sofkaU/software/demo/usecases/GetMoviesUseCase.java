package com.sofkaU.software.demo.usecases;

import com.sofkaU.software.demo.dto.MovieDTO;
import com.sofkaU.software.demo.mapper.MovieMapper;
import com.sofkaU.software.demo.repository.MovieRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class GetMoviesUseCase {

    private final MovieRepo movieRepo;

    private final MovieMapper mapper;

    public Flux<MovieDTO> getAllMovies(){
        return movieRepo.findAll().map(mapper::toMovieDto);
    }

}
