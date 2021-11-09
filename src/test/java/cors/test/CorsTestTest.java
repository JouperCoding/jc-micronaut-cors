package cors.test;

import io.micronaut.context.ApplicationContext;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.HttpClient;
import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import jakarta.inject.Inject;

@MicronautTest
class CorsTestTest {

    private static EmbeddedServer server;
    private static HttpClient client;

    @BeforeAll
    public static void setupServer() {
        server = ApplicationContext.run(EmbeddedServer.class);
        client = server
           .getApplicationContext()
           .createBean(HttpClient.class, server.getURL());
    }

    @AfterAll
    public static void stopServer() {
        if(server != null) {
            server.stop();
        }
        if(client != null) {
            client.stop();
        }
    }

    @Test
    void testCors() {
        HttpRequest request = HttpRequest.POST("/hello", "").header(
           "X-Api-Version", "1"
        );
        HttpResponse rsp = client.toBlocking().exchange(request);
        Assertions.assertEquals(200, rsp.getStatus().getCode());

        //
        // THIS TEST WILL FAIL, SEE controller.java and uncomment the handleOptions function
        //
        request = HttpRequest.OPTIONS("/hello").header(
           "X-Api-Version", "1"
        );
        rsp = client.toBlocking().exchange(request);
        Assertions.assertEquals(200, rsp.getStatus().getCode());
    }

}
