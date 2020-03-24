package io.ashimjk.spring.sse.web;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.ForkJoinPool;

@Log4j2
@RestController
public class DeferredController {

    /**
     * The problem here is that the request processing thread is blocked
     * until the complete request is processed and the result is returned.
     *
     * @return ok
     */
    @GetMapping("/process-blocking")
    public ResponseEntity<?> handleReqSync() {
        // ...
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/async-deferred-result")
    public DeferredResult<ResponseEntity<?>> handleReqDefResult() {
        log.info("Received async-deferred result request");
        DeferredResult<ResponseEntity<?>> output = new DeferredResult<>();

        ForkJoinPool.commonPool().submit(() -> {
            log.info("Processing in separate thread");
            try {
                Thread.sleep(6000);
                log.info("wake up !!!");
            } catch (Exception ignored) {
            }
            output.setResult(ResponseEntity.ok("ok"));
        });

        // on completion
        output.onCompletion(() -> log.info("Processing complete"));

        log.info("servlet thread freed");
        return output;
    }

    @GetMapping("/timeout")
    public DeferredResult<ResponseEntity<?>> handleTimeOut() {
        DeferredResult<ResponseEntity<?>> deferredResult = new DeferredResult<>(500L);

        ForkJoinPool.commonPool().submit(() -> {
            try {
                Thread.sleep(6000);
            } catch (Exception ignored) {
            }
            deferredResult.setResult(ResponseEntity.ok("ok"));
        });

        // on timeout
        deferredResult.onTimeout(() -> {
            deferredResult.setErrorResult(
                    ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body("Request timout occurred.")
            );
        });

        return deferredResult;
    }

    @GetMapping("/api-error")
    public DeferredResult<ResponseEntity<?>> handleError() {
        DeferredResult<ResponseEntity<?>> deferredResult = new DeferredResult<>();

        ForkJoinPool.commonPool().submit(() -> {
            try {
                throw new ApiServerException();
            } catch (Exception ex) {
                deferredResult.setErrorResult(
                        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage())
                );
            }
        });

        // on error
        deferredResult.onError((Throwable t) ->
                deferredResult.setErrorResult(
                        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(t.getMessage())
                ));

        return deferredResult;
    }

    private static class ApiServerException extends Exception {
        public ApiServerException() {
            super("Api Server Error");
        }

    }

}
