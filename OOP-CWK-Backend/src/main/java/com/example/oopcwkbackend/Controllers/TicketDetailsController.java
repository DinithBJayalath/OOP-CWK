package com.example.oopcwkbackend.Controllers;

import com.example.oopcwkbackend.Models.TicketData;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicketDetailsController {

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/api/v1/tickets")
    public TicketData returnTicketDetails() {
        return new TicketData(10, 40, 40);
    }
}
