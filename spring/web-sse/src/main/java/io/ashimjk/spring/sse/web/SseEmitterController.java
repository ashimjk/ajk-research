package io.ashimjk.spring.sse.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class SseEmitterController {

    private ExecutorService nonBlockingService = Executors.newCachedThreadPool();

    @GetMapping("/rbe")
    public ResponseEntity<ResponseBodyEmitter> handleRbe() {
        ResponseBodyEmitter emitter = new ResponseBodyEmitter();
        nonBlockingService.execute(() -> {
            try {
                emitter.send(
                        "/rbe" + " @ " + new Date(), MediaType.TEXT_PLAIN);
                emitter.complete();
            } catch (Exception ex) {
                emitter.completeWithError(ex);
            }
        });
        return new ResponseEntity<>(emitter, HttpStatus.OK);
    }

    @GetMapping("/sse")
    public SseEmitter handleSse() {
        SseEmitter sseEmitter = new SseEmitter();

        nonBlockingService.execute(() -> {
            try {
                sseEmitter.send("/sse" + " @ " + new Date());
                sseEmitter.complete();
            } catch (Exception ex) {
                sseEmitter.completeWithError(ex);
            }
        });

        return sseEmitter;
    }

    @GetMapping("/srb")
    public ResponseEntity<StreamingResponseBody> handleSrb() {
        StreamingResponseBody stream = out -> {
            String msg = "/srb" + " @ " + new Date();
            out.write(msg.getBytes());
        };
        return new ResponseEntity<>(stream, HttpStatus.OK);
    }

}
