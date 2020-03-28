package jsure.tlight.omdb.app;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration	
@EnableAsync
public class MovieAsyncConfiguraitoin extends AsyncConfigurerSupport {

}
