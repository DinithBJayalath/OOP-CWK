package com.example.oopcwkbackend.Controllers;

import com.example.oopcwkbackend.Models.TicketData;
import com.example.oopcwkbackend.Services.TicketDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
