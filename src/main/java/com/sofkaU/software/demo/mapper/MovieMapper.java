package com.sofkaU.software.demo.mapper;

import com.sofkaU.software.demo.collection.Movie;
import com.sofkaU.software.demo.dto.MovieDTO;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.config.EnableWebFlux;

@Component
@EnableWebFlux
public class MovieMapper {

    private final ModelMapper mapper;

    public MovieMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public MovieDTO toMovieDto (Movie movie){
        return mapper.map(movie, MovieDTO.class);
    }

    public Movie toMovieEntity (MovieDTO movieDTO){
        return mapper.map(movieDTO, Movie.class);
    }



}
