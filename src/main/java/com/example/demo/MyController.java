
package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
public class MyController {

    @GetMapping("/test")
    @CircuitBreaker(name = "backendA", fallbackMethod = "fallbackMethod")
    public String testService() {
        if (Math.random() > 0.5) {
            throw new RuntimeException("Service failed");
        }
        return "Service succeeded";
    }

    public String fallbackMethod(Throwable t) {
        return "Fallback: " + t.getMessage();
    }
}
