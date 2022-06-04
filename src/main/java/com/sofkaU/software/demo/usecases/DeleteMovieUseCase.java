package com.sofkaU.software.demo.usecases;


import com.sofkaU.software.demo.collection.Movie;
import com.sofkaU.software.demo.dto.MovieDTO;
import com.sofkaU.software.demo.repository.MovieRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class DeleteMovieUseCase {

    private final MovieRepo movieRepo;

    private Mono<Movie> findMovieById(String id){
        return movieRepo.findById(id)
                .switchIfEmpty(Mono.error(() -> new Throwable("The id was not found")));
    }

    public Mono<Void> deleteMovie(String id){
        return findMovieById(id)
                .flatMap(mov -> movieRepo.deleteById(mov.getId()));
    }



}
