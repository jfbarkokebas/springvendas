package com.felipe.cursospring.domain.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //--> getters, setters, hashcode, equals
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedidoDTO { //==> ok
    
    private Integer produtoID;
    private Integer quantidade;
    
}
