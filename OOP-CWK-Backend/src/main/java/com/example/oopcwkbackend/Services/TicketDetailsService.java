package com.example.oopcwkbackend.Services;

import com.example.oopcwkbackend.Models.TicketData;
import com.example.oopcwkbackend.Models.TicketPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketDetailsService {

    private final TicketPool ticketPool;

    @Autowired
    public TicketDetailsService(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }

    public TicketData getTicketDetails() {
        return new TicketData(ticketPool.getTicketPoolSize(), ticketPool.getTicketsAdded(), ticketPool.getTicketsSold());
    }
}
