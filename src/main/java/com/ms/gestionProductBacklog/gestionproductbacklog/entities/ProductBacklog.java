package com.ms.gestionProductBacklog.gestionproductbacklog.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.ms.gestionProductBacklog.gestionproductbacklog.models.Projet;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name="product_backlog")
@Data
@ToString
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class ProductBacklog extends Backlog{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //L'identifiant du product backlog
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(unique = true)
    private Long projetId;
    @Transient
    private Projet projet;
}
