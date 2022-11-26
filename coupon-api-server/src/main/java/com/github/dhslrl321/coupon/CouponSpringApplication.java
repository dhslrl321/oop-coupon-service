package com.github.dhslrl321.coupon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class CouponSpringApplication {

    @RestController
    public static class HealthCheckApi {
        @GetMapping("/health")
        public ResponseEntity<String> health() {
            return ResponseEntity.ok("ok");
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(CouponSpringApplication.class, args);
    }
}
