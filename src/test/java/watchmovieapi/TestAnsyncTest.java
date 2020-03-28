package watchmovieapi;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestAnsyncTest {

	Map<String , String >someStateVaribale = new HashMap<>();
	
	public void process() {
		System.out.println(Thread.currentThread() + " Process");
		this.someStateVaribale.put("100","OK");
	}
	
	@Test
	public void completableFutureRunAsync() {
		CompletableFuture<Void> runAsync = CompletableFuture.runAsync(() -> process());
		
		runAsync.join();
		
		assertEquals("OK", someStateVaribale.get("100"));
	}
	
}
