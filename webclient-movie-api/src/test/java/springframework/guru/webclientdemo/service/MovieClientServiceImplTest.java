package springframework.guru.webclientdemo.service;

import org.junit.Before;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import springframework.guru.webclientdemo.domain.Movie;
import org.assertj.core.api.Assertions;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.Duration;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//@AutoConfigureWebTestClient(timeout = "36000")
public class MovieClientServiceImplTest {

    @Autowired
    private WebTestClient webTestClient;

@Before
public void setUp() {
    webTestClient = webTestClient
            .mutate()
            .responseTimeout(Duration.ofMillis(36000))
            .build();
}
    @Test
    public void testGetMovieById() {
        webTestClient.get()
                .uri("/api/v1/movies/id/{imdbId}","tt3896198" )
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(response ->
                        Assertions.assertThat(response.getResponseBody()).isNotNull());
    }

    @Test
    public void testGetMovieByName() {
        webTestClient.get()
                .uri("/api/v1/movies/title/{name}", "Superman")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(response ->
                        Assertions.assertThat(response.getResponseBody()).isNotNull());
    }
}
