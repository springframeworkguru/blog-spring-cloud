package springframework.guru.webclientdemo.controller;

import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import reactor.core.publisher.Flux;
import springframework.guru.webclientdemo.domain.Movie;
import springframework.guru.webclientdemo.domain.MovieEvent;
import springframework.guru.webclientdemo.service.MovieClientEventService;
import springframework.guru.webclientdemo.service.MovieClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
public class MovieController {
    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

    private MovieClientService movieClientService;
    private MovieClientEventService movieClientEventService;
    private Environment env;

    @Autowired
    public MovieController(MovieClientService movieClientService, MovieClientEventService movieClientEventService, Environment env){
        this.movieClientService=movieClientService;
        this.movieClientEventService=movieClientEventService;
        this.env=env;
    }


    @GetMapping("/movies/title/{name}")
    public Mono<Movie> getMovieByTitle(@PathVariable String name) {
        String apiKey = env.getProperty("app.api.key");
        return movieClientService.searchMovieByTitle(apiKey, name);
    }

    @GetMapping("/movies/id/{imdbId}")
    public Mono<Movie> getMovieById(@PathVariable String imdbId) {
        return movieClientService.searchMovieById(env.getProperty("app.api.key"), imdbId);
    }

    @GetMapping(value = "/movies/events",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<MovieEvent> getEvents() {
        return movieClientEventService.getMovieEvents();
    }

    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<String> handleWebClientResponseException(WebClientResponseException ex) {
        logger.error("Error from WebClient - Status {}, Body {}", ex.getRawStatusCode(),
                ex.getResponseBodyAsString(), ex);
        return ResponseEntity.status(ex.getRawStatusCode()).body(ex.getResponseBodyAsString());
    }
}
