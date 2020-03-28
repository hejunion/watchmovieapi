package jsure.tlight.omdb.service;

import jsure.tlight.omdb.model.Movie;
import reactor.core.publisher.Mono;

public interface MovieClientService {
	public Mono<Movie> searchMoveByTitle(String title);
	public Mono<Movie> searchMovieById(String imdbId);
}
