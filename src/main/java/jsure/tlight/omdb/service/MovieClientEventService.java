package jsure.tlight.omdb.service;

import jsure.tlight.omdb.model.MovieEvent;
import reactor.core.publisher.Flux;

public interface MovieClientEventService {
	public Flux<MovieEvent> getMovieEvents();
}
