package springframework.guru.webclientdemo.service;

import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import reactor.core.publisher.Mono;
import springframework.guru.webclientdemo.domain.Movie;

public interface MovieClientService {
    public Mono<Movie> searchMovieByTitle(String apiKey, String title);
    public Mono<Movie> searchMovieById(String apiKey, String imdbId);



}