package io.ashimjk.wartest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WarController {

    @GetMapping("service")
    public ResponseEntity<Service> getService() {
        Service service = new Service("web-service");
        return ResponseEntity.ok(service);
    }

}
