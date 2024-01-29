package com.felipe.cursospring.domain.rest.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //--> getters, setters, hashcode, equals
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedidoDTO {
    
    private Integer produtoID;
    private Integer quantidade;
    
}
