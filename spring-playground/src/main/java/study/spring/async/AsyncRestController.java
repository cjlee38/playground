package study.spring.async;

import io.netty.channel.nio.NioEventLoopGroup;
import java.util.Objects;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.Netty4ClientHttpRequestFactory;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
public class AsyncRestController {
    private static final String URL = "http://www.example.com";

    private final RestTemplate rt = new RestTemplate();
    private final AsyncRestTemplate art = new AsyncRestTemplate(); // (deprecated) this creates new thread per request
    private final AsyncRestTemplate nart =
            new AsyncRestTemplate(new Netty4ClientHttpRequestFactory(new NioEventLoopGroup(1))); // this would be better than above one

    /**
     * This blocks thread
     */
    @GetMapping("/rest/v1")
    public String v1() {
        return rt.getForObject(URL, String.class);
    }

    /**
     * do not extract, just return. Spring handles these.
     */
    @GetMapping("/rest/v2")
    public ListenableFuture<ResponseEntity<String>> v2() {
        ListenableFuture<ResponseEntity<String>> response = art.getForEntity(URL, String.class);
        return response;
    }

    /**
     * use netty request factory, because of thread creation problem.
     */
    @GetMapping("/rest/v3")
    public ListenableFuture<ResponseEntity<String>> v3() {
        return nart.getForEntity(URL, String.class);
    }


    /**
     * You can also add callback to future
     */
    @GetMapping("/rest/v4")
    public DeferredResult<String> v4() {
        DeferredResult<String> dr = new DeferredResult<>();
        ListenableFuture<ResponseEntity<String>> response = nart.getForEntity(URL, String.class);
        response.addCallback(
                (s) -> dr.setResult(Objects.requireNonNull(s).getBody() + "/work"),
                (e) -> dr.setErrorResult(e.getMessage())
        );
        return dr; // see `DeferredController`
    }

    /**
     * What if you need another requests in response to initial request ?<br>
     * just nesting callbacks ?
     */
    @GetMapping("/rest/v5")
    public DeferredResult<String> v5() {
        DeferredResult<String> dr = new DeferredResult<>();
        ListenableFuture<ResponseEntity<String>> firstResponse = nart.getForEntity(URL, String.class);
        firstResponse.addCallback(
                (firstApiResponse) -> {
                    // let's assume that this utilizes `firstApiResponse` even if not specified.
                    ListenableFuture<ResponseEntity<String>> secondResponse = nart.getForEntity(URL, String.class);
                    secondResponse.addCallback(
                            (secondApiResponse) -> dr.setResult(Objects.requireNonNull(secondApiResponse).getBody() + "/work2"),
                            (e2) -> dr.setErrorResult(e2.getMessage())
                    );
                },
                (e) -> dr.setErrorResult(e.getMessage())
        );
        return dr;
    }
}
