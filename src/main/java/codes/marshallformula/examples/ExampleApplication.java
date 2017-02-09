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

    //this is to add allow the CORS origins specified on the cors.orgins property to communicate with this server.
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


    //this is necessary to forward all un-mapped requests to index.html.
    //This is required if you want to use the HTML5 History API
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.setOrder(Ordered.LOWEST_PRECEDENCE);
        registry.addViewController("/**").setViewName("forward:/index.html");
    }


    //this is helpful in connection with the method above to allow paths to the /assets folder for images, files etc
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }
}
