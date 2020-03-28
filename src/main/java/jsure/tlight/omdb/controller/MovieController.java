package jsure.tlight.omdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import jsure.tlight.omdb.model.Movie;
import jsure.tlight.omdb.model.MovieEvent;
import jsure.tlight.omdb.service.MovieClientService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
public class MovieController {

	@Autowired
	MovieClientService movieClientService;
	
	@GetMapping("/movies/title/{name}")
	public Mono<Movie> getMovieByTitle(@PathVariable String name){
		return movieClientService.searchMoveByTitle(name);
	}
	
	@GetMapping("/movies/id/{imdbId}")
	public Mono<Movie> getMovieById(@PathVariable String imdbId){
		return movieClientService.searchMovieById(imdbId);
	}
	
	@GetMapping(value ="/movies/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<MovieEvent> getEvents(){
		return Flux.empty();
	}
	
	@ExceptionHandler(WebClientResponseException.class)
	public ResponseEntity<String> handleWebClientResponseException(WebClientResponseException wce){
		return ResponseEntity.status(wce.getStatusCode()).body(wce.getResponseBodyAsString());
	}
}
