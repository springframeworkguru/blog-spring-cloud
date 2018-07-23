package springframework.guru.webclientdemo.service;

import reactor.core.publisher.Flux;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import springframework.guru.webclientdemo.domain.MovieEvent;

@Service
public class MovieClientEventServiceImpl implements MovieClientEventService {
    private static final String API_MIME_TYPE = "application/json";
    private static final String API_BASE_URL = "http://localhost:8082";
    private static final String USER_AGENT = "Spring 5 WebClient";
    private static final Logger logger = LoggerFactory.getLogger(MovieClientServiceImpl.class);

    private final WebClient webClient;

    public MovieClientEventServiceImpl() {
        this.webClient = WebClient.builder()
                .baseUrl(API_BASE_URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, API_MIME_TYPE)
                .defaultHeader(HttpHeaders.USER_AGENT, USER_AGENT)
                .build();
    }
    @Override
    public Flux<MovieEvent> getMovieEvents() {
        return webClient.get()
                .uri("/api/v1/movies/events")
                .exchange()
                .flatMapMany(clientResponse -> clientResponse.bodyToFlux(MovieEvent.class));

    }
}
