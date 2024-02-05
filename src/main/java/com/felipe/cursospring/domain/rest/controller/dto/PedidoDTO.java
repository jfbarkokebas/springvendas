package com.felipe.cursospring.domain.rest.controller.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //--> getters, setters, hashcode, equals
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO { //---> ok
    private Integer clienteID;
    private BigDecimal total;
    private List<ItemPedidoDTO> itens;
}
