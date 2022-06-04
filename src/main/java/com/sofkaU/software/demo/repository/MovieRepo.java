package com.sofkaU.software.demo.repository;


import com.sofkaU.software.demo.collection.Movie;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MovieRepo extends ReactiveMongoRepository<Movie, String> {
}
