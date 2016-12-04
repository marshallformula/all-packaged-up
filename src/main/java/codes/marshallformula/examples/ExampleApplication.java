package codes.marshallformula.examples;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.Ordered;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.*;

import java.util.Arrays;

@SpringBootApplication
public class ExampleApplication extends WebMvcConfigurerAdapter {

    public static void main(String[] args){
        SpringApplication.run(ExampleApplication.class, args);
    }


    @Value("${cors.origins}")
    private String origins;

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        if(!StringUtils.isEmpty(origins)){

            CorsRegistration registration = registry.addMapping("/api/**");

            Arrays.stream(origins.split(","))
                    .map(String::trim)
                    .forEach(registration::allowedOrigins);

            registration.allowedMethods("GET", "POST", "PUT", "DELETE");


        } else {
            super.addCorsMappings(registry);
        }
    }


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.setOrder(Ordered.LOWEST_PRECEDENCE);
        registry.addViewController("/**").setViewName("forward:/index.html");
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }
}
