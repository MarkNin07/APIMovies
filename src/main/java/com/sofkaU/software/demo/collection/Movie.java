package com.sofkaU.software.demo.collection;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "movie")
@Data
public class Movie {

    @Id
    private String id;

    private String movieName;

    private String synopsis;

}
