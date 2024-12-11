package com.example.oopcwkbackend.Controllers;

import com.example.oopcwkbackend.Services.LogDisplayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class LogDisplayController {
    /**
     * Log display controller class,
     * this class handles the REST API requests related to the display of logs
     */

    private final LogDisplayService logDisplayService;

    @Autowired
    public LogDisplayController(LogDisplayService logDisplayService) {
        this.logDisplayService = logDisplayService;
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/api/v1/logs")
    public ArrayList<String> returnLogs() {
        return logDisplayService.returnLogs();
    }
}
