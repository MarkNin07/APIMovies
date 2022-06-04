package com.sofkaU.software.demo.usecases;


import com.sofkaU.software.demo.dto.MovieDTO;
import com.sofkaU.software.demo.mapper.MovieMapper;
import com.sofkaU.software.demo.repository.MovieRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CreateMovieUseCase {

    private final MovieRepo movieRepo;

    private final MovieMapper mapper;

    //This method is in charge of validating there are no empty fields
    private boolean validateAttributes(MovieDTO movieDTO){
        return movieDTO.getMovieName() != null && movieDTO.getSynopsis() != null;
    }


    //This method filters that the method above is true and return an error in case it doesn't
    private Mono<MovieDTO> validateMovieDto(MovieDTO movieDTO){
        return Mono.just(movieDTO)
                .filter(this::validateAttributes)
                .switchIfEmpty(Mono.error(() -> new Throwable("Some fields are empty")));
    }

    private Mono<MovieDTO> postMovieDto(MovieDTO movieDTO){
        return validateMovieDto(movieDTO)
                .flatMap(moviedto -> movieRepo.save(mapper.toMovieEntity(moviedto)))
                .map(mapper::toMovieDto);
    }





}
