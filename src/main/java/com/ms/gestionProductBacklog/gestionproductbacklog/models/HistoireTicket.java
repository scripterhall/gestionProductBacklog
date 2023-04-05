package com.ms.gestionProductBacklog.gestionproductbacklog.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoireTicket {
    private Long id;
    private String priorite;
    private int effort;
    private int position;
}
