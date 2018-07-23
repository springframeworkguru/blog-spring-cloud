package springframework.guru.movieeventservice.service;

import springframework.guru.movieeventservice.domain.MovieEvent;
import reactor.core.publisher.Flux;


public interface MovieService {
	Flux<MovieEvent> events();
}
