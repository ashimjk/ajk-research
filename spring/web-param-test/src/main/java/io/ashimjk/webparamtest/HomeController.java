package io.ashimjk.webparamtest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HomeController {

    @GetMapping
    public String testApi() {
        return "Ok";
    }

    @GetMapping("/queryParamsWithMap")
    public Map<String, Object> queryParamsWithMap(@RequestParam Map<String, Object> params) {
        return params;
    }

    /**
     * Will not work because person object cannot be passed in query params
     */
    @GetMapping("/queryParamsWithObject")
    public Person queryParamsWithObject(@RequestParam Person params) {
        return params;
    }

    @GetMapping("/queryParamsWithoutAnnotation")
    public Person queryParamsWithoutAnnotation(Person params) {
        return params;
    }

    /**
     * Will not work
     * <p>
     * Circular view path Issue
     */
    @GetMapping("/queryParamsWithoutAnnotationForMap")
    public Map<String, Object> queryParamsWithoutAnnotationForMap(Map<String, Object> params) {
        return params;
    }

    @GetMapping("/requestBodyWithMap")
    public Map<String, Object> requestBodyWithMap(@RequestBody Map<String, Object> params) {
        return params;
    }

    @GetMapping("/requestBodyWithObject")
    public Person requestBodyWithObject(@RequestBody Person params) {
        return params;
    }

}
