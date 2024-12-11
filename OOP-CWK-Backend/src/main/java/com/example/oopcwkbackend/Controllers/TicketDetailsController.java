package com.example.oopcwkbackend.Controllers;

import com.example.oopcwkbackend.Models.TicketData;
import com.example.oopcwkbackend.Services.TicketDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class TicketDetailsController {
    /**
     * Ticket details controller class,
     * this class handles the REST API requests related to displaying ticket details
     */

    private final TicketDetailsService ticketDetailsService;

    @Autowired
    public TicketDetailsController(TicketDetailsService ticketDetailsService) {
        this.ticketDetailsService = ticketDetailsService;
    }

    @GetMapping("/api/v1/tickets")
    public TicketData returnTicketDetails() {
        return ticketDetailsService.getTicketDetails();
    }

    @PostMapping("/api/v1/reset")
    public ResponseEntity<String> resetTicketDetails() {
        ticketDetailsService.resetTicketDetails();
        return ResponseEntity.ok("Ticket details reset successfully!");
    }
}
