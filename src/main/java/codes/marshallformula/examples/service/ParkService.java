package codes.marshallformula.examples.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;

@Component
public class ParkService {

    public JsonNode readParks() throws IOException {
        File parksFile = ResourceUtils.getFile(this.getClass().getResource("/data/parks.json"));
        return new ObjectMapper().readTree(parksFile);
    }
}
