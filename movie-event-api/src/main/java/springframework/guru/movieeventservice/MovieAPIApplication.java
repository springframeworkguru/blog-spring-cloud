package springframework.guru.movieeventservice;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
public class MovieAPIApplication {
	public static void main(final String[] args) {
		SpringApplication.run(MovieAPIApplication.class, args);
	}
}
