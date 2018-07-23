package springframework.guru.movieeventservice.service;



import springframework.guru.movieeventservice.domain.MovieEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Date;

@Service
public class MovieServiceImpl implements MovieService {

	private String eventMessage= "Watch out this space for events";
	@Override
	public Flux<MovieEvent> events() {
		return Flux.<MovieEvent>generate(movieEventSynchronousSink -> {
					movieEventSynchronousSink.next(new MovieEvent(eventMessage,new Date()));
				}
		).delayElements(Duration.ofSeconds(1));
	}

}
