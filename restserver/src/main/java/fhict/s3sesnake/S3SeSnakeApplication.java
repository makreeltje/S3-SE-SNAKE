package fhict.s3sesnake;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class S3SeSnakeApplication {

    @GetMapping("/")
    public String home() {
        return "Welcome to snake auth rest service";
    }

    public static void main(String[] args) {
        SpringApplication.run(S3SeSnakeApplication.class, args);
    }

}
