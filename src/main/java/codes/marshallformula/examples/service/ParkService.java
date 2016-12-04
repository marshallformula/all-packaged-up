package codes.marshallformula.examples.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Component
public class ParkService {

    public JsonNode readParks() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream parksFile = classLoader.getResourceAsStream("data/parks.json");
        return new ObjectMapper().readTree(parksFile);
    }
}
