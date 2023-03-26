package com.ms.gestionProductBacklog.gestionproductbacklog.controllers;

import com.ms.gestionProductBacklog.gestionproductbacklog.entities.ProductBacklog;
import com.ms.gestionProductBacklog.gestionproductbacklog.models.HistoireTicket;
import com.ms.gestionProductBacklog.gestionproductbacklog.models.Projet;
import com.ms.gestionProductBacklog.gestionproductbacklog.services.ProductBacklogService;
import com.ms.gestionProductBacklog.gestionproductbacklog.services.ProjetClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/product-backlogs")
public class ProductBacklogController {
    @Autowired
    private ProductBacklogService productBacklogService;
    @Autowired
    private ProjetClientService projetClientService;
    @Autowired
    private RestTemplate restTemplate;
    @GetMapping("/{id}")
    public ProductBacklog getProductBacklogById(@PathVariable(name="id") Long id){
        try {
            return this.productBacklogService.getProductBacklogById(id);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null ;
    }

    @GetMapping
    public List<ProductBacklog> getAllProductBacklogs(){
        try {
            return this.productBacklogService.getAllProductBacklogs();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
    @GetMapping("/projet/{idProjet}")
    public ProductBacklog getProductBacklogByIdProjet(@PathVariable(name="idProjet") Long idProjet) throws SQLException{
        Projet projet  = this.projetClientService.findProjetById(idProjet);
        ProductBacklog productBacklog = this.productBacklogService.findProductBacklogByProjetId(idProjet);
        productBacklog.setProjet(projet);
        return productBacklog;
    }
    @GetMapping("/{productBacklogId}/histoiresTickets")
    public List<HistoireTicket> getHistoireTicketsByProductBacklogId(@PathVariable Long productBacklogId) {
        String url = "http://localhost:9999/gestion-histoire-ticket/histoireTickets/product-backlog/" + productBacklogId;
        return restTemplate.getForObject(url, List.class);
    }
    @PostMapping
    public ResponseEntity<ProductBacklog> create(@RequestBody ProductBacklog backlog, @RequestParam Long projectId) {
        backlog.setProjetId(projectId);
        ProductBacklog createdBacklog = productBacklogService.createNewProductBacklog(backlog);
        return new ResponseEntity<>(createdBacklog, HttpStatus.CREATED);
    }

}
