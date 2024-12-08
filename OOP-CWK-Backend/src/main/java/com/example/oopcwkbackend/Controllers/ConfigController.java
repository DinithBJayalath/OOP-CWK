package com.example.oopcwkbackend.Controllers;

import com.example.oopcwkbackend.Services.SimulateService;
import com.example.oopcwkcli.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ConfigController {
    private final SimulateService simulateService;

    @Autowired
    public ConfigController(SimulateService simulateService) {
        this.simulateService = simulateService;
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/api/v1/config")
    public ResponseEntity<String> config(@RequestBody Configuration configuration) {
        System.out.println(configuration.toString());
        simulateService.simulate(configuration);
        return ResponseEntity.ok("Configuration received");
    }
}
