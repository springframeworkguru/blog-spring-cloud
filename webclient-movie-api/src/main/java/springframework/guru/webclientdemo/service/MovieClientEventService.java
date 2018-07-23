package springframework.guru.webclientdemo.service;

import reactor.core.publisher.Flux;
import springframework.guru.webclientdemo.domain.MovieEvent;

public interface MovieClientEventService {
    public Flux<MovieEvent> getMovieEvents();
}
