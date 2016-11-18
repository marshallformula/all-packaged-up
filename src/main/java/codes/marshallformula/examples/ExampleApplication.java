package codes.marshallformula.examples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ExampleApplication {

    public static void main(String[] args){
        SpringApplication.run(ExampleApplication.class, args);
    }
}
