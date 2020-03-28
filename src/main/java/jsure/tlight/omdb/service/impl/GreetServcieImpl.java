package jsure.tlight.omdb.service.impl;

import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import jsure.tlight.omdb.service.GreetServcie;

@Service
public class GreetServcieImpl implements GreetServcie {

	@Override
	@Async
	public String greet(String name) {
		// TODO Auto-generated method stub
		System.out.println("Greeting in " + Thread.currentThread().getName());
		return name;
	}

	@Override
	@Async
	public CompletableFuture<String> greetFuture(String name) {
		System.out.println("Moving to future " + Thread.currentThread().getName());
		CompletableFuture<String> result = CompletableFuture.supplyAsync(()->{
			System.out.println("Greeting in future " + Thread.currentThread().getName());

			return name;
		}
		);
		return result;
	}

	@Override
	public CompletableFuture<Integer> greetSayHi() {
		
		CompletableFuture<Integer> hi= CompletableFuture.supplyAsync(
				() -> {
					System.out.println("Hi in future " + Thread.currentThread().getName());
					return Integer.valueOf("1000");
				}
				) ;
		
		return hi;
	}
	
	

	
}
