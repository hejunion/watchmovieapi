package jsure.tlight.omdb.service;

import java.util.concurrent.CompletableFuture;

public interface GreetServcie {

	public String greet(String name);
	
	public CompletableFuture<String> greetFuture(String name);
	
	public CompletableFuture<Integer> greetSayHi();
}
