package jsure.tlight.omdb.controller;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jsure.tlight.omdb.service.GreetServcie;

@RestController
@RequestMapping("/api/v2")
public class MovieAsyncController {
	
	@Autowired
	private GreetServcie greetServcie;
	
	@GetMapping("/greeting/{name}")
	public String greet(@PathVariable String name) {
		
		System.out.println("Greeting to " + Thread.currentThread().getName());

		greetServcie.greet(name);
		return name;
	}

	@GetMapping("/greeting/future/{name}")
	public CompletableFuture<ResponseEntity> greetFuture(@PathVariable String name) {
		
		System.out.println("Greeting to Future " + Thread.currentThread().getName());

		CompletableFuture<String> res = greetServcie.greetFuture(name);
		CompletableFuture<ResponseEntity> res1 = res.thenApply(
				r-> {
					System.out.println("Reponse in " + Thread.currentThread().getName());
					return ResponseEntity.ok(r);
				}
				);
		
		CompletableFuture<String> res2 = res1.thenCombine(greetServcie.greetSayHi(), (first, second) -> {return first.toString() + second;} );
		
		System.out.println("Greeted " + Thread.currentThread().getName());

		return res2.thenApply( ResponseEntity::ok);
	}
}
