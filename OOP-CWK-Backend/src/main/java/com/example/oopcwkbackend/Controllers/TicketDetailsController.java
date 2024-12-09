package com.example.oopcwkbackend.Controllers;

import com.example.oopcwkbackend.Models.TicketData;
import com.example.oopcwkbackend.Services.TicketDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TicketDetailsController {

    private final TicketDetailsService ticketDetailsService;

    @Autowired
    public TicketDetailsController(TicketDetailsService ticketDetailsService) {
        this.ticketDetailsService = ticketDetailsService;
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/api/v1/tickets")
    public TicketData returnTicketDetails() {
        return ticketDetailsService.getTicketDetails();
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/api/v1/reset")
    public void resetTicketDetails() {
        ticketDetailsService.resetTicketDetails();
    }
}
