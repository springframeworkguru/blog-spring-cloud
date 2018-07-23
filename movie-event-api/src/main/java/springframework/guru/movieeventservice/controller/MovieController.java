package springframework.guru.movieeventservice.controller;

import springframework.guru.movieeventservice.domain.MovieEvent;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springframework.guru.movieeventservice.service.MovieService;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping(path = "/api/v1/movies")
public class MovieController {
	private  MovieService movieService;
	@Autowired
	public MovieController(final MovieService movieService) {
		this.movieService = movieService;
	}
    @GetMapping(value = "events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<MovieEvent> streamMovieEvents(){
        return movieService.events();
    }
}
