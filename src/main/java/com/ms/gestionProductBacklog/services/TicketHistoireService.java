package com.ms.gestionProductBacklog.services;

import com.ms.gestionProductBacklog.models.HistoireTicket;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="gestion-histoire-ticket")

public interface TicketHistoireService {
    @GetMapping("/histoireTickets/{id}")
    public HistoireTicket findUserStoryById(@PathVariable("id") Long id);

    @GetMapping("/histoireTickets/productBacklog/{id}")
    public List<HistoireTicket> findTicketHistoireByProductBacklog(@PathVariable("id") Long id);
}
