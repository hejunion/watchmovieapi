package jsure.tlight.omdb.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import jsure.tlight.omdb.app.MoiveAppProperties;
import jsure.tlight.omdb.model.Movie;
import jsure.tlight.omdb.service.MovieClientService;
import reactor.core.publisher.Mono;

@Service
public class MovieClientServiceImpl implements MovieClientService {

	private MoiveAppProperties appProperties;
	
	private final WebClient omdbWebClient;
	
	@Autowired
	public MovieClientServiceImpl(MoiveAppProperties appProperties) {
		this.appProperties = appProperties;
		 this.omdbWebClient = WebClient.builder()
				 .baseUrl(this.appProperties.getApiUrl())
				 .defaultHeader(HttpHeaders.CONTENT_TYPE, this.appProperties.getMimeType())
				 .defaultHeader(HttpHeaders.USER_AGENT, this.appProperties.getUserAgent())
				 .build();
	}

	@Override
	public Mono<Movie> searchMoveByTitle(String title) {

		String reqUrl = "/?apiKey="+this.appProperties.getApiKey()+"&"+"t="+title;
		Mono<Movie> result  =this.omdbWebClient.get()
				.uri(reqUrl).accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(Movie.class); 
		return result;
	}

	@Override
	public Mono<Movie> searchMovieById(String imdbId) {
		String reqUrl = "/?apiKey="+this.appProperties.getApiKey()+"&"+"i="+imdbId;
		Mono<Movie> result  =this.omdbWebClient.get()
				.uri(reqUrl).accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(Movie.class); 
		return result;
	}

}
