package com.sofkaU.software.demo.controller;

import com.sofkaU.software.demo.dto.MovieDTO;
import com.sofkaU.software.demo.usecases.CreateMovieUseCase;
import com.sofkaU.software.demo.usecases.DeleteMovieUseCase;
import com.sofkaU.software.demo.usecases.GetMoviesUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class MovieController {

    @Bean
    public RouterFunction<ServerResponse> getAllMovies(GetMoviesUseCase moviesUseCase){
        return route(GET("/getmovies"), request -> ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromProducer(moviesUseCase.getAllMovies(), MovieDTO.class)));
    }

    @Bean
    public RouterFunction<ServerResponse> createMovie(CreateMovieUseCase createMovieUseCase){
        return route(POST("/create/movie").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(MovieDTO.class)
                        //save the movie
                        .flatMap(createMovieUseCase::postMovie)
                        //After saving the movie it'll return a created status
                        .flatMap(movieDTO -> ServerResponse.status(HttpStatus.CREATED)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(movieDTO))
                        .onErrorResume(e -> ServerResponse.status(HttpStatus.BAD_REQUEST).build()));
    }

    @Bean
    public RouterFunction<ServerResponse> deleteMovie(DeleteMovieUseCase deleteMovieUseCase){
        return route(DELETE("/delete/movie/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> deleteMovieUseCase.deleteMovie(request.pathVariable("id"))
                        .flatMap((del) -> ServerResponse.status(HttpStatus.ACCEPTED).build())
                        .onErrorResume(e -> ServerResponse.status(HttpStatus.NOT_FOUND).build()));
    }

}

