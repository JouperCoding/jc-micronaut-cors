package cors.test;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.core.version.annotation.Version;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;

@io.micronaut.http.annotation.Controller("/")
public class Controller {

	private static Logger log = LoggerFactory.getLogger(Controller.class);


	/**
	 *
	 *  UNCOMMENT BELOW AND TEST AGAIN, OPTIONS CALL WILL WORK.
	 *
	 */

//	@Version("1")
//	@Options("{/path:.*}")
//	void handleOptions(@Nullable @PathVariable String path) {
//		log.info("world");
//	}

	@Version("1")
	@Post("/hello")
	public HttpResponse<?> helloV1() {
		log.info("hello");
		return HttpResponse.ok();
	}

}
