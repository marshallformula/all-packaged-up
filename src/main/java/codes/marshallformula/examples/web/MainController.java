package codes.marshallformula.examples.web;

import codes.marshallformula.examples.service.ParkService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class MainController {

    private ParkService parkService;

    @Autowired
    public MainController(ParkService parkService) {
        this.parkService = parkService;
    }

    @RequestMapping("/parks")
    public ResponseEntity getParks() throws IOException {
        return ResponseEntity.ok(parkService.readParks());
    }
}
