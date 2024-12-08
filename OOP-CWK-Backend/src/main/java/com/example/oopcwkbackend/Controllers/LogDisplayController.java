package com.example.oopcwkbackend.Controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class LogDisplayController {

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/api/v1/logs")
    public ArrayList<String> returnLogs() {
        ArrayList<String> logs = new ArrayList<String>();
        logs.add("Log 1");
        logs.add("Other log");
        return logs;
    }
}
