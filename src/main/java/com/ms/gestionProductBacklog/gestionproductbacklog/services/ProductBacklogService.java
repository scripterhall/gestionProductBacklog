package com.ms.gestionProductBacklog.gestionproductbacklog.services;

import com.ms.gestionProductBacklog.gestionproductbacklog.entities.ProductBacklog;
import com.ms.gestionProductBacklog.gestionproductbacklog.models.HistoireTicket;
import com.ms.gestionProductBacklog.gestionproductbacklog.repositories.ProductBacklogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductBacklogService {
    @Autowired
    private ProductBacklogRepository productBacklogRepository;
    @Autowired
    private TicketHistoireService ticketHistoireService;
    public ProductBacklog getProductBacklogById(Long id) throws SQLException {
        return productBacklogRepository.findById(id).get();
    }
    public ProductBacklog findProductBacklogByProjetId(Long id){
        return this.productBacklogRepository.findProductBacklogByProjetId(id);
    }
    public ProductBacklog createNewProductBacklog(ProductBacklog backlog) {
        return productBacklogRepository.save(backlog);
    }

    /*public void elevateProductBacklogVelocity(Long productBacklogId, Long histoireTicketId) {
        HistoireTicket histoireTicket = ticketHistoireService.findUserStoryById(histoireTicketId);
        ProductBacklog productBacklog = productBacklogRepository.findById(productBacklogId).orElse(null);
        if (productBacklog != null && histoireTicket != null) {
            productBacklog.setVelocite(productBacklog.getVelocite() + histoireTicket.getEffort());
            productBacklogRepository.save(productBacklog);
        }
    }*/

    public void elevateProductBacklogVelocity(Long productBacklogId, int effort) throws ChangeSetPersister.NotFoundException {
        Optional<ProductBacklog> productBacklogOptional = productBacklogRepository.findById(productBacklogId);

        if (productBacklogOptional.isPresent()) {
            ProductBacklog productBacklog = productBacklogOptional.get();
            Integer currentVelocity = productBacklog.getVelocite();
            Integer newVelocity = currentVelocity + effort;
            productBacklog.setVelocite(newVelocity);
            productBacklogRepository.save(productBacklog);
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    public void decreaseProductBacklogVelocity(Long productBacklogId, Long histoireTicketId) {
        HistoireTicket histoireTicket = ticketHistoireService.findUserStoryById(histoireTicketId);
        ProductBacklog productBacklog = productBacklogRepository.findById(productBacklogId).orElse(null);
        if (productBacklog != null && histoireTicket != null) {
            productBacklog.setVelocite(productBacklog.getVelocite() - histoireTicket.getEffort());
            productBacklogRepository.save(productBacklog);
        }
    }

}

