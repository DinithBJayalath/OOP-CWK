package com.example.oopcwkbackend.Controllers;

import com.example.oopcwkbackend.Services.SimulateService;
import com.example.oopcwkcli.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class ConfigController {
    private final SimulateService simulateService;

    @Autowired
    public ConfigController(SimulateService simulateService) {
        this.simulateService = simulateService;
    }

    @PostMapping("/api/v1/config")
    public ResponseEntity<String> config(@RequestBody Configuration configuration) {
        System.out.println(configuration.toString());
        simulateService.simulate(configuration);
        return ResponseEntity.ok("Configuration received");
    }

    @GetMapping("/api/v1/stop")
    public ResponseEntity<String> stop() {
        simulateService.stopSimulation();
        return ResponseEntity.ok("Simulation stopped");
    }

    @PostMapping("/api/v1/updateArrays")
    public ResponseEntity<String> updateArrays(@RequestParam int vendors, @RequestParam int customers) {
        simulateService.updateArrays(vendors, customers);
        return ResponseEntity.ok("Arrays updated");
    }
}
